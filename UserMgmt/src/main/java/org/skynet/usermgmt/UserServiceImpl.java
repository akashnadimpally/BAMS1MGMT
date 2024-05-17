package org.skynet.usermgmt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MemberIDGenerator memberIDGenerator;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private HashingService hashservice;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Users createUser(String firstname, String lastname, String email, String nationality, Long mobile, String password, Date dob, Long ssn) {

        Users user = new Users();

        try {

            logger.info(email);
            logger.info(String.valueOf(ssn));
            logger.info(String.valueOf(mobile));

        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already in use");
        }

        if (userRepository.existsBySSN(ssn)) {
            throw new RuntimeException("SSN already in use");
        }

        if (userRepository.existsByMobileNumber(mobile)) {
            throw new RuntimeException("Mobile Number already in use");
        }

        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setEmail(email);
        user.setNationality(nationality);
        user.setMobileNumber(mobile);

        String encodedPassword = hashservice.hashPassword(password);
        user.setPassword(encodedPassword);

        String encodedSSN = hashservice.hashSSN(ssn);
        user.setSSN(ssn);

        user.setDateOfBirth(dob);

        String memberID = memberIDGenerator.generateUniqueMemberID();
        user.setMemberID(memberID);

        saveUser(user);
        return user;
        }
        catch (DuplicateKeyException e) {
            System.err.println("Error: Duplicate entry for key. " + e.getMessage());
            return null;
        } catch (NullPointerException e) {
            System.err.println("Null Pointer error occurred: " + e.getMessage());
            return null;
        }
        catch (DataAccessException e) {
            System.err.println("Database access error occurred: " + e.getMessage());
            return null;
        }
    }

    private void saveUser(Users user) {
        String sql = "INSERT INTO Users(MemberID,FirstName,LastName,Email,Nationality,MobileNumber,Password,SSN,DateOfBirth) VALUES(?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, user.getMemberID(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getNationality(), user.getMobileNumber(), user.getPassword(), user.getSSN(), user.getDateOfBirth());
    }

}
