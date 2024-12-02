package com.employee.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		return 
		http
		.csrf(Customizer->Customizer.disable()) //csrf().disable()
		.authorizeHttpRequests(request ->request
				.requestMatchers("/register").permitAll()
				.anyRequest().authenticated())
//		.formLogin(Customizer.withDefaults())  //it will enable in browser like chrome 
		.httpBasic(Customizer.withDefaults())  // enable postman
		.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.build();
	}
	
	
//	@Bean
//	public  UserDetailsService userDetailsService()
//	{
//		@SuppressWarnings("deprecation")
//		UserDetails user1=User
//				.withDefaultPasswordEncoder()
//				.username("omkar")
//				.password("omkar")
//				.roles("USER")
//				.build();
//		
//		return new InMemoryUserDetailsManager(user1);
//	}
	
	@Bean
	public AuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
	
}
