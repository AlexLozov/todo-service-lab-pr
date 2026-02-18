package com.todo_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // - включаем поддержку WebSocket с брокером сообщений STOMP
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // - фронт подключится к ws://localhost:8080/ws
        registry.addEndpoint("/ws") // Адрес для подключения
                .setAllowedOrigins("http://localhost:3000") // - cors для фронта
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // - для рассылки сообщения всем пользователям
        registry.enableSimpleBroker("/topic");
        // - от клиента к серверу
        registry.setApplicationDestinationPrefixes("/app");
    }
}