package com.example.kanban.controller;

import com.example.kanban.model.Todo;
import com.example.kanban.service.TodoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
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

    @Captor
    ArgumentCaptor<Todo> todoCaptor = ArgumentCaptor.forClass(Todo.class);

    @Autowired
    protected WebApplicationContext wac;

    @InjectMocks
    TodoController todoController;

    @Mock
    TodoService todoService;

    private List<Todo> todos;

    @Before
    public void setup() throws Exception {
        System.out.println("before test");
        this.mockMvc = standaloneSetup(this.todoController).build();
        System.out.println("aftre standalonesetup");
        Todo todo1 = new Todo("Title", "description");
        Todo todo2 = new Todo("Title 2", "longer description");
        todos = new ArrayList<>();
        todos.add(todo1);
        todos.add(todo2);
    }

    @Test
    public void testGetAllTodos() throws Exception {
        when(todoService.getAllTodos()).thenReturn(todos);
        this.mockMvc.perform(get("/api/todo"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(todoService).getAllTodos();
    }

    @Test
    public void testGetAllTodosReturnsError() throws Exception {
        when(todoService.getAllTodos()).thenThrow(new NoSuchElementException());
        this.mockMvc.perform(get("/api/todo"))
                .andExpect(status().isNoContent())
                .andDo(MockMvcResultHandlers.print());
        verify(todoService).getAllTodos();
    }

    @Test
    public void testGetTodoById() throws Exception {
        when(todoService.getTodoById("1")).thenReturn(todos.get(0));
        this.mockMvc.perform(get("/api/todo/1"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(todoService).getTodoById("1");
    }

    @Test
    public void testGetTodoByIdReturnsError() throws Exception {
        when(todoService.getTodoById("1")).thenThrow(new NoSuchElementException());
        this.mockMvc.perform(get("/api/todo/1"))
                .andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
        verify(todoService).getTodoById("1");
    }

    @Test
    public void testSearchTodos() throws Exception {
        when(todoService.getTodosByText("title"))
                .thenReturn(todos);
        this.mockMvc.perform(get("/api/todo/query/title"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(todoService).getTodosByText(anyString());
    }

    @Test
    public void testSearchTodoReturnsError() throws Exception {
        when(todoService.getTodosByText("fdsa")).thenThrow(new NoSuchElementException());
        this.mockMvc.perform(get("/api/todo/query/fdsa"))
                .andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
        verify(todoService).getTodosByText(anyString());
    }

    @Test
    public void testCreateTodo() throws Exception {
        when(todoService.createTodo("Title", "description"))
                .thenReturn(todos.get(0));
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(new Todo("Title", "description"));
        } catch (JsonProcessingException e) {
            fail();
        }
        this.mockMvc.perform(post("/api/todo")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(todoService).createTodo("Title", "description");
//        verify(todoService).createTodo(todoCaptor.capture());
    }

    @Test
    public void testCreateTodoReturnsError() throws Exception {
        when(todoService.createTodo("Title", "description"))
                .thenThrow(new IllegalArgumentException());
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(new Todo("Title", "description"));
        } catch (JsonProcessingException e) {
            fail();
        }
        this.mockMvc.perform(post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .characterEncoding("utf-8"))
                .andExpect(status().isNotAcceptable())
                .andDo(MockMvcResultHandlers.print());
        verify(todoService).createTodo("Title", "description");
    }

    @Test
    public void testUpdateTodos() throws Exception {
        when(todoService.updateTodos(List.of("1"), "1"))
                .thenReturn(todos);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(List.of(1));
        } catch (JsonProcessingException e) {
            fail();
        }
        this.mockMvc.perform(put("/api/todo/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        this.mockMvc.perform(put("/api/todo/0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(todoService).updateTodos(List.of("1"), "1");
    }

    @Test
    public void testUpdateTodosReturnsError() throws Exception {
        when(todoService.updateTodos(List.of("1"), "1"))
                .thenThrow(new NoSuchElementException());
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(List.of(1));
        } catch (JsonProcessingException e) {
            fail();
        }
        this.mockMvc.perform(put("api/todo/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .characterEncoding("utf-8"))
                .andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
        this.mockMvc.perform(put("/api/todo/0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .characterEncoding("utf-8"))
                .andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
        verify(todoService).updateTodos(List.of("1"), "1");
    }

    @Test
    public void testUpdateTodoContent() throws Exception {
        when(todoService.updateTodoContent("1", "Title", "description"))
                .thenReturn(todos.get(0));
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(todos.get(0));
        } catch (JsonProcessingException e) {
            fail();
        }
        this.mockMvc.perform(put("api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(todoService).updateTodoContent("1", "Title", "description");
    }

    @Test
    public void testUpdateTodoContentReturnsErrorWhenNotFound() throws Exception {
        Todo todo = new Todo("Title", "description");
        when(todoService.updateTodoContent("123", "Title", "description"))
                .thenThrow(new NoSuchElementException());
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(todo);
        } catch (JsonProcessingException e) {
            fail();
        }
        this.mockMvc.perform(put("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .characterEncoding("utf-8"))
                .andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
        verify(todoService).updateTodoContent("123", "Title", "description");
    }

    @Test
    public void testUpdateTodoContentReturnsErrorWhenNoTextOrDescriptionGiven() throws Exception {
        Todo todo = new Todo("Title", "");
        when(todoService.updateTodoContent("1", "Title", ""))
                .thenThrow(new IllegalArgumentException());
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(todo);
        } catch (JsonProcessingException e) {
            fail();
        }
        this.mockMvc.perform(put("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .characterEncoding("utf-8"))
                .andExpect(status().isNotAcceptable())
                .andDo(MockMvcResultHandlers.print());
        verify(todoService).updateTodoContent("1", "Title", "");
    }
}
