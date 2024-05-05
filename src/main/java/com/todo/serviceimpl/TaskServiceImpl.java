package com.todo.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.todo.model.CompletedTask;
import com.todo.model.Task;
import com.todo.model.User;
import com.todo.repository.CompletedTaskRepo;
import com.todo.repository.TaskRepo;
import com.todo.repository.UserRepo;
import com.todo.service.TaskService;
import com.todo.service.UserService;

@Service
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	TaskRepo taskRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	CompletedTaskRepo completedTaskRepo;
	
	@Override
	public String addTask(Task task) {
		Optional<User> validUser = userRepo.findById(task.getCreatedBy());
		if(validUser.isPresent()) {
			Task newTask = new Task(task.getDescription(), task.getCreatedBy(), "active");
			try{
				taskRepo.save(newTask);
				return "success";
			}catch(DataIntegrityViolationException e) {
				return "Task already present and active";
			}
		}
		return "User does not exist";
	}

	@Override
	public List<Task> getTasks(int userId) {
		List<Task> tasks = taskRepo.findAllByCreatedBy(userId);
		return tasks;
	}

	@Override
	public Object updateTask(Task updatedTask) {
		Task task = taskRepo.findById(updatedTask.getTaskId()).get();
		task.setDescription(updatedTask.getDescription());
		try{
			taskRepo.save(task);
			return task;
		}catch(DataIntegrityViolationException e) {
			return "Task already present and active";
		}
	}

	@Override
	public String finishTask(int taskId) {
		Task task = taskRepo.findById(taskId).get();
		CompletedTask ctask = new CompletedTask(task.getTaskId(), task.getDescription(), task.getCreatedBy(), LocalDateTime.now());
		try{
			completedTaskRepo.save(ctask);
			taskRepo.delete(task);
			return "success";
		}catch(Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public List<CompletedTask> getCTasks(int userId) {
		List<CompletedTask> tasks = completedTaskRepo.findAllByCreatedBy(userId);
		return tasks;
	}
}
