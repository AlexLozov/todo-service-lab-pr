package com.todo_service.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "mail.imap")
public class ImapProperties {

    private String host;
    private int port;
    private boolean sslEnable;
    private String protocol;
}
