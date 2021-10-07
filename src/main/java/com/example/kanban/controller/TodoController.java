package com.example.kanban.controller;

import com.example.kanban.model.Todo;
import com.example.kanban.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/todo")
@RequiredArgsConstructor
public class TodoController {

    @Autowired
    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos(){
            return new ResponseEntity(todoService.getAllTodos(), HttpStatus.OK);
    }
    @GetMapping(path="{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable String id){
            return new ResponseEntity<>(todoService.getTodoById(id), HttpStatus.OK);
    }
    @GetMapping(path="query/{query}")
    public ResponseEntity<List<Todo>>searchTodos(@PathVariable String query){
            return new ResponseEntity<>(todoService.getTodosByText(query), HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo)throws NoSuchElementException{
        return new ResponseEntity<>(todoService.createTodo(todo.getTitle(), todo.getDescription()),
        HttpStatus.OK);
    }
    @PutMapping(path="{advance}")
    public ResponseEntity<List<Todo>> updateTodos(@RequestBody List<String> ids, @PathVariable String advance){
            return new ResponseEntity<>(todoService.updateTodos(ids, advance), HttpStatus.OK);
    }
    @PutMapping()
    public ResponseEntity<Todo> updateTodoContent(@RequestBody Todo todo){
            return new ResponseEntity<>(todoService.updateTodoContent(todo), HttpStatus.OK);
    }
    @DeleteMapping(path="{id}")
    public ResponseEntity<Todo> deleteTodo(@PathVariable List<String> ids){
        todoService.deleteTodos(ids);
        return new ResponseEntity<>(new Todo(), HttpStatus.OK);
    }


}
