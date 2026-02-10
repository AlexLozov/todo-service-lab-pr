package com.todo_service.service.impl;

import com.todo_service.model.constants.ApiErrorMessage;
import com.todo_service.model.exception.TaskNotFoundException;
import com.todo_service.mapper.TaskMapper;
import com.todo_service.model.entity.Task;
import com.todo_service.model.request.TaskCreateRequest;
import com.todo_service.model.request.TaskUpdateRequest;
import com.todo_service.repository.TaskRepository;
import com.todo_service.service.TaskService;
import lombok.RequiredArgsConstructor;
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
    public Task createTask(TaskCreateRequest request) {
        Task task = taskMapper.createTask(request);
        return taskRepository.save(task);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getTasksList() {
        return taskRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Task getTaskById(Integer id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(ApiErrorMessage.TASK_WITH_ID_NOT_FOUND.getMessage(id)));
    }

    @Override
    @Transactional
    public Task updateTask(Integer id, TaskUpdateRequest request) {
        Task task = taskRepository
                .findById(id)
                .orElseThrow(() -> new TaskNotFoundException(ApiErrorMessage.TASK_WITH_ID_NOT_FOUND.getMessage(id)));

        Task updatedTask = taskMapper.updateTask(task, request);
        taskRepository.save(updatedTask);
        return updatedTask;
    }

    @Override
    @Transactional
    public boolean deleteTask(Integer id) {
        if(taskRepository.existsById(id)){
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public void checkTaskExists(Integer id) {
        if(!taskRepository.existsById(id)){
            throw new TaskNotFoundException(
                    ApiErrorMessage.TASK_WITH_ID_NOT_FOUND.getMessage(id)
            );
        }
    }

}
