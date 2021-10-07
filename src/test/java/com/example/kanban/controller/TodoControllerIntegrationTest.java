package com.example.kanban.controller;

import com.example.kanban.model.Todo;
import com.example.kanban.repo.TodoRepository;
import org.hibernate.tool.schema.spi.SchemaCreator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;
import java.io.IOException;

//source: https://phauer.com/2019/focus-integration-tests-mock-based-tests/

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TodoControllerIntegrationTest {

    private JdbcTemplate template;
    private TodoRepository todoRepo;

    @BeforeAll
    public void setup() throws IOException {
        PostgreSQLContainer db = new PostgreSQLContainer("postgres:11.2-alpine");
        db.start();
        DataSource dataSource = DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .username(db.getUsername())
                .password(db.getPassword())
                .url(db.getJdbcUrl())
                .build();
        this.template = new JdbcTemplate(dataSource);
        template.execute("Create Table IF NOT EXISTS products (id varchar(40) primary key, name varchar(40));");
//        TodoRepository todoRepo = new TodoRepository();
        TodoController controller = new TodoController();


    }


}
