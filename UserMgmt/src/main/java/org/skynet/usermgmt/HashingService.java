package org.skynet.usermgmt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Component
public class HashingService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public String hashSSN(Long ssn) {
        String temp = ssn.toString();
        return passwordEncoder.encode(temp);
    }

}
