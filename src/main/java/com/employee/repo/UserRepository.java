package com.employee.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

	//finding user by name from the DB
	Users findByUsername(String username);

}
