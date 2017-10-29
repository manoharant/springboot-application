package com.example.demo;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.model.Task;
import com.example.demo.repo.TaskRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class TaskRepoTest {
	@Autowired
	private TaskRepository taskRepo;

	@Test
	public void testGetAllTasks() {

		List<Task> tasks = taskRepo.findAll();

		assertEquals(5, tasks.size());

	}
}
