package com.todo_service.model.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ApiErrorMessage{
    TASK_WITH_ID_NOT_FOUND("Task by id=%s not found"),

    MAIL_EXCEPTION("mail exception"),
    MAIL_FAILED_TO_READ("Failed to read emails via IMAP"),

    ;

    private String message;

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}