package com.example.kanban.controller;

import com.example.kanban.model.Todo;
import com.example.kanban.service.TodoService;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


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
    public void setup() throws Exception{
        this.mockMvc = standaloneSetup(this.todoController).build();

        Todo todo1 = new Todo("Title", "description");
        Todo todo2 = new Todo("Title 2", "longer description");
        todos = new ArrayList<>();
        todos.add(todo1);
        todos.add(todo2);
    }



    public void testGetAllTodos() {
    }

    public void testGetTodoById() {
    }

    public void testSearchTodos() {
    }

    public void testCreateTodo() {
    }

    public void testUpdateTodos() {
    }

    public void testUpdateTodoContent() {
    }
}
