package com.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.employee.entity.Users;
import com.employee.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	JwtService jwtservice;
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	AuthenticationManager manager;
	
	private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();

	public Users register(Users user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return repo.save(user);
	}

	
	public String verify(Users user) {	
		try
		{
		Authentication authentication=manager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		
		if(authentication.isAuthenticated())
			return jwtservice.generateToken(user.getUsername());
		
		return "fail";
	}
		catch (Exception e) {
	        return "Authentication failed";
	    }
	}
}
