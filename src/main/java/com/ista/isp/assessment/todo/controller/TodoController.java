package com.ista.isp.assessment.todo.controller;


import com.ista.isp.assessment.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ista")
public class TodoController {

    @Autowired
    TodoService todoService;

    @PostMapping("tasks/{task}")
    public List<String> addTask(@PathVariable("task") String task) {
        return todoService.addTask(task);
    }

    @GetMapping("tasks")
    public List<String> GetAllTasks() {
        return todoService.getTaskList();
    }

    @DeleteMapping("tasks/{task}")
    public List<String> deleteTask(@PathVariable("task") String task) {
        return todoService.deleteTask(task);
    }

}
