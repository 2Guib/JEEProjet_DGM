package com.deguibert.todolist.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.deguibert.todolist.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	
	/**
	 * Returns a user by its login
	 * @param login Login of the desired user
	 * @return User found
	 */
	public User findByLogin(String login);
}
