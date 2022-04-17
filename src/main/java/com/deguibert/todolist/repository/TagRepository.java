package com.deguibert.todolist.repository;

import org.springframework.data.repository.CrudRepository;

import com.deguibert.todolist.model.Tag;

public interface TagRepository extends CrudRepository<Tag, Integer>{

}
