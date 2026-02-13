package com.todo_service.service.impl;

import com.todo_service.config.ImapProperties;
import com.todo_service.model.constants.ApiErrorMessage;
import com.todo_service.model.exception.MailReadException;
import com.todo_service.model.response.mail.MailImapResponse;
import com.todo_service.service.MailImapService;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class MailImapServiceImpl implements MailImapService {

    private final ImapProperties imapProperties;
    private final MailProperties mailProperties;

    @Override
    public List<MailImapResponse> readLastEmails(int limit) {

        List<MailImapResponse> emails = new ArrayList<>();

        Properties props = new Properties();
        props.put("mail.store.protocol", imapProperties.getProtocol());
        props.put("mail.imap.host", imapProperties.getHost());
        props.put("mail.imap.port", String.valueOf(imapProperties.getPort()));
        props.put("mail.imap.ssl.enable", String.valueOf(imapProperties.isSslEnable()));

        try {
            Session session = Session.getInstance(props);
            Store store = session.getStore(imapProperties.getProtocol());

            store.connect(
                    imapProperties.getHost(),
                    mailProperties.getUsername(),
                    mailProperties.getPassword()
            );

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            int total = inbox.getMessageCount();
            int start = Math.max(1, total - limit + 1);

            Message[] messages = total == 0
                    ? new Message[0]
                    : inbox.getMessages(start, total);

            for (Message msg : messages) {

                String from = Arrays.toString(msg.getFrom());
                String subject = msg.getSubject();

                LocalDateTime date = msg.getReceivedDate() == null
                        ? null
                        : msg.getReceivedDate()
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();

                emails.add(new MailImapResponse(from, subject, date));
            }

            inbox.close(false);
            store.close();

        } catch (Exception ex) {
            throw new MailReadException(ApiErrorMessage.MAIL_FAILED_TO_READ.getMessage(), ex);
        }

        return emails;
    }
}
