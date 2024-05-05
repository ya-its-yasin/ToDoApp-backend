package com.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.model.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task,Integer>{

	List<Task> findAllByCreatedBy(int createdBy);
	
}
