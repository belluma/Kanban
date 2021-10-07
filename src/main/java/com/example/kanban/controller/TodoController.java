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
        try {
            return new ResponseEntity(todoService.getAllTodos(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @GetMapping(path="{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable String id){
        try {
            return new ResponseEntity<>(todoService.getTodoById(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(path="query/{query}")
    public ResponseEntity<List<Todo>>searchTodos(@PathVariable String query){
        try {
            return new ResponseEntity<>(todoService.getTodosByText(query), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @PostMapping()
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo){
        try {
            return new ResponseEntity<>(todoService.createTodo(todo.getTitle(), todo.getDescription()), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }
    @PutMapping(path="{advance}")
    public ResponseEntity<List<Todo>> updateTodos(@RequestBody List<String> ids, @PathVariable String advance){
        try {
            return new ResponseEntity<>(todoService.updateTodos(ids, advance), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping()
    public ResponseEntity<Todo> updateTodoContent(@RequestBody Todo todo){
        try {
            return new ResponseEntity<>(todoService.updateTodoContent(todo), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch(IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }
    @DeleteMapping(path="{id}")
    public ResponseEntity<Todo> deleteTodo(@PathVariable List<String> ids){
        todoService.deleteTodos(ids);
        return new ResponseEntity<>(new Todo(), HttpStatus.OK);
    }


}
