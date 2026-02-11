package com.todo_service.mapper.impl;

import com.todo_service.mapper.TaskMapper;
import com.todo_service.model.entity.Task;
import com.todo_service.model.request.task.TaskCreateRequest;
import com.todo_service.model.request.task.TaskUpdateRequest;
import com.todo_service.model.response.task.TaskResponse;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Override
    public TaskResponse entityToResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .isFinished(task.getIsFinished())
                .build();
    }

    @Override
    public List<TaskResponse> entityListToResponse(List<Task> tasks) {
        return tasks.stream()
                .map(this::entityToResponse)
                .toList();
    }


}
