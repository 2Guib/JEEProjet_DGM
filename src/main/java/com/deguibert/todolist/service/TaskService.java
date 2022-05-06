package com.deguibert.todolist.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

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
	 * @return the list of tasks of the user
	 */
	public List<Task> getTasks(User user) {
		List<Task> tasks = taskRepository.findAllByUser(user);
		Collections.sort(tasks);
		return tasks;
	}
	
	/**
	 * Returns a list of all tasks corresponding to the user and filters it by query
	 * @param user the author of the tasks
	 * @param query query for the title of the task
	 * @param begin min creation date
	 * @param end max creation date
	 * @return the list of tasks of the user filtered by name and date
	 */
	public List<Task> getTasks(User user, String query, Date begin, Date end, int[] qtags) {
		List<Task> tasks = this.getTasks(user);
		return filterTasks(tasks, query, begin, end, qtags);
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
	
	private List<Task> filterTasks(List<Task> tasks, String query, Date begin, Date end, int[] qtags) {
		if (query != null) {
			tasks.removeIf(t -> !t.getTitle().toUpperCase().contains(query.toUpperCase()));
		}
		if (begin != null) {
			tasks.removeIf(t -> t.getCreation().before(begin));
		}
		if (end != null) {
			tasks.removeIf(t -> t.getCreation().after(end));
		}
		if (qtags != null) {
			tasks.removeIf(t -> !t.containsOneTag(qtags));
		}
		return tasks;
	}
	
	
}
