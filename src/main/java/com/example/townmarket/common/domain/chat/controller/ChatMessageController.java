package com.example.townmarket.common.domain.chat.controller;

import com.example.townmarket.common.domain.chat.dto.ChatMessageDto;
import com.example.townmarket.common.domain.chat.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatMessageController {

  private final ChatMessageService messageService;
//  private final SimpMessagingTemplate messagingTemplate;





  @CrossOrigin(value = "http://localhost:8080", methods = RequestMethod.GET)
  @MessageMapping("/{roomId}") //여기로 전송되면 메서드 호출 -> WebSocketConfig prefixes 에서 적용한건 앞에 생략 || /pub/send
  @SendTo("/sub/{roomId}")
  public ChatMessageDto chatMessage(@DestinationVariable Long roomId, ChatMessageDto message) {

    messageService.createChat(message, roomId);

//    messagingTemplate.convertAndSend(
//        "/sub/" + message.getReceiver() + "/product" + message.getProductId(), message);
    return message;
  }
}
