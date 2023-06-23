package com.ista.isp.assessment.todo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class Todo {
    @JsonIgnore
    private static final AtomicInteger count = new AtomicInteger(0);
    private long id;
    private String title;
    private boolean complete;

    public Todo() { }
    public Todo(String title, boolean complete) {
        this.id = count.incrementAndGet();
        this.title = title;
        this.complete = complete;
    }

    public Todo(long id, String title, boolean complete) {
        this.id = id;
        this.title = title;
        this.complete = complete;
    }
}
