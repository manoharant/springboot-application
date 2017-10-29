package com.example.demo.controller;

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

import com.example.demo.exception.CustomerException;
import com.example.demo.model.Customer;
import com.example.demo.model.DefaultResponse;
import com.example.demo.service.CustomerService;

@RestController
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@GetMapping(value = "/customer/")
	public ResponseEntity<List<Customer>> getAllCustomer() {
		return new ResponseEntity<List<Customer>>(customerService.getAllCustomers(), HttpStatus.OK);
	}

	@GetMapping(value = "/customer/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("id") int id) throws CustomerException {
		Customer customer = customerService.getCustomerById(id);
		if (customer == null)
			throw new CustomerException("Customer with " + id + " does not exists");
		return new ResponseEntity<Customer>(customerService.getCustomerById(id), HttpStatus.OK);
	}

	@PostMapping(value = "/customer/")
	public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) throws CustomerException {
		if (customer.getCustId() != null && customer.getCustId() > 0)
			throw new CustomerException("Customer id with must not present");

		return new ResponseEntity<Customer>(customerService.saveCustomer(customer), HttpStatus.CREATED);
	}

	@PutMapping(value = "/customer/")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) throws CustomerException {
		Customer customerRes = customerService.getCustomerById(customer.getCustId());
		if (customerRes == null)
			throw new CustomerException("Customer with " + customer.getCustId() + " does not exists");

		return new ResponseEntity<Customer>(customerService.updateCustomer(customer), HttpStatus.OK);
	}

	@DeleteMapping(value = "/customer/{id}")
	public ResponseEntity<DefaultResponse> deleteCustomerById(@PathVariable("id") int id) throws CustomerException {
		Customer customer = customerService.getCustomerById(id);
		if (customer == null)
			throw new CustomerException("Customer with " + id + " does not exists");

		customerService.deleteCustomerById(id);
		return new ResponseEntity<DefaultResponse>(
				new DefaultResponse(HttpStatus.NO_CONTENT.value(), "Customer with id: " + id + " is deleted"),
				HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(value = "/customer/")
	public ResponseEntity<DefaultResponse> deleteAllCustomer() {
		customerService.deleteAllCustomer();
		return new ResponseEntity<DefaultResponse>(
				new DefaultResponse(HttpStatus.NO_CONTENT.value(), "All the customers are deleted"),
				HttpStatus.NO_CONTENT);
	}
}
