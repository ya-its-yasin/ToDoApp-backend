package com.todo.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.model.User;
import com.todo.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService Userservice;
	
	@GetMapping("/")
	public String greet() {
		return "Hello world";
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> createUser(@RequestBody User user){
		String result = "Failed";
		if(user.getUserName()!=null && user.getPassword()!=null && user.getMailId()!=null) {
			result = Userservice.createUser(user);
			if(result =="success") {
				return new ResponseEntity<>(" { \"body\" : \"Resgistration successfull\"} ", HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(" { \"body\" : \" " + result + " \"} ", HttpStatus.UNAUTHORIZED);
	} 
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user){
		if(user!=null && Userservice.loginUser(user)) {
			return new ResponseEntity<>(" { \"body\" : \"Login successfull\"} ", HttpStatus.OK);
		}
		return new ResponseEntity<>(" { \"body\" : \" Invalid username or password \"} ", HttpStatus.UNAUTHORIZED);
	}
	
}
