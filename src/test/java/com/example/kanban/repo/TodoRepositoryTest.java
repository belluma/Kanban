package com.example.kanban.repo;

import com.example.kanban.model.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
class TodoRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TodoRepository repository;


    @Test
    void findByTitle() {
        entityManager.persist(new Todo("Title", "description"));

        List<Todo> todos1 = repository.findByTitleOrDescription("Title");
        List<Todo> todos2 = repository.findByTitleOrDescription("itle");
        List<Todo> todos3 = repository.findByTitleOrDescription("TITLE");
        List<Todo> todos4 = repository.findByTitleOrDescription("title");

        assertThat(todos1.get(0).getTitle()).isEqualTo("Title");
        assertThat(todos2.get(0).getTitle()).isEqualTo("Title");
        assertThat(todos3.get(0).getTitle()).isEqualTo("Title");
        assertThat(todos4.get(0).getTitle()).isEqualTo("Title");
    }

    @Test
    void findByDescription() {
        entityManager.persist(new Todo("Title", "description"));

        List<Todo> todos1 = repository.findByTitleOrDescription("description");
        List<Todo> todos2 = repository.findByTitleOrDescription("iption");
        List<Todo> todos3 = repository.findByTitleOrDescription("DESC");
        List<Todo> todos4 = repository.findByTitleOrDescription("dEScriPTIon");

        assertThat(todos1.get(0).getTitle()).isEqualTo("Title");
        assertThat(todos2.get(0).getTitle()).isEqualTo("Title");
        assertThat(todos3.get(0).getTitle()).isEqualTo("Title");
        assertThat(todos4.get(0).getTitle()).isEqualTo("Title");
    }

    @Test
    void findByTitleOrDescriptionThrowsWhenNothingFound() {
        entityManager.persist(new Todo("Title", "description"));

        assertThrows(NoSuchElementException.class, () -> repository.findByTitleOrDescription("fsda"));

    }
}
