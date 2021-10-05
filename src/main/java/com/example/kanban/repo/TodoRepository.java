package com.example.kanban.repo;

import com.example.kanban.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer>  {
    default List<Todo> findByTitleOrDescription(String searchQuery)throws NoSuchElementException {
        List<Todo> result =  findAll().stream()
                .filter(todo -> todo.getTitle().toLowerCase().contains(searchQuery.toLowerCase()) || todo.getDescription().toLowerCase().contains(searchQuery.toLowerCase()))
                .toList();
        if(!result.isEmpty()) return result;
        throw new NoSuchElementException();
    }

}
