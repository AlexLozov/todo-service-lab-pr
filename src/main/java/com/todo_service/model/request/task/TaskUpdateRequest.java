package com.todo_service.model.request.task;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class TaskUpdateRequest implements Serializable {
    @NotEmpty
    @Size(max = 64)
    private String title;

    @Size(max = 128)
    private String description;

    private boolean isFinished;
}
