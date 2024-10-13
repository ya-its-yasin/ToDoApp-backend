package com.todo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.todo.model.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task,Integer>{

	List<Task> findAllByCreatedBy(int createdBy);

	@Query(value = "SELECT * FROM task t WHERE t.created_by = ?1 AND t.description = ?2", nativeQuery = true)
	Optional<Task> findByUserIdAndDesc(int userId, String desc);
	
}
