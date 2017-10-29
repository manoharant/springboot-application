package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.TaskException;
import com.example.demo.model.DefaultResponse;
import com.example.demo.model.Task;
import com.example.demo.service.TaskService;

@RestController
public class TaskController {
	@Autowired
	private TaskService taskService;

	@GetMapping(value = "/task/")
	public ResponseEntity<List<Task>> getAllTasks() {
		return new ResponseEntity<List<Task>>(taskService.getAllTasks(), HttpStatus.OK);
	}

	@GetMapping(value = "/task/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable("id") long id) throws TaskException {
		Task task = taskService.getTaskById(id);
		if (task == null) {
			throw new TaskException("Task with id:" + id + "does not exists");
		}
		return new ResponseEntity<Task>(taskService.getTaskById(id), HttpStatus.OK);
	}

	@PostMapping(value = "/task/")
	public ResponseEntity<Task> saveTask(@RequestBody Task task) throws TaskException {

		if (task.getTaskId() != null && task.getTaskId() > 0)
			throw new TaskException("Task id should not present");
		return new ResponseEntity<Task>(taskService.saveTask(task), HttpStatus.OK);
	}

	@PatchMapping(value = "/task/")
	public ResponseEntity<Task> updateTask(@RequestBody Task task) {
		return new ResponseEntity<Task>(taskService.updateTask(task), HttpStatus.OK);
	}

	@DeleteMapping(value = "/task/")
	public ResponseEntity<DefaultResponse> deleteAllTask() {
		taskService.deleteAllTask();
		return new ResponseEntity<DefaultResponse>(
				new DefaultResponse(HttpStatus.OK.value(), "All the taks are deleted"), HttpStatus.OK);
	}

	@DeleteMapping(value="/task/{id}")
	public ResponseEntity<DefaultResponse> deleteTaskById(@PathVariable("id") long id) throws TaskException {
		Task task = taskService.getTaskById(id);
		if (task == null)
			throw new TaskException("Task with id:" + id + "does not exists");
		taskService.deleteTaskById(id);
		return new ResponseEntity<DefaultResponse>(
				new DefaultResponse(HttpStatus.OK.value(), "Task with id:" + id + "is deleted"), HttpStatus.OK);
	}

}
