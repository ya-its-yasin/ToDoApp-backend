package com.todo.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.model.User;
import com.todo.service.UserService;

@CrossOrigin("http://localhost:4200/") //port number of angular application
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
				return new ResponseEntity<>(" { \"body\" : \"Resgistration successfull\" }"
						, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(" { \"body\" : \" " + result + " \"} ", HttpStatus.BAD_REQUEST);
	} 
	
	@PostMapping(path = "/login", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> login(@RequestBody User user){
		int userId = 0;
		if(user!=null && user.getMailId()!=null && user.getPassword()!=null) {
			userId = Userservice.loginUser(user);
			if(userId!=0)
				return new ResponseEntity<>(" { "
					+ " \"body\" :"
					+ " { \"Message\" : \"Login successfull\" ,"
					+ "  \"userId\" : " + userId + " }"
					+ "  } ", HttpStatus.OK);
		}
		return new ResponseEntity<>(" { \"body\" : \" Invalid username or password \"} ", HttpStatus.UNAUTHORIZED);
	}
	
}
