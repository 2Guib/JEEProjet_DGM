package com.deguibert.todolist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class IndexController {

	
	@GetMapping("/")
	public RedirectView viewIndex() {
		return new RedirectView("/list");
	}
}
