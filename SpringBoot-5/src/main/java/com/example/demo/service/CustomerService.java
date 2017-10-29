package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Customer;

public interface CustomerService {

	List<Customer> getAllCustomers();

	Customer getCustomerById(Integer id);

	Customer saveCustomer(Customer customer);

	Customer updateCustomer(Customer customer);

	void deleteCustomerById(Integer id);

	void deleteAllCustomer();

}
