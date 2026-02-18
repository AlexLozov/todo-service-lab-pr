package com.todo_service.mapper;

import com.todo_service.model.constants.TaskStatus;
import com.todo_service.model.entity.Task;
import com.todo_service.model.request.task.TaskCreateRequest;
import com.todo_service.model.request.task.TaskUpdateRequest;
import com.todo_service.model.response.task.TaskNotificationResponse;
import com.todo_service.model.response.task.TaskResponse;

import java.util.List;

public interface TaskMapper {

    Task createTask(TaskCreateRequest request);
    Task updateTask(Task task, TaskUpdateRequest request);
    TaskResponse entityToResponse(Task task);
    List<TaskResponse> entityListToResponse(List<Task> tasks);
    TaskNotificationResponse toTaskNotificationResponse(Task task, TaskStatus status);


}
