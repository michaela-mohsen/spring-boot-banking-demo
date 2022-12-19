package com.banking.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.banking.springboot.model.Employee;

@Component
public interface EmployeeService {
	List<Employee> getAllEmployees();

	Employee getEmployeeById(Integer id);

	Employee getEmployeeByEmail(String email);

	Employee saveEmployee(Employee employee);

	Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
