package com.todo_service.service;

import com.todo_service.model.dto.MailDTO;

public interface MailService {

    void sendSimpleEmail(MailDTO mailDTO);
}
