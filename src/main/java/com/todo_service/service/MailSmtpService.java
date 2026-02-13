package com.todo_service.service;

import com.todo_service.model.request.mail.MailSendSmtpRequest;

public interface MailSmtpService {

    void sendSimpleEmail(MailSendSmtpRequest request);
}
