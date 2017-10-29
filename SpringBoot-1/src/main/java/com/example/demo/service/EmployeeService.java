package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Employee;

public interface EmployeeService {
	Employee findById(Long id);

	List<Employee> findAllEmployees();

	void saveEmployee(Employee employee);

	void updateEmployee(Employee employee);

	void deleteEmployeeById(Long id);

	void deleteAllEmployees();

	boolean isEmployeeExists(Employee employee);
}
