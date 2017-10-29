package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
public class EmployeeRestController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeRestController.class);

	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = "/employee/", method = RequestMethod.POST)
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee, UriComponentsBuilder uriBuilder) {

		logger.debug("Create employee : {}", employee);

		employeeService.saveEmployee(employee);

		HttpHeaders headers = new HttpHeaders();

		headers.setLocation(uriBuilder.path("/EmployeeRestApi/employee/{id}").buildAndExpand(employee.getId()).toUri());

		return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") long id) {
		logger.debug("get employee : {id}", id);
		Employee employee = employeeService.findById(id);
		if (employee == null)
			return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);

	}

	@RequestMapping(value = "/employee/", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getAllEmployees() {
		logger.debug("get all the employees : {}");
		List<Employee> employeeList = employeeService.findAllEmployees();

		return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
	}

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
		logger.debug("Update employee : {}", employee);
		Employee extEmployee = employeeService.findById(id);

		extEmployee.setName(employee.getName());
		extEmployee.setLocation(employee.getLocation());
		extEmployee.setCountry(employee.getCountry());

		employeeService.updateEmployee(extEmployee);

		return new ResponseEntity<Employee>(extEmployee, HttpStatus.OK);
	}

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") long id) {
		logger.debug("delete employee : {}", id);
		employeeService.deleteEmployeeById(id);

		return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/employee/", method = RequestMethod.DELETE)
	public ResponseEntity<Employee> deleteAllEmployee() {
		logger.debug("delete all the employees : {}");
		employeeService.deleteAllEmployees();
		return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
	}

}
