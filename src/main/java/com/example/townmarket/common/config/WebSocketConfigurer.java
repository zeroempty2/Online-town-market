package com.example.townmarket.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfigurer implements WebSocketMessageBrokerConfigurer {

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {

    registry.addEndpoint("/chat")
        .setAllowedOriginPatterns("http://localhost:8080")
        //보안상의 문제로 전체를 허용하는 것보다 직접 하나씩 지정해주어야 함
        .withSockJS()
        .setClientLibraryUrl("https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.2/sockjs.js");
    // sockjs CDN 주소를 입력해도 무관

  }


  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {

    config.enableSimpleBroker("/receive"); // 받는 쪽
    config.setApplicationDestinationPrefixes("/send"); // 보내는 쪽
  }
}
