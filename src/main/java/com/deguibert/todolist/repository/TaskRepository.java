package com.deguibert.todolist.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.deguibert.todolist.model.Task;
import com.deguibert.todolist.model.User;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {
	
	/**
	 * Returns a task by its id
	 * @param id Id of the desired task
	 * @return Task found
	 */
	public Task findById(int id);
	
	/**
	 * Returns all tasks of a specific user
	 * @param id_user id of the user
	 * @return tasks found
	 */
	public List<Task> findAllByUser(User user);
}
