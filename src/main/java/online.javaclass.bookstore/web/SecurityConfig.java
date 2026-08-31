package online.javaclass.bookstore.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
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

                .mvcMatchers(HttpMethod.GET, "/books/create", "/books/edit/**")
                .hasAnyAuthority("MANAGER", "ADMIN")

                .mvcMatchers(HttpMethod.POST, "/books/create", "/books/edit/**", "/books/delete/**", "/api/books")
                .hasAnyAuthority("MANAGER", "ADMIN")

                .mvcMatchers(HttpMethod.PUT, "/api/books/**")
                .hasAuthority("MANAGER")
                .mvcMatchers(HttpMethod.PUT, "/api/books/**")
                .hasAuthority("ADMIN")

                .mvcMatchers(HttpMethod.PATCH, "/api/books/**")
                .hasAuthority("MANAGER")
                .mvcMatchers(HttpMethod.PATCH, "/api/books/**")
                .hasAuthority("ADMIN")

                .mvcMatchers(HttpMethod.DELETE, "/api/books/**")
                .hasAuthority("MANAGER")
                .mvcMatchers(HttpMethod.DELETE, "/api/books/**")
                .hasAuthority("ADMIN")

                .mvcMatchers(HttpMethod.GET, "/orders/getAll")
                .hasAuthority("MANAGER")
                .mvcMatchers(HttpMethod.GET, "/orders/getAll")
                .hasAuthority("ADMIN")

                .mvcMatchers(HttpMethod.POST, "/orders/create", "/orders/edit/**", "/orders/delete/**")
                .hasAuthority("MANAGER")
                .mvcMatchers(HttpMethod.POST, "/orders/create", "/orders/edit/**", "/orders/delete/**")
                .hasAuthority("ADMIN")

                .mvcMatchers("/users/**", "/api/users/**")
                .hasAuthority("ADMIN")

                .mvcMatchers("/cart/**", "/api/cart/**", "/api/orders/cart/**")
                .authenticated()

                .anyRequest()
                .authenticated()
                .and()

                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error=true")
                .permitAll()
                .and()

                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login?logout=true")
                .permitAll()
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        manager.setUsersByUsernameQuery("SELECT email, password, true AS enable FROM users WHERE email = ?");
        manager.setAuthoritiesByUsernameQuery("SELECT email, role FROM users WHERE email = ?");
        return manager;
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}
