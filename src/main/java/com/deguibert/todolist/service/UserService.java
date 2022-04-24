package com.deguibert.todolist.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deguibert.todolist.model.User;
import com.deguibert.todolist.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * Returns the desired user from the database
	 * @param id the id of the desired user
	 * @return the user found
	 */
	public User getUser(int id) {
		return userRepository.findById(id);
	}
	
	/**
	 * Returns a list of all users in the database
	 * @return the list of all users
	 */
	public List<User> getUsers() {
		return StreamSupport.stream(userRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}
	
	/**
	 * Saves a user in the database
	 * @param user User to save
	 * @return the user actually saved
	 */
	public User saveUser(User user) {
		return userRepository.save(user);
	}
}
