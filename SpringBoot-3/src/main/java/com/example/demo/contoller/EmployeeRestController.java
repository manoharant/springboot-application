package com.example.demo.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.EmployeeException;
import com.example.demo.model.Employee;
import com.example.demo.model.Response;
import com.example.demo.service.EmployeeService;

@RestController
public class EmployeeRestController {
	@Autowired
	EmployeeService employeeService;

	@PostMapping(value = "/employee/")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) throws EmployeeException {
		if (employee.getId() != null && employee.getId() > 0) {
			throw new EmployeeException("Id must not be present");
		}
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.OK);
	}

	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id) throws EmployeeException {
		Employee employee = employeeService.findEmployeeById(id);
		if (employee == null) {
			throw new EmployeeException("Emplyee with id :" + id + " is not present");
		}
		return new ResponseEntity<Employee>(employeeService.findEmployeeById(id), HttpStatus.OK);
	}

	@GetMapping("/employee/")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		return new ResponseEntity<List<Employee>>(employeeService.findAllEmployees(), HttpStatus.OK);
	}

	@PutMapping(value = "/employee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee)
			throws EmployeeException {
		Employee extEmployee = employeeService.findEmployeeById(employee.getId());
		if (extEmployee == null) {
			throw new EmployeeException("Emplyee with id :" + id + " is not present");
		}
		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee), HttpStatus.OK);

	}

	@DeleteMapping(value = "/employee/{id}")
	public ResponseEntity<Response> deleteEmployeeById(@PathVariable("id") long id) throws EmployeeException {
		Employee employee = employeeService.findEmployeeById(id);
		if (employee == null) {
			throw new EmployeeException("Emplyee with id :" + id + " is not present");
		}
		employeeService.deleteEmployeeById(id);
		return new ResponseEntity<Response>(
				new Response(HttpStatus.OK.value(), "Emplyee with id:" + id + " is deleted"), HttpStatus.OK);
	}

	@DeleteMapping(value = "/employee/")
	public ResponseEntity<Response> deleteAllEmployees() {
		employeeService.deleteAllEmployees();

		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "All the employyes are deleted"),
				HttpStatus.OK);
	}
}
