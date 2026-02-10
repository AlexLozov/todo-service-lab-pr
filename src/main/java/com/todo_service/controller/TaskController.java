package com.todo_service.controller;

import com.todo_service.model.entity.Task;
import com.todo_service.model.request.TaskCreateRequest;
import com.todo_service.model.request.TaskUpdateRequest;
import com.todo_service.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@Tag(name = "Task API", description = "CRUD операции с задачами")
public class TaskController {
    private final TaskService taskService;
    // - http://localhost:8080/swagger-ui/index.html

    @PostMapping
    @Operation(summary = "Создать новую задачу")
    public ResponseEntity<Task> createTask(@RequestBody TaskCreateRequest request){
        Task task = taskService.createTask(request);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation( summary = "Получить задачу по ID")
    public ResponseEntity<Task> getTaskById(@PathVariable Integer id){
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @GetMapping
    @Operation(summary = "Получить список задач")
    public ResponseEntity<List<Task>> getTasksList(){
        List<Task> tasks = taskService.getTasksList();
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{id}")
    @Operation(summary = "изменить задачу по ID")
    public ResponseEntity<Task> updateTask(@PathVariable Integer id,
                                           @Valid @RequestBody TaskUpdateRequest request){
        Task task = taskService.updateTask(id, request);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "удалить задачу по ID")
    public ResponseEntity<Boolean> deleteTask(@PathVariable Integer id){
        taskService.checkTaskExists(id);
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build(); // 204
    }

}
