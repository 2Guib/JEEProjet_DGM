package com.deguibert.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.deguibert.todolist.model.User;
import com.deguibert.todolist.service.UserService;

@RestController
public class AuthenticationController {

	@Autowired
	private UserService userService;
	
	@Autowired(required = false)
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/register")
	public ModelAndView viewRegister() {
		ModelAndView modelAndView = new ModelAndView("register");
		modelAndView.getModelMap().addAttribute("user", new User());
		return modelAndView;
	}
	
	@PostMapping("/process_register")
	public String processRegister(User user) {
		if (user != null) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userService.saveUser(user);
			return "User "+user.getLogin()+" registered";
		} else {
			return "Invalid User";
		}
		
	}
}
