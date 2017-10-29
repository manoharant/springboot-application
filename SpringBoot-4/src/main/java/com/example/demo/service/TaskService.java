package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Task;

public interface TaskService {

	List<Task> getAllTasks();

	Task getTaskById(Long id);

	Task saveTask(Task task);

	Task updateTask(Task task);

	void deleteAllTask();

	void deleteTaskById(Long id);

}
