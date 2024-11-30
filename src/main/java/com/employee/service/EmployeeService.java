package com.employee.service;

import java.util.List;
import java.util.Optional;

import com.employee.entity.Employee;

public interface EmployeeService {

	Employee saveEmployee(Employee employee);

	List<Employee> getAllEmployee();

	Optional<Employee> getEmployeeById(Long id);

	void deleteEmployeeById(Long id);

}
