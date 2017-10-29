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

import com.example.demo.model.Task;
import com.example.demo.repo.TaskRepository;
import com.example.demo.service.TaskServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class TaskServiceTest {

	@Mock
	private TaskRepository taskRepo;

	@InjectMocks
	private TaskServiceImpl taskService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(taskRepo);
	}

	@Test
	public void test1GetAllTasks() {
		List<Task> tasks = new ArrayList<>();
		tasks.add(new Task(Long.valueOf(1), "Task1"));
		tasks.add(new Task(Long.valueOf(2), "Task2"));
		tasks.add(new Task(Long.valueOf(3), "Task3"));
		tasks.add(new Task(Long.valueOf(4), "Task4"));
		tasks.add(new Task(Long.valueOf(5), "Task5"));

		when(taskRepo.findAll()).thenReturn(tasks);

		List<Task> result = taskService.getAllTasks();
		assertEquals(5, result.size());
	}

	@Test
	public void deleteTaskById() {
		taskService.deleteTaskById(Long.valueOf(5));
		verify(taskRepo, times(1)).delete(Long.valueOf(5));
	}
}
