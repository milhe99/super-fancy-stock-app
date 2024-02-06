package fi.jyu.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.junit.jupiter.api.Test;

@SpringBootTest
public class DatacaseConnectionTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testDatabaseConnection() {
        String result = jdbcTemplate.queryForObject("SELECT 'Database connection is working'", String.class);
        System.out.println(result);
    }
}
