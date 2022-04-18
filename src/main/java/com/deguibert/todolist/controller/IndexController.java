package com.deguibert.todolist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IndexController {

	
	@GetMapping("/")
	public ModelAndView viewIndex() {
		ModelAndView modelAndView = new ModelAndView("index");
		return modelAndView;
	}
}
