package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.Task;
import com.example.demo.repo.TaskRepository;

@SpringBootApplication
public class SpringBoot4Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot4Application.class, args);
	}

	@Bean
	public CommandLineRunner setup(TaskRepository repository) {
		return (args) -> {
			repository.save(new Task(Long.valueOf(1), "Task1"));
			repository.save(new Task(Long.valueOf(2), "Task2"));
			repository.save(new Task(Long.valueOf(3), "Task3"));
			repository.save(new Task(Long.valueOf(4), "Task4"));
			repository.save(new Task(Long.valueOf(5), "Task5"));
		};
	}
}
