package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeRepositoryTest {
	@Autowired
	EmployeeRepository employeeRepository;

	@Test
	public void test1SaveEmployee() {
		Employee emp = new Employee(Long.valueOf(5), "Manoharan5", "Frankfurt", "Germany");
		employeeRepository.save(emp);
		Employee result = employeeRepository.findOne(Long.valueOf(5));

		assertEquals(Long.valueOf(5), result.getId());
		assertEquals("Manoharan5", result.getName());
	}

	@Test
	public void test2GetAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		assertEquals(5, employees.size());
	}

	@Test
	public void test3GetEmployeeById() {
		Employee result = employeeRepository.findOne(Long.valueOf(5));
		assertEquals(Long.valueOf(5), result.getId());
		assertEquals("Manoharan5", result.getName());
	}

	@Test
	public void test4DeleteEmployeeById() {
		employeeRepository.delete(Long.valueOf(5));
		Employee result = employeeRepository.findOne(Long.valueOf(5));
		assertTrue(result == null);

	}
}
