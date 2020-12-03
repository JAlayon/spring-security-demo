package com.alayon.springsecuritydemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.alayon.springsecuritydemo.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {


    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/v1/students") .permitAll() //This is the white list for access resources
                .antMatchers("/api/**").hasRole(STUDENT.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Bean

    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails student1 = User.builder()
                                .username("jalayon")
                                .password(passwordEncoder.encode("password"))
                                .roles(STUDENT.name()) //ROLE_STUDENT. This is how spring understand this role.
                                .build();

        UserDetails student2 = User.builder()
                .username("majones")
                .password(passwordEncoder.encode("password"))
                .roles(ADMIN.name())
                .build();

        UserDetails student3 = User.builder()
                .username("anasmith")
                .password(passwordEncoder.encode("password"))
                .roles(STUDENT.name())
                .build();

        return new InMemoryUserDetailsManager(
                student1, student2, student3
        );
    }
}
