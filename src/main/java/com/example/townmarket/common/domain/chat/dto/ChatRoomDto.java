package com.example.townmarket.common.domain.chat.dto;

import com.example.townmarket.common.domain.chat.entity.ChatMessage;
import com.example.townmarket.common.domain.chat.entity.ChatRoom;
import com.example.townmarket.common.domain.product.entity.Product.ProductEnum;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomDto {

  private String roomName;
  private long productPrice;

  private ProductEnum productEnum;

  private Set<ChatMessageDto> message;

  public ChatRoomDto(ChatRoom room) {
    this.productPrice = room.getProduct().getProductPrice();
    this.roomName = room.getProductName();
    this.productEnum = room.getProduct().getProductEnum();
    Set<ChatMessageDto> messageList = new LinkedHashSet<>();
    for (ChatMessage messages : room.getMessage()) {
      messageList.add(new ChatMessageDto(messages));
    }
    this.message = messageList;
  }
}
