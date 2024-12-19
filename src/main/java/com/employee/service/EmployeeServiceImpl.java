package com.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.entity.Employee;
import com.employee.repo.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository repo;

	@Override
	public Employee saveEmployee(Employee employee) {
		
		return repo.save(employee);
	}

	@Override
	public List<Employee> getAllEmployee() {
		
		return repo.findAll();
	}

	@Override
	public Optional<Employee> getEmployeeById(Long id) {
		
		return repo.findById(id);
	}

	@Override
	public void deleteEmployeeById(Long id) {
		
		 repo.deleteById(id);
		
	}

	@Override
	public List<Employee> getEmployeeWithSalaryGreaterThan(double salary) {
		
		return repo.findEmployeeWithsalaryGreaterThan(salary);
	}
	
	
}
