package com.durga.securityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf->csrf.disable()).authorizeHttpRequests(auth->auth.requestMatchers("/registration").permitAll()
				.requestMatchers("/students").permitAll()
				.requestMatchers("/v1/login").permitAll()
				.anyRequest().authenticated()
				);
		return http.build();
		
	}
}
