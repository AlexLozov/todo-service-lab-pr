package com.todo_service.model.exception;

public class MailReadException extends RuntimeException {
    public MailReadException(String message) {
        super(message);
    }

    public MailReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
