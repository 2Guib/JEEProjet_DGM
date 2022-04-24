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
	
	/**
	 * Link to the register for page
	 * @return the model and view of the register page
	 */
	@GetMapping("/register")
	public ModelAndView viewRegister() {
		ModelAndView modelAndView = new ModelAndView("form/register");
		modelAndView.getModelMap().addAttribute("user", new User());
		return modelAndView;
	}
	
	/**
	 * Processes the registration of a user
	 * @param user the user to register
	 * @return 
	 */
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
