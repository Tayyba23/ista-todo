package com.ista.isp.assessment.todo.service;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
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
        Assert.assertTrue(todoService.addTask("FirstTask").size()>0);
    }

    @Test
    public void givenAList_whenOneTaskAdded_ThenListSizeIncrease() {
        List<String> taskList = new ArrayList<String>( Arrays.asList("FirstTask", "SecondTask"));
        todoService.setTaskList(taskList);
        int initialTaskListSize = todoService.getTaskList().size();
        todoService.addTask("Last Task");
        Assert.assertTrue((initialTaskListSize-todoService.getTaskList().size() )>0);
    }

    @Test
    public void givenAList_whenGetAll_ThenReturnTheList() {
        List<String> taskList = new ArrayList<String>( Arrays.asList("FirstTask", "SecondTask"));
        todoService.setTaskList(taskList);
        Assert.assertTrue((todoService.getTaskList().size() )>0);
    }

    @Test
    public void givenEmptyList_whenGetAll_ThenReturnEmptyList() {
        List<String> taskList = new ArrayList<String>();
        todoService.setTaskList(taskList);
        Assert.assertFalse((todoService.getTaskList().size())>0);
    }

    @Test
    public void givenAList_whenDeleteTask_ThenReturnRestOfList() {
        List<String> taskList = new ArrayList<String>( Arrays.asList("FirstTask", "SecondTask","thirdTask"));
        todoService.setTaskList(taskList);
        todoService.delete("secondTask");
        Assert.assertTrue((todoService.getTaskList().size())>0);
    }

    @Test
    public void givenEmptyList_whenDeleteTask_ThenReturnEmptyList() {
        List<String> taskList = new ArrayList<String>();
        todoService.setTaskList(taskList);
        todoService.delete("secondTask");
        Assert.assertFalse((todoService.getTaskList().size())>0);
    }
}
