package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@SpringBootApplication
public class SpringBoot3Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot3Application.class, args);
	}

	@Bean
	public CommandLineRunner setup(EmployeeRepository repository) {
		return (args) -> {
			repository.save(new Employee(Long.valueOf(1), "Manoharan1", "Frankfurt", "Gernamy"));
			repository.save(new Employee(Long.valueOf(2), "Manoharan2", "Frankfurt", "Gernamy"));
			repository.save(new Employee(Long.valueOf(3), "Manoharan3", "Frankfurt", "Gernamy"));
			repository.save(new Employee(Long.valueOf(4), "Manoharan4", "Frankfurt", "Gernamy"));
		};
	}
}
