package com.todo_service.service;

import com.todo_service.model.entity.Task;
import com.todo_service.model.request.TaskCreateRequest;
import com.todo_service.model.request.TaskUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    Task createTask(TaskCreateRequest request);

    List<Task> getTasksList();
    Task getTaskById(Integer id);

    Task updateTask(Integer id, TaskUpdateRequest request);
    void deleteTask(Integer id);

    void checkTaskExists(Integer id);
}
