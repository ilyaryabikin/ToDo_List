package organizer.list.todo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaskController {
    private static final String INDEX_PAGE = "index";
    private static final String TASK_PAGE = "task";

    @GetMapping("/")
    public String getIndexPage() {
        return INDEX_PAGE;
    }

    @GetMapping("/task")
    public String getTaskPage() {
        return TASK_PAGE;
    }
}
