package com.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.employee.entity.UserPrincipal;
import com.employee.entity.Users;
import com.employee.repo.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		Users user=repo.findByUsername(username);
		
		if(user==null)
		{
			throw new UsernameNotFoundException("user not found");
		}
		
		
		return new UserPrincipal(user);
	}
	
	
	

}
