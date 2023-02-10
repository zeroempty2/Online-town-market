package com.example.townmarket.chat.dto;

import com.example.townmarket.chat.entity.ChatMessage;
import com.example.townmarket.chat.entity.ChatRoom;
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

  private Set<ChatMessageDto> message;

  public ChatRoomDto(ChatRoom room) {
    this.roomName = room.getRoomName();
    Set<ChatMessageDto> messageList = new LinkedHashSet<>();
    for (ChatMessage messages : room.getMessage()) {
      messageList.add(new ChatMessageDto(messages));
    }
    this.message = messageList;
  }
}
