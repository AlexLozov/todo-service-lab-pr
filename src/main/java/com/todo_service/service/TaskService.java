package com.todo_service.service;


import com.todo_service.model.request.task.TaskCreateRequest;
import com.todo_service.model.request.task.TaskUpdateRequest;
import com.todo_service.model.response.task.TaskResponse;

import java.util.List;

public interface TaskService {

    TaskResponse createTask(TaskCreateRequest request);

    List<TaskResponse> getTasksList();
    TaskResponse getTaskById(Integer id);

    TaskResponse updateTask(Integer id, TaskUpdateRequest request);
    void deleteTask(Integer id);

    void checkTaskExists(Integer id);
}
