package com.deguibert.todolist.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deguibert.todolist.model.Task;
import com.deguibert.todolist.model.User;
import com.deguibert.todolist.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	/**
	 * Returns the desired tast from the database
	 * @param id the id of the desired tast
	 * @return the tast found
	 */
	public Task getTask(int id) {
		return taskRepository.findById(id);
	}
	
	/**
	 * Returns a list of all tasks corresponding to the user
	 * @param user the author of the tasks
	 * @return the list of all users
	 */
	public Iterable<Task> getTasks(User user) {
		return taskRepository.findAllByUser(user);
	}

	/**
	 * Create or update a task in the database
	 * @param task to update
	 * @return the actual task saved
	 */
	public Task updateTask(Task task) {
		task.setLast_change(new Date());
		return taskRepository.save(task);
	}
	
	
}
