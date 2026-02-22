package com.todo_service.model.response.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskResponse {
    private Integer id;
    private String title;
    private String description;
    private Boolean isFinished;
    private LocalDateTime date;
}
