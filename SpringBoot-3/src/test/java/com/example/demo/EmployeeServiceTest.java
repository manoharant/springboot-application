package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeServiceTest {

	@Mock
	private EmployeeRepository repository;

	@InjectMocks
	private EmployeeServiceImpl employeeService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(repository);
	}

	@Test
	public void testGetAllEmployees() {
		List<Employee> employees = new ArrayList<>();

		employees.add(new Employee(Long.valueOf(1), "Manoharan1", "Frankfurt", "Germany"));
		employees.add(new Employee(Long.valueOf(2), "Manoharan2", "Frankfurt", "Germany"));
		employees.add(new Employee(Long.valueOf(3), "Manoharan3", "Frankfurt", "Germany"));
		employees.add(new Employee(Long.valueOf(4), "Manoharan4", "Frankfurt", "Germany"));

		when(repository.findAll()).thenReturn(employees);

		List<Employee> result = employeeService.findAllEmployees();

		assertEquals(result.size(), 4);
	}
}
