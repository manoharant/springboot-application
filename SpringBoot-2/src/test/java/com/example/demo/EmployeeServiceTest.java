package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
	private EmployeeRepository employeeRepository;
	@InjectMocks
	private EmployeeServiceImpl employeeService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(employeeRepository);
	}

	@Test
	public void testGetAllEmployees() {
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee(Long.valueOf(1), "Manoharan1", "Frankfurt", "Germany"));
		employees.add(new Employee(Long.valueOf(2), "Manoharan2", "Frankfurt", "Germany"));
		employees.add(new Employee(Long.valueOf(3), "Manoharan3", "Frankfurt", "Germany"));
		employees.add(new Employee(Long.valueOf(4), "Manoharan4", "Frankfurt", "Germany"));

		when(employeeRepository.findAll()).thenReturn(employees);

		List<Employee> result = employeeService.findAllEmployees();
		assertEquals(result.size(), 4);
	}

	@Test
	public void testGetEmployeeById() {
		Employee emp = new Employee(Long.valueOf(5), "Manoharan5", "Frankfurt", "Germany");

		when(employeeRepository.findOne(Long.valueOf(5))).thenReturn(emp);

		Employee result = employeeService.findEmployeeById(Long.valueOf(5));
		assertEquals(Long.valueOf(5), result.getId());
		assertEquals("Manoharan5", result.getName());
	}

	@Test
	public void testSaveEmployee() {
		Employee emp = new Employee(Long.valueOf(6), "Manoharan6", "Frankfurt", "Germany");
		when(employeeRepository.save(emp)).thenReturn(emp);

		Employee result = employeeService.saveEmployee(emp);
		assertEquals(Long.valueOf(6), result.getId());
		assertEquals("Manoharan6", result.getName());
	}

	@Test
	public void testDeletEmployeeById() {
		employeeService.deleteEmployeeById(Long.valueOf(6));
		verify(employeeRepository, times(1)).delete(Long.valueOf(6));
	}
}
