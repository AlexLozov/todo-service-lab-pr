package com.todo_service.model.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {

    private String message;
    private T payload;
    private boolean success;
    private LocalDateTime timestamp;
    private int status;

    public static <T> ApiResponse<T> success(T payload) {
        return ApiResponse.<T>builder()
                .message("success")
                .payload(payload)
                .success(true)
                .timestamp(LocalDateTime.now())
                .status(200)
                .build();
    }

    public static <T> ApiResponse<T> error(String message, int status) {
        return ApiResponse.<T>builder()
                .message(message)
                .payload(null)
                .success(false)
                .timestamp(LocalDateTime.now())
                .status(status)
                .build();
    }
}
