package com.todo_service.service.impl.mail;

import com.todo_service.model.constants.ApiErrorMessage;
import com.todo_service.model.request.mail.MailSendSmtpRequest;
import com.todo_service.service.MailSmtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSmtpServiceImpl implements MailSmtpService {

    private final JavaMailSender mailSender; // - для SMTP - отправка сообщения

    @Override
    public void sendSimpleEmail(MailSendSmtpRequest request) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(request.getTo());
            message.setSubject(request.getSubject());
            message.setText(request.getText());

            mailSender.send(message);
        } catch (MailException ex){
            throw new MailSendException(ApiErrorMessage.MAIL_EXCEPTION.getMessage(), ex);
        }
    }

}
