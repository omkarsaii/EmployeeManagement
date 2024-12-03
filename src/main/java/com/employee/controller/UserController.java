package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.Users;
import com.employee.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping("/register")
	public Users registeruser(@RequestBody Users user)
	{
		return service.register(user);
	}
	
	@PostMapping("/login")
	public String loginuser(@RequestBody Users user)
	{
//		System.out.println(user);
		return service.verify(user);
	}

}
