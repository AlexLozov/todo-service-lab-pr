package com.todo_service.service.impl;

import com.todo_service.model.constants.ApiErrorMessage;
import com.todo_service.model.exception.TaskNotFoundException;
import com.todo_service.mapper.TaskMapper;
import com.todo_service.model.entity.Task;
import com.todo_service.model.request.task.TaskCreateRequest;
import com.todo_service.model.request.task.TaskUpdateRequest;
import com.todo_service.model.response.PaginationResponse;
import com.todo_service.model.response.task.TaskResponse;
import com.todo_service.repository.TaskRepository;
import com.todo_service.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    @Transactional
    public TaskResponse createTask(TaskCreateRequest request) {
        Task task = taskMapper.createTask(request);
        task.setIsFinished(false);
        taskRepository.save(task);
        return taskMapper.entityToResponse(task);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponse> getTasksList() {
        List<Task> tasks = taskRepository.findAll();
        return taskMapper.entityListToResponse(tasks);
    }

    @Override
    @Transactional(readOnly = true)
    public TaskResponse getTaskById(Integer id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(ApiErrorMessage.TASK_WITH_ID_NOT_FOUND.getMessage(id)));
        return taskMapper.entityToResponse(task);
    }

    @Override
    @Transactional
    public TaskResponse updateTask(Integer id, TaskUpdateRequest request) {
        Task task = taskRepository
                .findById(id)
                .orElseThrow(() -> new TaskNotFoundException(ApiErrorMessage.TASK_WITH_ID_NOT_FOUND.getMessage(id)));

        Task updatedTask = taskMapper.updateTask(task, request);
        return taskMapper.entityToResponse(updatedTask);
    }

    @Override
    @Transactional
    public void deleteTask(Integer id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(ApiErrorMessage.TASK_WITH_ID_NOT_FOUND.getMessage(id)));

        taskRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public PaginationResponse<TaskResponse> getTaskPage(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        Page<Task> tasksPage = taskRepository.findAll(pageable);

        List<TaskResponse> tasks = tasksPage.stream()
                .map(taskMapper::entityToResponse)
                .toList();

        PaginationResponse.Pagination pagination = PaginationResponse.Pagination.builder()
                .page(page)
                .limit(limit)
                .pages(tasksPage.getTotalPages())
                .total(tasksPage.getTotalElements())
                .build();

        return PaginationResponse.<TaskResponse>builder()
                .content(tasks)
                .pagination(pagination)
                .build();
    }


}
