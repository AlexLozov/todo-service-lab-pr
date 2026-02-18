package com.todo_service.model.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum WebSocketTopics {
    TOPIC_TASKS("/topic/tasks")

    ;
    private String topic;

    public String getTopic(Object... args) {
        return String.format(topic, args);
    }
}
