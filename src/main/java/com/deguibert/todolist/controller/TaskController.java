package com.deguibert.todolist.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.deguibert.todolist.authentication.UserDetailsImpl;
import com.deguibert.todolist.model.Task;
import com.deguibert.todolist.service.TagsService;
import com.deguibert.todolist.service.TaskService;

@RestController
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private TagsService tagsService;
	
	@GetMapping("/create-task")
	public ModelAndView viewCreateTask(@AuthenticationPrincipal UserDetailsImpl userDetail) {
		ModelAndView modelAndView = new ModelAndView("task/update-task");
		modelAndView.getModelMap().addAttribute("task", new Task());
		modelAndView.getModelMap().addAttribute("title", "Créer une tache");
		modelAndView.getModelMap().addAttribute("tags", tagsService.getTags());
		return modelAndView;
	}
	
	@GetMapping("/update-task")
	public ModelAndView viewUpdateTask(@AuthenticationPrincipal UserDetailsImpl userDetail, @RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView("task/update-task");
		Task task = taskService.getTask(id);
		if (task != null) {
			modelAndView.getModelMap().addAttribute("task", task);
			modelAndView.getModelMap().addAttribute("title", "Mettre a jour une tache");
			modelAndView.getModelMap().addAttribute("tags", tagsService.getTags());
		} else {
			modelAndView.setView(new RedirectView("/create-task", true));
		}
		return modelAndView;
	}
	
	@PostMapping("/process_update_task")
	public RedirectView processUpdateTask(@AuthenticationPrincipal UserDetailsImpl userDetail, Task task) {
		if (task.getCreation() == null) {
			task.setCreation(new Date());
		}
		task.setUser(userDetail.getUser());
		taskService.updateTask(task);
		return new RedirectView("/list", true);
	}
	
	@PostMapping("/process_switch_done")
	public RedirectView processUpdateTask(@AuthenticationPrincipal UserDetailsImpl userDetail, @RequestParam int id) {
		taskService.switchTaskDone(id);
		return new RedirectView("/list", true);
	}
}
