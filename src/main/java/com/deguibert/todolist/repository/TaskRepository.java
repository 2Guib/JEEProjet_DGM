package com.deguibert.todolist.repository;

import org.springframework.data.repository.CrudRepository;

import com.deguibert.todolist.model.Task;

public interface TaskRepository extends CrudRepository<Task, Integer> {
	
}
