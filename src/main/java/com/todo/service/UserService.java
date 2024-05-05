package com.todo.service;

import org.springframework.stereotype.Service;

import com.todo.model.User;

@Service
public interface UserService {

	String createUser(User user);

	boolean loginUser(User user);
	
}
