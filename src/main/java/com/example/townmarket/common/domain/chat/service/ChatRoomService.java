package com.example.townmarket.common.domain.chat.service;

import com.example.townmarket.common.domain.chat.dto.ChatRoomDto;
import com.example.townmarket.common.domain.chat.dto.ChatRoomResponse;
import java.util.List;

public interface ChatRoomService {

  List<ChatRoomResponse> createRoom(Long productId, String username);

  void deleteChat(Long roomId, String username);

  ChatRoomDto getChatRoom(Long roomId, String username);

  List<ChatRoomResponse> myChatList(String username);
}
