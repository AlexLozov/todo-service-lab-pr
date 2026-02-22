package com.todo_service.model.response.task;


import com.todo_service.model.constants.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskNotificationResponse {
    private Integer id;
    private String title;
    private String description;
    private LocalDateTime localDateTime;
    private TaskStatus status;
    private LocalDateTime date;
}
