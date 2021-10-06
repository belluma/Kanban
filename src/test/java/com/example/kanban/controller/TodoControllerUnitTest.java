package com.example.kanban.controller;

import com.example.kanban.model.Todo;
import com.example.kanban.service.TodoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;


@SpringBootTest
@AutoConfigureMockMvc
@WebMvcTest
@RunWith(MockitoJUnitRunner.class)
public class TodoControllerUnitTest extends TestCase {

    MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    TodoController todoController;

    @MockBean
    TodoService todoService;

    private List<Todo> todos;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(this.todoController).build();

        Todo todo1 = new Todo("Title", "description");
        Todo todo2 = new Todo("Title 2", "longer description");
        todos = new ArrayList<>();
        todos.add(todo1);
        todos.add(todo2);
    }


    public void testGetAllTodos() {
        when(todoService.getAllTodos()).thenReturn(todos);
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andDo(MockMvcResultHandlers.print());
    }

    public void testGetAllTodosReturnsError() {
        when(todoService.getAllTodos()).thenThrow(new NoSuchElementException());
        this.mockMvc.perform(get("/"))
                .andExpect(status().isNoContent())
                .andExpect(view().name("index"))
                .andDo(MockMvcResultHandlers.print());
    }

    public void testGetTodoById() {
        when(todoService.getTodoById(1)).thenReturn(new Todo());
        this.mockMvc.perform(get("/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andDo(MockMvcResultHandlers.print());
    }

    public void testGetTodoByIdReturnsError() {
        when(todoService.getTodoById(1)).thenThrow(new NoSuchElementException());
        this.mockMvc.perform(get("/1"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("index"))
                .andDo(MockMvcResultHandlers.print());
    }

    public void testSearchTodos() {
        when(todoService.getTodosByText("title"))
                .thenReturn(List.of(new Todo("title", "description")));
        this.mockMvc.perform(get("/title"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andDo(MockMvcResultHandlers.print());
    }

    public void testSearchTodoReturnsError() {
        when(todoService.getTodosByText("fdsa")).thenThrow(new NoSuchElementException());
        this.mockMvc.perform(get("/fdsa"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andDo(MockMvcResultHandlers.print());
    }

    public void testCreateTodo() {
        when(todoService.createTodo("Title", "description"))
                .thenReturn(new Todo("Title", "description"));
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(new Todo());
        } catch (JsonProcessingException e) {
            fail();
        }
        this.mockMvc.perform(post("/"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8")
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andDo(MockMvcResultHandlers.print());
    }

    public void testCreateTodoReturnsError() {
        when(todoService.createTodo("Title", "description"))
                .thenThrow(new IllegalArgumentException());
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(new Todo());
        } catch (JsonProcessingException e) {
            fail();
        }
        this.mockMvc.perform(post("/"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8")
                .andExpect(status().isNotAcceptable())
                .andExpect(view().name("index"))
                .andDo(MockMvcResultHandlers.print());
    }

    public void testUpdateTodos() {
        when(todoService.updateTodos(List.of(1), true))
                .thenReturn(List.of(new Todo()));
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(List.of(1));
        } catch (JsonProcessingException e) {
            fail();
        }
        this.mockMvc.perform(put("/1"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8")
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andDo(MockMvcResultHandlers.print());
        this.mockMvc.perform(put("/0"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8")
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andDo(MockMvcResultHandlers.print());
    }

    public void testUpdateTodosReturnsError() {
        when(todoService.updateTodos(List.of(1), true))
                .thenThrow(new NoSuchElementException());
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(List.of(1));
        } catch (JsonProcessingException e) {
            fail();
        }
        this.mockMvc.perform(put("/1"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8")
                .andExpect(status().isNotFound())
                .andExpect(view().name("index"))
                .andDo(MockMvcResultHandlers.print());
        this.mockMvc.perform(put("/0"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8")
                .andExpect(status().isNotFound())
                .andExpect(view().name("index"))
                .andDo(MockMvcResultHandlers.print());
    }

    public void testUpdateTodoContent() {
        Todo todo = new Todo("Title", "description");
        when(todoService.updateTodoContent(1, "Title", "description"))
                .thenReturn(todo);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(todo);
        } catch (JsonProcessingException e) {
            fail();
        }
        this.mockMvc.perform(put("/"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8")
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andDo(MockMvcResultHandlers.print());
    }

    public void testUpdateTodoContentReturnsErrorWhenNotFound(){
        Todo todo = new Todo("Title", "description");
        when(todoService.updateTodoContent(123, "Title", "description"))
                .thenThrow(new NoSuchElementException());
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(todo);
        } catch (JsonProcessingException e) {
            fail();
        }
        this.mockMvc.perform(put("/"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8")
                .andExpect(status().isNotFound())
                .andExpect(view().name("index"))
                .andDo(MockMvcResultHandlers.print());
    }
 public void testUpdateTodoContentReturnsErrorWhenNoTextOrDescriptionGiven() {
        Todo todo = new Todo("Title", "");
        when(todoService.updateTodoContent(1, "Title", ""))
                .thenThrow(new IllegalArgumentException());
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(todo);
        } catch (JsonProcessingException e) {
            fail();
        }
        this.mockMvc.perform(put("/"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8")
                .andExpect(status().isNotAcceptable())
                .andExpect(view().name("index"))
                .andDo(MockMvcResultHandlers.print());
    }
}
