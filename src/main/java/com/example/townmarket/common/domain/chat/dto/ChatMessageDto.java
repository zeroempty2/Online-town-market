package com.example.townmarket.common.domain.chat.dto;

import com.example.townmarket.common.domain.chat.entity.ChatMessage;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDto {

  private long roomId;
  private long productId;
  private String sender;
  private String receiver;
  private String message;
  private LocalDateTime sendDate;

  public ChatMessageDto(ChatMessage message) {
    this.roomId = message.getRoom().getId();
    this.productId = message.getProductId();
    this.sender = message.getSender();
    this.receiver = message.getReceiver();
    this.message = message.getMessage();
    this.sendDate = message.getSendDate();
  }
}
