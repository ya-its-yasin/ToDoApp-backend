package com.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.model.CompletedTask;

@Repository
public interface CompletedTaskRepo extends JpaRepository<CompletedTask, Integer> {

	List<CompletedTask> findAllByCreatedBy(int userId);

}
