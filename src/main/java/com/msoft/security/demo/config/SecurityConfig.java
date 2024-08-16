package com.msoft.security.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.authorizeHttpRequests(request -> {
			request.requestMatchers("/home", "/login").permitAll();
			request.requestMatchers("/user/**").hasRole("USER");
			request.requestMatchers("/admin/**").hasRole("ADMIN");
			request.anyRequest().authenticated();
		})
				.formLogin(Customizer.withDefaults())
				.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.builder().username("user")
		.password(passwordEncoder().encode("1234")).roles("USER").build();
		UserDetails admin = User.builder().username("admin")
				.password(passwordEncoder().encode("5678")).roles("USER","ADMIN").build();
		return new InMemoryUserDetailsManager(user, admin);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
