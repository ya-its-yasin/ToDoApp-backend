package com.todo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.todo.model.CompletedTask;
import com.todo.model.Task;

@Service
public interface TaskService {

	int addTask(Task task);

	List<Task> getTasks(int userId);

	Object updateTask(Task updatedTask);

	String finishTask(int taskId);

	List<CompletedTask> getCTasks(int userId);

	Optional<Task> getTask(int userId, String desc);

}
