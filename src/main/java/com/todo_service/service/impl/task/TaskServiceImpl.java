package com.todo_service.service.impl.task;

import com.todo_service.model.constants.ApiErrorMessage;
import com.todo_service.model.constants.TaskStatus;
import com.todo_service.model.constants.WebSocketTopics;
import com.todo_service.model.exception.TaskNotFoundException;
import com.todo_service.mapper.TaskMapper;
import com.todo_service.model.entity.Task;
import com.todo_service.model.request.task.TaskCreateRequest;
import com.todo_service.model.request.task.TaskUpdateRequest;
import com.todo_service.model.response.PaginationResponse;
import com.todo_service.model.response.task.TaskNotificationResponse;
import com.todo_service.model.response.task.TaskResponse;
import com.todo_service.repository.TaskRepository;
import com.todo_service.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final SimpMessagingTemplate messagingTemplate; // - для websocket

    @Override
    @Transactional
    public TaskResponse createTask(TaskCreateRequest request) {
        Task task = taskMapper.createTask(request);
        task.setIsFinished(false);
        taskRepository.save(task);

        TaskNotificationResponse taskNotificationResponse = taskMapper.toTaskNotificationResponse(task, TaskStatus.CREATED);
        messagingTemplate.convertAndSend(WebSocketTopics.TOPIC_TASKS.getTopic(), taskNotificationResponse);

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

        TaskNotificationResponse taskNotificationResponse = taskMapper.toTaskNotificationResponse(updatedTask, TaskStatus.UPDATED);
        messagingTemplate.convertAndSend(WebSocketTopics.TOPIC_TASKS.getTopic(), taskNotificationResponse);

        return taskMapper.entityToResponse(updatedTask);
    }

    @Override
    @Transactional
    public void deleteTask(Integer id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(ApiErrorMessage.TASK_WITH_ID_NOT_FOUND.getMessage(id)));

        TaskNotificationResponse taskNotificationResponse = taskMapper.toTaskNotificationResponse(task, TaskStatus.DELETED);
        messagingTemplate.convertAndSend(WebSocketTopics.TOPIC_TASKS.getTopic(), taskNotificationResponse);

        taskRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public PaginationResponse<TaskResponse> getTaskPageByDate(int page, int limit) {

        Pageable pageable = PageRequest.of(
                page,
                limit,
                Sort.by(Sort.Direction.DESC, "date"));

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

    @Override
    @Transactional(readOnly = true)
    public PaginationResponse<TaskResponse> getTaskPageByStatusAndSortByDate(
            Boolean isFinished,
            int page,
            int limit) {

        Pageable pageable = PageRequest.of(
                page,
                limit,
                Sort.by(Sort.Direction.DESC, "date"));

        Page<Task> tasksPage = taskRepository.findAllByIsFinished(isFinished, pageable);

        List<TaskResponse> tasks = tasksPage.stream()
                .map(taskMapper::entityToResponse)
                .toList();

        PaginationResponse.Pagination pagination = PaginationResponse.Pagination.builder()
                .limit(limit)
                .page(page)
                .total(tasksPage.getTotalElements())
                .pages(tasksPage.getTotalPages())
                .build();

        return PaginationResponse.<TaskResponse>builder()
                .pagination(pagination)
                .content(tasks)
                .build();
    }


}
