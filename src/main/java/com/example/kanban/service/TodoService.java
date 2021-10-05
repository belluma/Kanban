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
    public Todo getTodoById(Integer id){
        return new Todo();
    }
    public List<Todo> getTodosByText(String searchQuery){
        return List.of(new Todo());
    }
    public Todo createTodo(String title, String description){
        return new Todo(title, description);
    }
    public Todo updateTodo(Integer id, boolean advance){
        return new Todo();
    }
    public List<Todo> updateMultipleTodos(List<Integer> ids){
        return List.of(new Todo());
    }
    public void deleteTodo(Integer id){

    }
    public void deleteMultipleTodos(Integer id){

    }
    public Todo updateTodoContent(Integer id, String title, String description){
        return new Todo();
    }

}
