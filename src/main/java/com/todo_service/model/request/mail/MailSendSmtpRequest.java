package com.todo_service.model.request.mail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailSendSmtpRequest {
    private String to;
    private String subject;
    private String text;
}
