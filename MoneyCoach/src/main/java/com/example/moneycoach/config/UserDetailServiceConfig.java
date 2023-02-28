package com.example.moneycoach.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserDetailServiceConfig {

    @Bean
    public UserDetailsService userDetailsService (BCryptPasswordEncoder bCryptPasswordEncoder){

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        manager.createUser(
                User.withUsername("admin")
                        .password(bCryptPasswordEncoder.encode("admin"))
                        .roles("ADMIN")
                        .build()
        );

        manager.createUser(
                User.withUsername("amd")
                        .password(bCryptPasswordEncoder.encode("1234"))
                        .roles("USER1")
                        .build()
        );

        manager.createUser(
                User.withUsername("test")
                        .password(bCryptPasswordEncoder.encode("1234"))
                        .roles("USER2")
                        .build()
        );
        return manager;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
