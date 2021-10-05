package com.example.kanban.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

import static com.example.kanban.model.TodoStatus.TODO;

@Entity
@Table(name = "todos")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;
    private TodoStatus status;

    public Todo(String title, String description){
        this.title = title;
        this.description = description;
        this.status = TODO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Todo todo = (Todo) o;
        return Objects.equals(id, todo.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
