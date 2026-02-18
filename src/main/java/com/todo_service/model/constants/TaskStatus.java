package com.todo_service.model.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TaskStatus {
    CREATED("CREATED"),
    UPDATED("UPDATED"),
    DELETED("DELETED"),
    FINISHED("FINISHED")

    ;
    private String status;

    public String getStatus(Object... args) {
        return String.format(status, args);
    }
}
