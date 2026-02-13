package com.todo_service.controller;

import com.todo_service.model.request.mail.MailSendSmtpRequest;
import com.todo_service.model.response.ApiResponse;
import com.todo_service.service.MailSmtpService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail/sent")
@Tag(name = "Mail API for sent message", description = "методы для отправки сообщений на почту")
@RequiredArgsConstructor
public class MailSendController {
    private final MailSmtpService mailSmtpService;

    @PostMapping
    public ResponseEntity<ApiResponse<String>> sendMail(@Valid @RequestBody MailSendSmtpRequest request){
        mailSmtpService.sendSimpleEmail(request);
        return ResponseEntity.ok(ApiResponse.success("Email sent successfully"));
    }


}
