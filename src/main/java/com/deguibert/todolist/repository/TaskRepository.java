package com.deguibert.todolist.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.deguibert.todolist.model.Task;
import com.deguibert.todolist.model.User;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {
	
	/**
	 * Returns all tasks of a specific user
	 * @param id_user id of the user
	 * @return tasks found
	 */
	public Iterable<Task> findAllByUser(User user);
}
