package com.example.townmarket.chat.dto;

import com.example.townmarket.chat.entity.ChatMessage;
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

  public ChatMessageDto(ChatMessage message) {
    this.roomId = message.getRoom().getId();
    this.productId = message.getProductId();
    this.sender = message.getSender();
    this.receiver = message.getReceiver();
    this.message = message.getMessage();
  }
}
