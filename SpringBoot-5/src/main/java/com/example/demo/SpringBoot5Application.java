package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.Customer;
import com.example.demo.repo.CustomerRepository;

@SpringBootApplication
public class SpringBoot5Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot5Application.class, args);
	}

	@Bean
	public CommandLineRunner setup(CustomerRepository repo) {
		return (args) -> {
			repo.save(new Customer(1, "test1"));
			repo.save(new Customer(2, "test2"));
			repo.save(new Customer(3, "test3"));
			repo.save(new Customer(4, "test4"));
		};
	}
}
