package com.todo_service.mapper.impl;

import com.todo_service.mapper.TaskMapper;
import com.todo_service.model.entity.Task;
import com.todo_service.model.request.TaskCreateRequest;
import com.todo_service.model.request.TaskUpdateRequest;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public Task createTask(TaskCreateRequest request) {
        return Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .build();
    }

    @Override
    public Task updateTask(Task task, TaskUpdateRequest request) {
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        return task;
    }
}
