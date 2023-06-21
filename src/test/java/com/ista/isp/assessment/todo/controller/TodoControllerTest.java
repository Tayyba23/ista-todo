package com.ista.isp.assessment.todo.controller;

import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static com.jayway.restassured.RestAssured.port;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerTest {

    @Autowired
    MockMvc mockMvc;


    @Before
    public void setBaseUri () {
        port = 8080;
        RestAssured.baseURI = "http://localhost"; // replace as appropriate
    }

    @Test
    void addItem() throws Exception{
        mockMvc.perform(post("/ista/tasks")).andExpect(status().is2xxSuccessful());
    }

    @Test
    void getItem() throws Exception{
        mockMvc.perform(get("/ista/tasks")).andExpect(status().is2xxSuccessful());
    }

    @Test
    void deleteItem() throws Exception{
        String task = "taskOne";
        mockMvc.perform(delete("/ista/task/{task}",task)).andExpect(status().is2xxSuccessful());
    }

}
