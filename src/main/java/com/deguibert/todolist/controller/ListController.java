package com.deguibert.todolist.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.deguibert.todolist.authentication.UserDetailsImpl;
import com.deguibert.todolist.model.User;
import com.deguibert.todolist.service.TagsService;
import com.deguibert.todolist.service.TaskService;

@RestController
public class ListController {
	

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private TagsService tagsService;
	
	/**
	 * Link the list page that shows all tasks of the logged in user
	 * @param userDetail the logged in user
	 * @param query query for the title of the task
	 * @param begin min creation date
	 * @param end max creation date
	 * @param qtags list of tags id for query
	 * @return the model and view of the list page
	 */
	@GetMapping("/list")
	public ModelAndView viewList(@AuthenticationPrincipal UserDetailsImpl userDetail, 
			@RequestParam(required = false) String query, 
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date begin, 
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date end,
			@RequestParam(required = false) int[] qtags) {
		ModelAndView modelAndView = new ModelAndView("task/list");
		User user = userDetail.getUser();
		System.out.println(qtags !=null ? qtags[0] : "null");
		if (user != null) {
			modelAndView.getModelMap().addAttribute("user", user);
			modelAndView.getModelMap().addAttribute("tasks", taskService.getTasks(user, query, begin, end, qtags));
			modelAndView.getModelMap().addAttribute("tags", tagsService.getTags());
		}
		return modelAndView;
	}
}
