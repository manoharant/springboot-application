package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Employee;

public interface EmployeeService {

	List<Employee> findAllEmployees();

	Employee findEmployeeById(Long id);

	Employee saveEmployee(Employee employee);

	Employee updateEmployee(Employee employee);

	void deleteEmployeeById(Long id);

	void deleteAllEmployees();

}
