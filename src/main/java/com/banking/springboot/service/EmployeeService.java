package com.banking.springboot.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.banking.springboot.model.Employee;

@Component
public interface EmployeeService {
	List<Employee> getAllEmployees();
	Employee getEmployeeById(Long id);
	Employee saveEmployee(Employee employee);

}
