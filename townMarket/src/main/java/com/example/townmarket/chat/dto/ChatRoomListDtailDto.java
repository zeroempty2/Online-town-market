package com.example.townmarket.chat.dto;

import com.example.townmarket.chat.entity.ChatRoom;
import com.example.townmarket.user.entity.User;
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
public class ChatRoomListDtailDto {

  private long userId;
  private Set<ChatRoomListDto> list;


  public ChatRoomListDtailDto(User user) {
    this.userId = user.getId();
    Set<ChatRoomListDto> roomlist = new LinkedHashSet<>();
    for (ChatRoom rooms : user.getChatRooms()) {
      roomlist.add(new ChatRoomListDto(rooms));
    }
    this.list = roomlist;
  }
}

