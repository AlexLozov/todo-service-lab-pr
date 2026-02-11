package com.todo_service.controller;

import com.todo_service.model.entity.Task;
import com.todo_service.model.request.task.TaskCreateRequest;
import com.todo_service.model.request.task.TaskUpdateRequest;
import com.todo_service.model.response.ApiResponse;
import com.todo_service.model.response.PaginationResponse;
import com.todo_service.model.response.task.TaskResponse;
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
    public ResponseEntity<ApiResponse<TaskResponse>> createTask(
            @Valid @RequestBody TaskCreateRequest request){
        TaskResponse taskResponse = taskService.createTask(request);
        return new ResponseEntity<>(ApiResponse.success(taskResponse), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation( summary = "Получить задачу по ID")
    public ResponseEntity<ApiResponse<TaskResponse>> getTaskById(@PathVariable Integer id){
        TaskResponse taskResponse = taskService.getTaskById(id);
        return ResponseEntity.ok(ApiResponse.success(taskResponse));
    }

    @GetMapping
    @Operation(summary = "Получить список задач")
    public ResponseEntity<ApiResponse<List<TaskResponse>>> getTasksList(){
        List<TaskResponse> tasks = taskService.getTasksList();
        return ResponseEntity.ok(ApiResponse.success(tasks));
    }

    @PutMapping("/{id}")
    @Operation(summary = "изменить задачу по ID")
    public ResponseEntity<ApiResponse<TaskResponse>> updateTask(@PathVariable Integer id,
                                           @Valid @RequestBody TaskUpdateRequest request){
        TaskResponse taskResponse = taskService.updateTask(id, request);
        return ResponseEntity.ok(ApiResponse.success(taskResponse));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "удалить задачу по ID")
    public ResponseEntity<ApiResponse<Void>> deleteTask(@PathVariable Integer id){
        taskService.deleteTask(id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @GetMapping("/page")
    @Operation(summary = "получить список задач с пагинацией")
    public ResponseEntity<ApiResponse<PaginationResponse<TaskResponse>>> getTasksPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int limit){

        PaginationResponse<TaskResponse> response = taskService.getTaskPage(page, limit);

        return ResponseEntity.ok(ApiResponse.success(response));
    }

}
