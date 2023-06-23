package com.ista.isp.assessment.todo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ista.isp.assessment.todo.model.Todo;
import com.ista.isp.assessment.todo.service.TodoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Mock
    TodoService todoService;

    @Test
    void whenAddItemCall_ReturnSuccessfulResponse() throws Exception {
        Todo task = new Todo("taskOne", false);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        String requestJson = ow.writeValueAsString(task);
        mockMvc.perform(post("/ista/tasks").
                        contentType(APPLICATION_JSON).
                        content(requestJson)).
                andExpect(status().is2xxSuccessful());
    }

    @Test
    void WhenAddItemCall_ReturnSuccessfulResponseWithList() throws Exception {
        Todo task = new Todo("taskOne", false);
        ArrayList<Todo> initialList = new ArrayList<Todo>(Arrays.asList(new Todo("taskOne", false)));

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        String requestJson = ow.writeValueAsString(task);

        Mockito.when(todoService.addTask(task)).thenReturn(initialList);
        mockMvc.perform(post("/ista/tasks").
                        contentType(APPLICATION_JSON).
                        content(requestJson)).
                andExpect(status().is2xxSuccessful()).
                andExpect(
                        new ResultMatcher() {
                            @Override
                            public void match(MvcResult mvcResult) throws Exception {
                                String content = mvcResult.getResponse().getContentAsString();
                                ObjectMapper objectMapper = new ObjectMapper();
                                ArrayList<String> testTaskList = (ArrayList<String>) objectMapper.readValue(content, List.class);
                                assertNotEquals(0, testTaskList.size());
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
        Todo task = new Todo("taskOne", false);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        String requestJson = ow.writeValueAsString(task);

        mockMvc.perform(delete("/ista/tasks").
                        contentType(APPLICATION_JSON).
                        content(requestJson)).
                andExpect(status().is2xxSuccessful());
    }
}
