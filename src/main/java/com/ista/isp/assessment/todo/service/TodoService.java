package com.ista.isp.assessment.todo.service;

import com.ista.isp.assessment.todo.model.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private List<Todo> taskList = new ArrayList<Todo>();

    public List<Todo> addTask(Todo task) {
        Todo item =  new Todo(task.getTitle(),task.isComplete());
        getTaskList().add(item);
        return getTaskList();
    }

    public List<Todo> deleteTask(Todo task) {
        getTaskList().removeIf(t -> t.getId() == task.getId());
        return getTaskList();
    }

    public List<Todo> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Todo> taskList) {
        this.taskList = taskList;
    }

    public List<Todo> updateTask(Todo task){
        this.taskList.stream().filter( t-> t.getId()==(task.getId())).findFirst().ifPresent(t->t.setComplete(task.isComplete()));
        return getTaskList();
    }
}
