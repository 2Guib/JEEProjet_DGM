package com.deguibert.todolist.service;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
	public List<Task> getTasks(User user) {
		List<Task> tasks = taskRepository.findAllByUser(user);
		Collections.sort(tasks);
		return tasks;
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
	
	/**
	 * Switch if the task is done or not
	 * @param id id of the task to update 
	 * @return the actual task updated
	 */
	public Task switchTaskDone(int id) {
		Task t = this.getTask(id);
		if(t != null) {
			t.setDone(!t.isDone());
			t.setClose_date(t.isDone() ? new Date() : null);
			t = this.updateTask(t);
		}
		return t;
	}
	
}
