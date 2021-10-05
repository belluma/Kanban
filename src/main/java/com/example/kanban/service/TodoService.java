package com.example.kanban.service;

import com.example.kanban.model.Todo;
import com.example.kanban.repo.TodoRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class TodoService {
    @Autowired
    private TodoRepository todoRepo;

    public List<Todo> getAllTodos(){
        return List.of(new Todo());
    }
    public Todo getSingleTodo(){
        return new Todo();
    }
    public Todo createTodo(String title, String description){
        return new Todo(title, description);
    }

}
