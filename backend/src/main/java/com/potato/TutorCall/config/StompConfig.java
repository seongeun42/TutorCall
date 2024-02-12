package com.potato.TutorCall.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class StompConfig implements WebSocketMessageBrokerConfigurer {

    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 연결 요청
        registry.addEndpoint("/ws-hello")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 구독 요청 prefix
        config.enableSimpleBroker("/sub");
        // 발행 요청 prefix
        config.setApplicationDestinationPrefixes("/pub");
    }

}
