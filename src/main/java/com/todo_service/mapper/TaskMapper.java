package com.todo_service.mapper;

import com.todo_service.model.entity.Task;
import com.todo_service.model.request.TaskCreateRequest;
import com.todo_service.model.request.TaskUpdateRequest;
import org.springframework.stereotype.Component;

public interface TaskMapper {

    Task createTask(TaskCreateRequest request);
    Task updateTask(Task task, TaskUpdateRequest request);

}
