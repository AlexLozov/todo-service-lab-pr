package com.todo_service.model.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ApiErrorMessage{
    TASK_WITH_ID_NOT_FOUND("Task by id=%s not found"),

    ;

    private String message;

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}