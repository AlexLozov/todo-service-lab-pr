package com.todo_service.model.request;


import lombok.Data;

import java.io.Serializable;

@Data
public class TaskUpdateRequest implements Serializable {

    private String title;
    private String description;
}
