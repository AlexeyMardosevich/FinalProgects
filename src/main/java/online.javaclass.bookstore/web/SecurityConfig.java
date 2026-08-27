package online.javaclass.bookstore.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                    .invalidSessionUrl("/login")
                    .maximumSessions(5).maxSessionsPreventsLogin(true)
                    .and()
                .and()

                .cors()
                .and()

                .authorizeRequests()
                .mvcMatchers("/", "/css/**", "/js/**", "/images/**")
                    .permitAll()

                .mvcMatchers(HttpMethod.GET, "/books/getAll", "/books/{id}", "/api/books", "/api/books/**")
                .permitAll()

                .mvcMatchers( HttpMethod.GET, "/books/create", "/books/edit/**")
                    .hasAnyAuthority("manager")

                .mvcMatchers(HttpMethod.POST, "/books/create", "/books/edit/**", "/books/delete/**","/api/books")
                    .hasAnyAuthority("manager")

                .mvcMatchers(HttpMethod.PUT, "/api/books/**")
                    .hasAuthority("manager")

                .mvcMatchers(HttpMethod.PATCH, "/api/books/**")
                    .hasAuthority("manager")

                .mvcMatchers(HttpMethod.DELETE, "/api/books/**")
                    .hasAuthority("manager")

                .mvcMatchers(HttpMethod.GET, "/orders/getAll")
                    .hasAuthority("manager")

                .mvcMatchers(HttpMethod.POST, "/orders/create", "/orders/edit/**", "/orders/delete/**")
                    .hasAuthority("manager")

                .mvcMatchers("/users/**", "/api/users/**")
                    .hasAuthority("admin")

                .mvcMatchers("/cart/**", "/api/cart/**", "/api/orders/cart/**")
                    .authenticated()

                .mvcMatchers("/login", "/login/**")
                    .permitAll()

                .anyRequest()
                    .authenticated()
                .and()

                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/")
                    .failureUrl("/login")
                    .and()

                .logout()
                    .logoutUrl("/login/logout")
                    .clearAuthentication(true)
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl("/login")
                    .permitAll()
                    .and()
                .build();
    }
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource){
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        manager.setUsersByUsernameQuery("SELECT email, password, true AS enable FROM users WHERE email = ?");
        manager.setAuthoritiesByUsernameQuery("SELECT email, role FROM users WHERE email = ?");
        return manager;
    }
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher(){
        return new HttpSessionEventPublisher();
    }
}
