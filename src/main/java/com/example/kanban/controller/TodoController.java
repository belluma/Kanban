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
        System.out.println("Controller");
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

//        return new ResponseEntity<>(new Todo(), HttpStatus.OK);
    }
    @GetMapping(path="{query}")
    public ResponseEntity<List<Todo>>searchTodos(@PathVariable String query){
        return new ResponseEntity<>(List.of(new Todo()), HttpStatus.OK);

    }
    @PostMapping()
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo){
        return new ResponseEntity<>(new Todo(), HttpStatus.OK);

    }
    @PutMapping(path="{advance}")
    public ResponseEntity<List<Todo>> updateTodos(@RequestBody List<Integer> ids, @PathVariable String advance){
        return new ResponseEntity<>(List.of(new Todo()), HttpStatus.OK);

    }
    @PutMapping()
    public ResponseEntity<Todo> updateTodoContent(@RequestBody Todo todo){
        return new ResponseEntity<>(new Todo(), HttpStatus.OK);

    }


}
