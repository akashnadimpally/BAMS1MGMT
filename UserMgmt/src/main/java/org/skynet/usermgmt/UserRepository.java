package org.skynet.usermgmt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {

    boolean existsByEmail(String email);
    boolean existsBySSN(Long SSN);
    boolean existsByMobileNumber(Long mobileNumber);

}
