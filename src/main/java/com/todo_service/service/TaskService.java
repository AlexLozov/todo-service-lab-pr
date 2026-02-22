package com.todo_service.service;


import com.todo_service.model.request.task.TaskCreateRequest;
import com.todo_service.model.request.task.TaskUpdateRequest;
import com.todo_service.model.response.PaginationResponse;
import com.todo_service.model.response.task.TaskResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TaskService {

    TaskResponse createTask(TaskCreateRequest request);

    List<TaskResponse> getTasksList();
    TaskResponse getTaskById(Integer id);

    TaskResponse updateTask(Integer id, TaskUpdateRequest request);
    void deleteTask(Integer id);

    PaginationResponse<TaskResponse> getTaskPageByDate(int page, int limit);
    PaginationResponse<TaskResponse> getTaskPageByStatusAndSortByDate(Boolean isFinished, int page, int limit);
}
