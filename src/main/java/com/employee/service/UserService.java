package com.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.employee.entity.Users;
import com.employee.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	
	private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();

	public Users register(Users user) {
		
		user.setPassword(encoder.encode(user.getPassword()));
		
		return repo.save(user);
		
	}
	
}
