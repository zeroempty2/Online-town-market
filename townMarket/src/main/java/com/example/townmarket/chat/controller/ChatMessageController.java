package com.example.townmarket.chat.controller;

import com.example.townmarket.chat.dto.ChatMessageDto;
import com.example.townmarket.chat.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatMessageController {

  private final ChatMessageService messageService;
  private final SimpMessageSendingOperations simpMessageSendingOperations;

  @MessageMapping("/send") //여기로 전송되면 메서드 호출 -> WebSocketConfig prefixes 에서 적용한건 앞에 생략
  public ChatMessageDto chatMessage(ChatMessageDto message) {
    //채팅 저장
    messageService.createChat(message);

    simpMessageSendingOperations.convertAndSend(
        "/sub/receive/" + message.getReceiver() + "/product" + message.getProductId(), message);
    return message;
    //구독하고 있는 장소로 메시지 전송 (목적지)  -> WebSocketConfig Broker 에서 적용한건 앞에 붙어줘야됨
    // sendTo : 브라우저에서 요청
    // simpMessagingTemplate : 어떤 환경에서도 사용가능한 API
  }
}
