package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@SpringBootApplication
public class SpringBoot2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot2Application.class, args);
	}

	@Bean
	public CommandLineRunner setup(EmployeeRepository employeeRepository) {
		return (args) -> {
			employeeRepository.save(new Employee(new Long(1), "Manoharan", "Frankfurt", "Germany"));
			employeeRepository.save(new Employee(new Long(2), "Manoharan2", "Frankfurt", "Germany"));
			employeeRepository.save(new Employee(new Long(3), "Manoharan3", "Frankfurt", "Germany"));
			employeeRepository.save(new Employee(new Long(4), "Manoharan4", "Frankfurt", "Germany"));
			employeeRepository.save(new Employee(new Long(5), "Manoharan5", "Frankfurt", "Germany"));
		};

	}
}
