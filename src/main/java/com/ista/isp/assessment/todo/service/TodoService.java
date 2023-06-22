package com.ista.isp.assessment.todo.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    private List<String> taskList = new ArrayList<String>();

    public List<String> addTask(String task) {
        getTaskList().add(task);
        return getTaskList();
    }

    public List<String> deleteTask(String task) {
        getTaskList().remove(task);
        return getTaskList();
    }

    public List<String> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<String> taskList) {
        this.taskList = taskList;
    }

}
