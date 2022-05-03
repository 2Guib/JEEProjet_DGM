package com.deguibert.todolist.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deguibert.todolist.model.Tag;
import com.deguibert.todolist.repository.TagRepository;

@Service
public class TagsService {

	@Autowired
	private TagRepository tagRepository;
	
	public List<Tag> getTags() {
		return StreamSupport.stream(tagRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}
}
