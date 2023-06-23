package com.ista.isp.assessment.todo.service;

import com.ista.isp.assessment.todo.model.Todo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class TodoServiceTest {

    @InjectMocks
    TodoService todoService;

    @Test
    public void givenEmptyList_whenOneTaskAdded_ThenListSizeIsGreaterThan0() {
        Todo task = new Todo("FirstTask", false);
        Assert.assertTrue(todoService.addTask(task).size() > 0);
    }

    @Test
    public void givenAList_whenOneTaskAdded_ThenListSizeIncrease() {
        ArrayList<Todo> taskList = new ArrayList<Todo>(Arrays.asList(
                new Todo("FirstTask", false), new Todo("SecondTask", false)));
        todoService.setTaskList(taskList);
        int initialTaskListSize = todoService.getTaskList().size();
        todoService.addTask(new Todo("LastTask", false));
        Assert.assertTrue((todoService.getTaskList().size() - initialTaskListSize) > 0);
    }

    @Test
    public void givenAList_whenGetAll_ThenReturnTheList() {
        ArrayList<Todo> taskList = new ArrayList<Todo>(Arrays.asList(
                new Todo("FirstTask", false), new Todo("SecondTask", false)));
        todoService.setTaskList(taskList);
        Assert.assertTrue((todoService.getTaskList().size()) > 0);
    }

    @Test
    public void givenEmptyList_whenGetAll_ThenReturnEmptyList() {
        List<Todo> taskList = new ArrayList<Todo>();
        todoService.setTaskList(taskList);
        Assert.assertFalse((todoService.getTaskList().size()) > 0);
    }

    @Test
    public void givenAList_whenDeleteTask_ThenReturnRestOfList() {
        ArrayList<Todo> taskList = new ArrayList<Todo>(Arrays.asList(
                new Todo(11, "FirstTask", false), new Todo(12, "SecondTask", false),
                new Todo(13, "ThirdTask", false)));
        List<Todo> taskList1 = new ArrayList<Todo>();
        todoService.getTaskList().clear();
        todoService.setTaskList(taskList);
        todoService.deleteTask(new Todo(12, "SecondTask", false));
        Assert.assertTrue((todoService.getTaskList().size()) == 2);
    }

    @Test
    public void givenEmptyList_whenDeleteTask_ThenReturnEmptyList() {
        List<Todo> taskList = new ArrayList<Todo>();
        todoService.setTaskList(taskList);
        todoService.deleteTask(new Todo("SecondTask", false));
        Assert.assertFalse((todoService.getTaskList().size()) > 0);
    }
}
