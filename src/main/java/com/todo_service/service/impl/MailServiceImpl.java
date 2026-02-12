package com.todo_service.service.impl;

import com.todo_service.model.constants.ApiErrorMessage;
import com.todo_service.model.dto.MailDTO;
import com.todo_service.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender; // - для SMTP - отправка сообщения

    @Override
    public void sendSimpleEmail(MailDTO mailDTO) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(mailDTO.getTo());
            message.setSubject(mailDTO.getSubject());
            message.setText(mailDTO.getText());

            mailSender.send(message);
        } catch (MailException ex){
            throw new MailSendException(ApiErrorMessage.MAIL_EXCEPTION.getMessage(), ex);
        }
    }

}
