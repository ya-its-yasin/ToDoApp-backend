package com.todo.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.model.CompletedTask;
import com.todo.model.Task;
import com.todo.service.TaskService;

@CrossOrigin("http://localhost:4200/") //port number of angular application
@RestController
public class TaskController {

	@Autowired
	TaskService taskService;
	
	@PostMapping(path = "/addTask", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addTask(@RequestBody Task task){
		int taskId = 0;
		if(task!=null) {
			taskId = taskService.addTask(task);
			if(taskId!=0) {
				return new ResponseEntity<>(" { "
						+ " \"body\" :"
						+ " { \"Message\" : \"Task added successfull\" ,"
						+ "  \"taskId\" : " + taskId + " }"
						+ "  } ", HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(" { \"body\" : \" Expectation Failed \"} ", HttpStatus.EXPECTATION_FAILED);
	}
	
	@GetMapping("/getTasks/{userId}")
	public ResponseEntity<?> getTasks(@PathVariable int userId){
		List<Task> tasks = taskService.getTasks(userId);
		return new ResponseEntity<>(tasks, HttpStatus.OK);
	}
	
	@PutMapping("/updateTask")
	public ResponseEntity<?> updateTask(@RequestBody Task updatedTask){
		var result = taskService.updateTask(updatedTask);
		if(result instanceof Task) {
			return new ResponseEntity<>((Task)result, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(" { \"body\" : \" " + result + " \"} ", HttpStatus.OK);
		}
		
	}
	
	@DeleteMapping("/finish/{taskId}")
	public ResponseEntity<?> finishTask(@PathVariable int taskId){
		String result = taskService.finishTask(taskId);
		if(result == "success") {
			return new ResponseEntity<>(" { \"body\" : \"Task marked as completed\"} ", HttpStatus.OK);
		}else {
			return new ResponseEntity<>(" { \"body\" : \" " + result + " \"} ", HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@GetMapping("/ctasks/{userId}")
	public ResponseEntity<?> getCTasks(@PathVariable int userId){
		List<CompletedTask> tasks = taskService.getCTasks(userId);
		return new ResponseEntity<>(tasks, HttpStatus.OK);
	}
	
}
