package com.example.kanban.service;

import com.example.kanban.model.Todo;
import com.example.kanban.repo.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class TodoServiceTest {

    //    @Mock
    TodoRepository todoRepository = mock(TodoRepository.class);

    //    @InjectMocks
    private TodoService todoService = new TodoService(todoRepository);

    @Test
    void getAllTodos() {
        when(todoRepository.findAll()).thenReturn(List.of(new Todo("Title", "description")));
        List<Todo> actual = todoService.getAllTodos();
        assertIterableEquals(List.of(new Todo("Title", "description")), actual);
    }
    @Test
    void getAllTodosThrowsWhenDBEmpty(){
        when(todoRepository.findAll()).thenReturn(List.of());
        assertThrows(NoSuchElementException.class,() -> todoService.getAllTodos());
    }
    @Test
    void getTodosById() {
        when(todoRepository.findById(1)).thenReturn(Optional.of(new Todo("Title", "description")));
        Todo actual = todoService.getTodoById(1);
        assertThat(actual).isEqualTo(new Todo("Title", "description"));
    }
    @Test
    void getTodoByIdThrowsWhenNotFound() {
        when(todoRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> todoService.getTodoById(1));
    }
    @Test
    void getTodosByText(){
        when(todoRepository.findByTitleOrDescription("Title")).thenReturn(List.of(new Todo("Title", "description")));
        Todo actual = todoService.getTodosByText("Title").get(0);
        assertThat(actual).isEqualTo(new Todo("title", "description"));

    }
    @Test
    void getTodosByTextThrowsWhenNothingFound(){
        when(todoRepository.findByTitleOrDescription("no such title")).thenThrow(new NoSuchElementException());
        assertThrows(NoSuchElementException.class, () -> todoService.getTodosByText("no such title"));
    }

    @Test
    void createTodo() {
        when(todoRepository.save(new Todo("title", "description"))).thenReturn(new Todo("title", "description"));
        Todo actual = todoService.createTodo("title", "description");
        assertThat(actual).isEqualTo(new Todo("title", "description"));
    }
    @Test
    void createTodoThrowsWhenTitleEmpty() {
        when(todoRepository.save(new Todo("title", "description"))).thenThrow(new IllegalArgumentException());
        assertThrows(IllegalArgumentException.class, () -> todoService.createTodo( "title", "description"));
    }

    @Test
    void updateTodo() {
        when(todoRepository.save(new Todo("title", "description"))).thenReturn(new Todo("title", "description"));
        Todo actual = todoService.updateTodos("title", "description");
        assertThat(actual).isEqualTo(new Todo("title", "description"));
    }
    @Test
    void updateTodoThrowsWhenNotFound() {
        when(todoRepository.save(new Todo("title", "description"))).thenThrow(new IllegalArgumentException());
        assertThrows(IllegalArgumentException.class, () -> todoService.createTodo( "title", "description"));
    }

    @Test
    void updateMultipleTodos() {
    }
    @Test
    void updateMultipleTodosThrowsWhenNotFound() {
    }

    @Test
    void deleteTodo() {
    }
    @Test
    void deleteTodoThrowsWhenNotFound() {
    }

    @Test
    void deleteMultipleTodos() {
    }

    @Test
    void updateTodoContent() {
    }
    @Test
    void updateTodoContentThrowsWhenTitleEmpty() {
    }

}
