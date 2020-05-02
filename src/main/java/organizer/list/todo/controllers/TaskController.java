package organizer.list.todo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import organizer.list.todo.dtos.TaskDetails;
import organizer.list.todo.exceptions.TaskNotFoundException;
import organizer.list.todo.services.TaskService;

import javax.validation.Valid;
import java.util.List;

import static organizer.list.todo.security.PrincipalManager.getCurrentUser;

@Controller
public class TaskController {
    private static final String REDIRECT_TO = "redirect:/";
    private static final String INDEX_PAGE = "index";
    private static final String TASK_PAGE = "task";

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String getIndexPage(Model model) {
        List<TaskDetails> tasks = getCurrentUser()
                .map(taskService::findTaskByUser_Name)
                .orElseThrow(IllegalStateException::new);
        model.addAttribute("tasks", tasks);
        return INDEX_PAGE;
    }

    @GetMapping("/task/{id}")
    public String getTask(@PathVariable("id") Long id, Model model) {
        TaskDetails taskDetails = taskService.findTaskDetailsById(id)
                .orElseThrow(TaskNotFoundException::new);
        model.addAttribute("task", taskDetails);
        return TASK_PAGE;
    }

    @GetMapping("/task/create")
    public String createTaskPage(Model model) {
        model.addAttribute("task", new TaskDetails());
        return TASK_PAGE;
    }

    @PostMapping("/task/create")
    public String createTask(@ModelAttribute("task") @Valid TaskDetails taskDetails,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return TASK_PAGE;
        }

        taskService.save(taskDetails);
        return REDIRECT_TO;
    }

    @GetMapping("/task/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.delete(id);
        return REDIRECT_TO;
    }
}
