package com.todo_service.model.response.mail;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MailImapResponse {
    private String from;
    private String subject;
    private LocalDateTime receivedDate;
}
