package online.javaclass.bookstore;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
@ComponentScan
@PropertySource("classpath:/application.properties")
public class AppConfig {
    @Value("${db.URL}")
    private String URL;
    @Value("${db.Login}")
    private String LOGIN;
    @Value("${db.Password}")
    private String PASSWORD;

    @Bean
    public HikariDataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(LOGIN);
        dataSource.setPassword(PASSWORD);
        dataSource.setDriverClassName("org.postgresql.Driver");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(HikariDataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(HikariDataSource dataSource) {
        return new  NamedParameterJdbcTemplate(dataSource);
    }
}