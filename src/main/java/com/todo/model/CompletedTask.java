package com.todo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CompletedTask {
	@Id
	private int taskId;
	private String description;
	private int createdBy;
	private LocalDateTime completedOn;
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDateTime getCompletedOn() {
		return completedOn;
	}
	public void setCompletedOn(LocalDateTime completedOn) {
		this.completedOn = completedOn;
	}
	public CompletedTask(int taskId, String description, int createdBy, LocalDateTime completedOn) {
		super();
		this.taskId = taskId;
		this.description = description;
		this.createdBy = createdBy;
		this.completedOn = completedOn;
	}
	
	public CompletedTask() {
		// TODO Auto-generated constructor stub
	}
	
}
