package com.example.kanban.service;

import com.example.kanban.model.Todo;
import com.example.kanban.repo.TodoRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Data
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepo;

    public List<Todo> getAllTodos() throws NoSuchElementException {
        List<Todo> result =  todoRepo.findAll();
        if(!result.isEmpty()) return result;
        throw new NoSuchElementException();
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

    public List<Todo> updateTodos(List<Integer> ids, boolean advance){
        return List.of(new Todo());
    }
    public void deleteTodos(List<Integer> id){

    }
    public Todo updateTodoContent(Integer id, String title, String description){
        return new Todo();
    }

}
