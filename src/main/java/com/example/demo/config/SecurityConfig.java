package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .anyRequest().permitAll()  // Разрешить все запросы
                )
                .csrf(csrf -> csrf.disable())  // Отключить CSRF
                .formLogin(form -> form.disable())  // Отключаем стандартную форму login
                .httpBasic(httpBasic -> httpBasic.disable());  // Отключаем basic auth

        // Для H2 консоли
        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
}