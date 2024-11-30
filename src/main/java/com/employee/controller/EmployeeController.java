package com.employee.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.Employee;
import com.employee.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	
	@RequestMapping(method = RequestMethod.POST,value="/add")
	public Employee addEmployee(@RequestBody Employee employee)
	{
		return service.saveEmployee(employee);	 
	}
	
	@GetMapping("/getall")
	public List<Employee> getAllDetails()
	{
		return service.getAllEmployee();
	}
	
//	@RequestMapping(method = RequestMethod.GET,value ="/get/{id}")
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable Long id)
	{
		Optional<Employee>employee=service.getEmployeeById(id);
		
		if(employee.isPresent())
		{
			return ResponseEntity.ok(employee.get());
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee id "+id+" not found");
		}
	}
	
//	@RequestMapping(method = RequestMethod.PUT,value="/{id}")
	@PutMapping("/put/{id}")
	public ResponseEntity<Employee>updateEmployeePut(@PathVariable Long id,@RequestBody Employee employee)
	{
		Optional<Employee>emp=service.getEmployeeById(id);
		
		if(emp.isPresent())
		{
			employee.setId(id);
			Employee updateEmployee=service.saveEmployee(employee);
			
			return ResponseEntity.ok(updateEmployee);
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
		
	}

	@RequestMapping(method = RequestMethod.PATCH,value = "/{id}")
	public ResponseEntity<Employee>updateEmployee(@PathVariable Long id,@RequestBody Employee employee)
	{
		Optional<Employee>emp=service.getEmployeeById(id);
		
		if(emp.isPresent())
		{
			employee.setId(id);
			
			Employee updateEmployee=emp.get();
			
			if(employee.getName()!=null)
			{
				updateEmployee.setName(employee.getName());
			}
			
			if(employee.getEmail()!=null)
			{
				updateEmployee.setEmail(employee.getEmail());
			}
			
			if(employee.getDepartment()!=null)
			{
				updateEmployee.setDepartment(employee.getDepartment());
			}
			if(employee.getRole()!=null)
			{
				updateEmployee.setRole(employee.getRole());
			}
			
			Employee saveemp=service.saveEmployee(updateEmployee);
			
			return ResponseEntity.ok(saveemp);
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
		
	}
	
//	@DeleteMapping("/delete/{id}")
	@RequestMapping("/delete/{id}")
	public ResponseEntity<String>DeleteById(@PathVariable Long id)
	{
		Optional<Employee> emp=service.getEmployeeById(id);
		
		if(emp.isPresent())
		{
			service.deleteEmployeeById(id);
			
			return ResponseEntity.ok("employee "+id+" deleted successfully");
	
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee id not  found");
		}
		
	}
}
