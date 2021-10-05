package com.example.kanban.service;

import com.example.kanban.model.Todo;
import com.example.kanban.model.TodoStatus;
import com.example.kanban.repo.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
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
    private final TodoService todoService = new TodoService(todoRepository);

    @Test
    void getAllTodos() {
        when(todoRepository.findAll()).thenReturn(List.of(new Todo("Title", "description")));
        List<Todo> actual = todoService.getAllTodos();
        assertIterableEquals(List.of(new Todo("Title", "description")), actual);
    }

    @Test
    void getAllTodosThrowsWhenDBEmpty() {
        when(todoRepository.findAll()).thenReturn(List.of());
        assertThrows(NoSuchElementException.class, todoService::getAllTodos);
    }

    @Test
    void getTodoById() {
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
    void getTodosByText() {
        when(todoRepository.findByTitleOrDescription("Title")).thenReturn(List.of(new Todo("Title", "description")));
        List<Todo> actual = todoService.getTodosByText("Title");
        assertIterableEquals(List.of(new Todo("Title", "description")), actual);
    }

    @Test
    void getTodosByTextThrowsWhenNothingFound() {
        when(todoRepository.findByTitleOrDescription("no such title")).thenThrow(new NoSuchElementException());
        assertThrows(NoSuchElementException.class, () -> todoService.getTodosByText("no such title"));
    }

    @Test
    void createTodo() {
        when(todoRepository.save(new Todo("title", "description"))).thenReturn(new Todo("title", "description"));
        Todo actual = todoService.createTodo("title",  "description");
        assertThat(actual).isEqualTo(new Todo("title", "description"));
    }

    @Test
    void createTodoThrowsWhenTitleEmpty() {
        assertThrows(IllegalArgumentException.class, () -> todoService.createTodo("", "description"));
        assertThrows(IllegalArgumentException.class, () -> todoService.createTodo("title", ""));
    }

    @Test
    void updateTodo() {
        Todo todo = new Todo("title", "description");
        when(todoRepository.findById(1)).thenReturn(Optional.of(todo));
        todo.setStatus(TodoStatus.DOING);
        when(todoRepository.save(todo)).thenReturn(todo);
        List<Todo> actual = todoService.updateTodos(List.of(1), true);
        assertThat(actual.get(0)).isEqualTo(todo);
    }

    @Test
    void updateTodoThrowsWhenNotFound() {
        when(todoRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> todoService.updateTodos(List.of(1), false));
    }

    @Test
    void deleteTodos() {
        Todo todo = new Todo("title", "description");
        when(todoRepository.findById(1)).thenReturn(Optional.of(todo));
        assertDoesNotThrow(() -> todoService.deleteTodos(List.of(1)));
    }

    @Test
    void deleteTodoThrowsWhenNotFound() {
        when(todoRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> todoService.deleteTodos(List.of(1)));
    }

    @Test
    void updateTodoContent() {
        Todo todo = new Todo("Title", "description");
        Todo updatedTodo = new Todo("new title", "better description");
        when(todoRepository.findById(1)).thenReturn(Optional.of(todo));
        Todo actual = todoService.updateTodoContent(1, "new title", "better description");
        assertThat(actual).isEqualTo(updatedTodo);

    }

    @Test
    void updateTodoContentThrowsWhenTitleOrDescriptionEmpty() {
        assertThrows(IllegalArgumentException.class, () -> todoService.updateTodoContent(1, "", "description"));
        assertThrows(IllegalArgumentException.class, () -> todoService.updateTodoContent(1, "Title", ""));

    }

    @Test
    void updateTodoContentThrowsWhenNotFound() {
        when(todoRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> todoService.updateTodoContent(1, "Title", ""));
    }

}
