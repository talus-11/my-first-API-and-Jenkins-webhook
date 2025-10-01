// src/main/java/com/demo/Application.java
package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

@RestController
@RequestMapping("/api")
class DemoController {
    
    private List<Task> tasks = new ArrayList<>();
    private long idCounter = 1;
    
    @GetMapping("/health")
    public Map<String, Object> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("timestamp", LocalDateTime.now());
        response.put("message", "Jenkins CI/CD Demo App is running!");
        return response;
    }
    
    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return tasks;
    }
    
    @GetMapping("/tasks/{id}")
    public Task getTask(@PathVariable long id) {
        return tasks.stream()
            .filter(t -> t.getId() == id)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Task not found"));
    }
    
    @PostMapping("/tasks")
    public Task createTask(@RequestBody TaskRequest request) {
        Task task = new Task();
        task.setId(idCounter++);
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setCompleted(false);
        task.setCreatedAt(LocalDateTime.now());
        tasks.add(task);
        return task;
    }
    
    @PutMapping("/tasks/{id}")
    public Task updateTask(@PathVariable long id, @RequestBody TaskRequest request) {
        Task task = getTask(id);
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setCompleted(request.isCompleted());
        return task;
    }
    
    @DeleteMapping("/tasks/{id}")
    public Map<String, String> deleteTask(@PathVariable long id) {
        tasks.removeIf(t -> t.getId() == id);
        return Map.of("message", "Task deleted successfully");
    }
}

class Task {
    private long id;
    private String title;
    private String description;
    private boolean completed;
    private LocalDateTime createdAt;
    
    // Getters and Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}

class TaskRequest {
    private String title;
    private String description;
    private boolean completed;
    
    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}
