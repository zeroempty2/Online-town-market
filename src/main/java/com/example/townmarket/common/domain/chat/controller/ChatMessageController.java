package com.example.townmarket.common.domain.chat.controller;

import com.example.townmarket.common.domain.chat.dto.ChatMessageDto;
import com.example.townmarket.common.domain.chat.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatMessageController {

  private final ChatMessageService messageService;
  private final SimpMessagingTemplate messagingTemplate;





  @CrossOrigin(value = "http://localhost:8080", methods = RequestMethod.GET)
  @MessageMapping("/send") //여기로 전송되면 메서드 호출 -> WebSocketConfig prefixes 에서 적용한건 앞에 생략 || /pub/send
  public ChatMessageDto chatMessage(ChatMessageDto message) {

    messageService.createChat(message);

    messagingTemplate.convertAndSend(
        "/sub/" + message.getReceiver() + "/product" + message.getProductId(), message);
    return message;
  }

  // 임시 저장 용도
//  @PostMapping("pub/send")
//  public ChatMessageDto sendMessage(@RequestParam("productId") Long productId, @RequestBody ChatMessageDto message) {
//    //채팅 저장
//    messageService.createChat(message);
//    // productId와 message를 이용하여 채팅 메시지 전송
//    messagingTemplate.convertAndSend("/sub/receive/" + message.getReceiver() + "/product" + productId, message);
//    return message;
//  }
}
