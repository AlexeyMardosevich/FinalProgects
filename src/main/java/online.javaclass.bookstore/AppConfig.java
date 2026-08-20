package online.javaclass.bookstore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
@Configuration
@ComponentScan
//@PropertySource("classpath:/application.properties")
@EnableTransactionManagement
public class AppConfig {
   /* @Value("${db.URL}")
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
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(HikariDataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);

    }*/

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("databases");
    }

    @Bean
    public TransactionManager transactionManager(EntityManagerFactory factory){
        return new JpaTransactionManager(factory);
    }
}
