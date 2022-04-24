package com.deguibert.todolist.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.deguibert.todolist.authentication.UserDetailsImpl;
import com.deguibert.todolist.model.Task;
import com.deguibert.todolist.model.User;
import com.deguibert.todolist.repository.TaskRepository;
import com.deguibert.todolist.service.TaskService;

@RestController
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping("/create-task")
	public ModelAndView viewCreateTask(@AuthenticationPrincipal UserDetailsImpl userDetail) {
		ModelAndView modelAndView = new ModelAndView("form/update-task");
		modelAndView.getModelMap().addAttribute("task", new Task());
		modelAndView.getModelMap().addAttribute("title", "Créer une tache");
		return modelAndView;
	}
	
	@GetMapping("/update-task")
	public ModelAndView viewUpdateTask(@AuthenticationPrincipal UserDetailsImpl userDetail, @RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView("form/update-task");
		Task task = taskService.getTask(id);
		if (task != null) {
			modelAndView.getModelMap().addAttribute("task", task);
			modelAndView.getModelMap().addAttribute("title", "Mettre a jour une tache");
		} else {
			modelAndView.setView(new RedirectView("/create-task"));
		}
		return modelAndView;
	}
	
	@PostMapping("/process_update_task")
	public String processUpdateTask(@AuthenticationPrincipal UserDetailsImpl userDetail, Task task) {
		task.setUser(userDetail.getUser());
		task = taskService.updateTask(task);
		if (task == null) {
			return "Echec de sauvegarde de tache";
		} else {
			return "Tache " + task.getTitle() + "sauvegardée";
		}
	}
}
