package com.example.kanban.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TodoControllerIntegrationTest {

    private JdbcTemplate template;
    private MockMvc client;

    @BeforeAll
    public void setup() throws IOException {
        PostgreSQLContainer db
    }


}
