package com.deguibert.todolist.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.deguibert.todolist.authentication.UserDetailsImpl;
import com.deguibert.todolist.model.User;
import com.deguibert.todolist.repository.UserRepository;
import com.deguibert.todolist.service.TaskService;
import com.deguibert.todolist.service.UserService;

@RestController
public class ListController {
	

	@Autowired
	private TaskService taskService;
	
	/**
	 * Link the list page that shows all tasks of the logged in user
	 * @param userDetail the logged in user
	 * @return the model and view of the list page
	 */
	@GetMapping("/list")
	public ModelAndView viewList(@AuthenticationPrincipal UserDetailsImpl userDetail) {
		ModelAndView modelAndView = new ModelAndView("task/list");
		User user = userDetail.getUser();
		if (user != null) {
			modelAndView.getModelMap().addAttribute("user", user);
			modelAndView.getModelMap().addAttribute("tasks", taskService.getTasks(user));
		}
		return modelAndView;
	}
}
