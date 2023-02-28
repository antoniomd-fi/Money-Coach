package com.example.moneycoach.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception{

        http.csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/admin/**")
                .hasRole("ADMIN")
                /*.requestMatchers("/getUser/1", "/getBalance/1",  "/getTotalEntriesByUser/1", "/getTotalExitsByUser/1")
                .hasAnyRole("USER1", "ADMIN")
                .requestMatchers("/getUser/2", "/getBalance/2",  "/getTotalEntriesByUser/2", "/getTotalExitsByUser/2")
                .hasAnyRole("USER2", "ADMIN")*/
                .anyRequest()
                .anonymous()
                .and()
                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
}
