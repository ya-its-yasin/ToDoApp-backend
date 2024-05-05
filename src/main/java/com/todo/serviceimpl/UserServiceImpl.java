package com.todo.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.todo.model.User;
import com.todo.repository.UserRepo;
import com.todo.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepo userRepo;
	
	@Override
	public String createUser(User user) {
		String result = "success";
		User newUser = new User(user.getMailId(), user.getUserName(), user.getPassword());
		try{
			userRepo.save(newUser);
		}catch(DataIntegrityViolationException e) {
			result = "Mail id already exist, please try loggin in";
		}
		return result;
	}

	@Override
	public boolean loginUser(User user) {
		User validUser = userRepo.findByMailId(user.getMailId());
		if(validUser!=null && validUser.getPassword().equals(user.getPassword())) {
			return true;
		}
		return false;
	}

}
