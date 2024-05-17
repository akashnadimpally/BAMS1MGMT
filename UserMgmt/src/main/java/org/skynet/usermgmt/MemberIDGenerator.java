package org.skynet.usermgmt;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Random;

@Component
public class MemberIDGenerator {

    private final JdbcTemplate jdbcTemplate;

    public MemberIDGenerator(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final Random random = new Random();
    private static final int MAX = 999999;
    private static final int MIN = 100000;

    public String generateUniqueMemberID() {
        String memberId;
        do {
            int number = random.nextInt((MAX - MIN) + 1) + MIN;
            memberId = String.format("Bnk00%d", number);
        } while (!isMemberIDUnique(memberId));
        return memberId;
    }

    private boolean isMemberIDUnique(String memberId) throws NullPointerException{
        try {
            String sql = "SELECT COUNT(*) FROM Users WHERE MemberID=?";
            int count;
            count = jdbcTemplate.queryForObject(sql, new Object[]{memberId}, Integer.class);
            return count == 0;
        }catch (Exception e) {
            return false;
        }
    }

}
