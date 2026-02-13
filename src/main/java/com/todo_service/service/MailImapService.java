package com.todo_service.service;

import com.todo_service.model.response.mail.MailImapResponse;

import java.util.List;

public interface MailImapService {
    List<MailImapResponse> readLastEmails(int limit);
}
