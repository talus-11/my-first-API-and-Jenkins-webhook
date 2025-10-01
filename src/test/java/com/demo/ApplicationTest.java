// src/test/java/com/demo/ApplicationTests.java
package com.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
        // Verifica que el contexto de Spring se cargue correctamente
    }

    @Test
    void testHealthEndpoint() throws Exception {
        mockMvc.perform(get("/api/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"))
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void testGetAllTasks() throws Exception {
        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testCreateTask() throws Exception {
        String taskJson = """
            {
                "title": "Test Task",
                "description": "Test Description",
                "completed": false
            }
            """;

        mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(taskJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Task"))
                .andExpect(jsonPath("$.description").value("Test Description"))
                .andExpect(jsonPath("$.completed").value(false))
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void testUpdateTask() throws Exception {
        // Primero crear una tarea
        String createJson = """
            {
                "title": "Original Task",
                "description": "Original Description",
                "completed": false
            }
            """;

        mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createJson));

        // Actualizar la tarea
        String updateJson = """
            {
                "title": "Updated Task",
                "description": "Updated Description",
                "completed": true
            }
            """;

        mockMvc.perform(put("/api/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Task"))
                .andExpect(jsonPath("$.completed").value(true));
    }

    @Test
    void testDeleteTask() throws Exception {
        // Crear una tarea primero
        String taskJson = """
            {
                "title": "Task to Delete",
                "description": "This task will be deleted",
                "completed": false
            }
            """;

        mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(taskJson));

        // Eliminar la tarea
        mockMvc.perform(delete("/api/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Task deleted successfully"));
    }
}
