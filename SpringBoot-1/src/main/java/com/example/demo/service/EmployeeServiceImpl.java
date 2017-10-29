package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepositiry;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeRepositiry employeeRepositiry;

	@Override
	public Employee findById(Long id) {
		return employeeRepositiry.findOne(id);
	}

	@Override
	public List<Employee> findAllEmployees() {
		return employeeRepositiry.findAll();
	}

	@Override
	public void saveEmployee(Employee employee) {
		employeeRepositiry.save(employee);
	}

	@Override
	public void updateEmployee(Employee employee) {
		saveEmployee(employee);
	}

	@Override
	public void deleteEmployeeById(Long id) {
		employeeRepositiry.delete(id);
	}

	@Override
	public void deleteAllEmployees() {
		employeeRepositiry.deleteAll();
	}

	@Override
	public boolean isEmployeeExists(Employee employee) {
		// TODO Auto-generated method stub
		return false;
	}

}
