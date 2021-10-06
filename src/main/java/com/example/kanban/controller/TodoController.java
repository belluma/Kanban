package com.example.kanban.controller;

import com.example.kanban.model.Todo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todo")
public class TodoController {

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos(){
        return new ResponseEntity<>(List.of(new Todo()), HttpStatus.OK);
    }
    @GetMapping(path="{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable String id){
        return new ResponseEntity<>(new Todo(), HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<Todo>>searchTodos(@RequestBody String searchQuery){
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
