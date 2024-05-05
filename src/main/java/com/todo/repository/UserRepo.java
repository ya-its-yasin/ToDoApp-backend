package com.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.model.User;

@Repository
public interface UserRepo extends JpaRepository<User,Integer>{

	User findByMailId(String mailId);

}
