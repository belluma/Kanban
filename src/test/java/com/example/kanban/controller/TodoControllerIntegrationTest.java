package com.example.kanban.controller;

import com.example.kanban.model.Todo;
import com.example.kanban.repo.TodoRepository;
import org.hibernate.tool.schema.spi.SchemaCreator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import javax.sql.DataSource;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
public class TodoControllerIntegrationTest {

    @Container
    private final PostgreSQLContainer container = new PostgreSQLContainer()
            .withDatabaseName("myDb")
            .withUsername("user")
            .withPassword("123");

    @Test
    void test() {
        assertTrue(container.isRunning());
    }
}
