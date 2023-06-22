package com.ista.isp.assessment.todo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ista.isp.assessment.todo.service.TodoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TodoService todoService;

    @Test
    void whenAddItemCall_ReturnSuccessfulResponse() throws Exception {
        String task = "taskOne";
        mockMvc.perform(post("/ista/tasks/{task}", task)).
                andExpect(status().is2xxSuccessful());
    }

    @Test
    void WhenAddItemCall_ReturnSuccessfulResponseWithList() throws Exception {
        String task = "taskOne";
        ArrayList<String> initialList = new ArrayList<String>(Arrays.asList("taskOne"));

        Mockito.when(todoService.addTask(task)).thenReturn(initialList);
        mockMvc.perform(post("/ista/tasks/{task}", task)).
                andExpect(status().is2xxSuccessful()).andExpect(
                        new ResultMatcher() {
                            @Override
                            public void match(MvcResult mvcResult) throws Exception {
                                String content = mvcResult.getResponse().getContentAsString();
                                ObjectMapper objectMapper = new ObjectMapper();
                                ArrayList<String> testTaskList = (ArrayList<String>) objectMapper.readValue(content, List.class);
                                assertEquals(1, testTaskList.size());
                            }
                        });
    }

    @Test
    void getItemTest() throws Exception {
        mockMvc.perform(get("/ista/tasks")).
                andExpect(status().is2xxSuccessful());
    }

    @Test
    void deleteItemTest() throws Exception {
        String task = "taskOne";
        mockMvc.perform(delete("/ista/tasks/{task}", task)).
                andExpect(status().is2xxSuccessful());
    }

    @Test
    void givenAList_whenDeleteItem_ThenReturnList() throws Exception {
        String task = "taskOne";
        ArrayList<String> finalList = new ArrayList<String>(Arrays.asList("taskTwo"));

        Mockito.when(todoService.deleteTask(task)).thenReturn(finalList);
        mockMvc.perform(delete("/ista/tasks/{task}", task)).
                andExpect(status().is2xxSuccessful()).andExpect(
                        new ResultMatcher() {
                            @Override
                            public void match(MvcResult mvcResult) throws Exception {
                                String content = mvcResult.getResponse().getContentAsString();
                                ObjectMapper objectMapper = new ObjectMapper();
                                ArrayList<String> testTaskList = (ArrayList<String>) objectMapper.readValue(content, List.class);
                                assertEquals(1, testTaskList.size());
                            }
                        }
                );
    }
}
