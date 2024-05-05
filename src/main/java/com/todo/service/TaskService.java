package com.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.todo.model.CompletedTask;
import com.todo.model.Task;

@Service
public interface TaskService {

	String addTask(Task task);

	List<Task> getTasks(int userId);

	Object updateTask(Task updatedTask);

	String finishTask(int taskId);

	List<CompletedTask> getCTasks(int userId);

}
