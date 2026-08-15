package com.rrr.esb4ss7;

import com.rrr.esb4ss7.domain.Task;
import com.rrr.esb4ss7.service.TaskService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String listTasks(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("tasks", taskService.findTasksForUser(userDetails.getUsername()));
        return "tasks/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("task", new Task());
        return "tasks/form";
    }

    @PostMapping("/new")
    public String createTask(Task task, @AuthenticationPrincipal UserDetails userDetails) {
        taskService.createTask(task, userDetails.getUsername());
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Task task = taskService.findTaskById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        if (!task.getUser().getUsername().equals(userDetails.getUsername())) {
            throw new SecurityException("Access Denied");
        }
        model.addAttribute("task", task);
        return "tasks/form";
    }

    @PostMapping("/edit/{id}")
    public String updateTask(@PathVariable Long id, Task task, @AuthenticationPrincipal UserDetails userDetails) {
        Task existingTask = taskService.findTaskById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        if (!existingTask.getUser().getUsername().equals(userDetails.getUsername())) {
            throw new SecurityException("Access Denied");
        }
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setStatus(task.getStatus());
        taskService.updateTask(existingTask);
        return "redirect:/tasks";
    }

    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        taskService.deleteTask(id, userDetails.getUsername());
        return "redirect:/tasks";
    }
}