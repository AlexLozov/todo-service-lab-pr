package com.todo_service.controller;

import com.todo_service.model.response.ApiResponse;
import com.todo_service.model.response.mail.MailImapResponse;
import com.todo_service.service.MailImapService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mail/read")
@Tag(name = "Mail API for read message", description = "методы для чтения сообщений с почты")
@RequiredArgsConstructor
public class MailReadController {

    private final MailImapService mailImapService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<MailImapResponse>>> getLastEmails() {
        return ResponseEntity.ok(ApiResponse.success(mailImapService.readLastEmails(3)));
    }
}
