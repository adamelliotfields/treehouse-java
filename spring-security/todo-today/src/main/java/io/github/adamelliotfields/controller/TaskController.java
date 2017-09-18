package io.github.adamelliotfields.controller;

import io.github.adamelliotfields.dto.TaskDto;
import io.github.adamelliotfields.entity.Task;
import io.github.adamelliotfields.entity.User;
import io.github.adamelliotfields.service.TaskService;
import io.github.adamelliotfields.service.UserService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TaskController {
  private TaskService taskService;
  private UserService userService;

  @Autowired
  public TaskController(TaskService taskService, UserService userService) {
    this.taskService = taskService;
    this.userService = userService;
  }

  @RequestMapping(path = {"", "/"}, method = RequestMethod.GET)
  public String getIndex() {
    return "redirect:/tasks";
  }

  @RequestMapping(path = {"/tasks", "/tasks/"}, method = RequestMethod.GET)
  public String getTasks(Model model, Principal principal) {
    final String username = principal.getName();

    Iterable<Task> tasks = taskService.findAllByUsername(username);
    TaskDto taskDto = new TaskDto();

    model.addAttribute("tasks", tasks);
    model.addAttribute("taskDto", taskDto);

    return "tasks";
  }

  @RequestMapping(path = "/tasks", method = RequestMethod.POST)
  public String postTasks(@ModelAttribute TaskDto taskDto, Principal principal) {
    // Advanced way using the username from context
    // Saves a database call
    // User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
    String username = principal.getName();
    User user = userService.findByUsername(username);
    Task task = new Task(taskDto, user);
    taskService.save(task);

    return "redirect:/tasks";
  }

  @RequestMapping(path = "/tasks/{id}", method = RequestMethod.POST)
  public String postTaskId(@PathVariable String id) {
    Task task = taskService.findOne(id);
    taskService.toggleComplete(id);

    return "redirect:/tasks";
  }
}
