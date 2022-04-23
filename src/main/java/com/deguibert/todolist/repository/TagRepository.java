package com.deguibert.todolist.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.deguibert.todolist.model.Tag;

@Repository
public interface TagRepository extends CrudRepository<Tag, Integer>{

}
