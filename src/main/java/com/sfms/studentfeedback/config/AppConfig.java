package com.sfms.studentfeedback.config;

import com.sfms.studentfeedback.security.JwtAuthenticationFilter;
import com.sfms.studentfeedback.security.JwtUtil;
import com.sfms.studentfeedback.security.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtUtil jwtUtil, CustomUserDetailsService customUserDetailsService) {
        return new JwtAuthenticationFilter(jwtUtil, customUserDetailsService);
    }
}
