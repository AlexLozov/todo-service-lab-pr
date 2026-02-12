package com.todo_service.controller;

import com.todo_service.model.dto.MailDTO;
import com.todo_service.model.response.ApiResponse;
import com.todo_service.service.MailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
@Tag(name = "Mail API", description = "методы для работы с почтой")
@RequiredArgsConstructor
public class MailController {
    private final MailService mailService;

    @PostMapping
    public ResponseEntity<ApiResponse<String>> sendMail(@Valid @RequestBody MailDTO request){
        mailService.sendSimpleEmail(request);
        return ResponseEntity.ok(ApiResponse.success("Email sent successfully"));
    }


}
