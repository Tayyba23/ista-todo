package com.ista.isp.assessment.todo.controller;


import com.ista.isp.assessment.todo.model.Todo;
import com.ista.isp.assessment.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("ista/tasks")
public class TodoController {

    @Autowired
    TodoService todoService;

    @PostMapping("")
    public List<Todo> addTask(@RequestBody Todo todoTask) {
        return todoService.addTask(todoTask);
    }

    @GetMapping("")
    public List<Todo> GetAllTasks() {
        return todoService.getTaskList();
    }

    @DeleteMapping("")
    public List<Todo> deleteTask(@RequestBody Todo task) {
        return todoService.deleteTask(task);
    }

    @PutMapping("")
    public List<Todo> updateTask(@RequestBody Todo task) {
        return todoService.updateTask(task);
    }
}
