package com.deguibert.todolist.repository;

import org.springframework.data.repository.CrudRepository;

import com.deguibert.todolist.model.User;

public interface UserRepository extends CrudRepository<User, Integer>{

}
