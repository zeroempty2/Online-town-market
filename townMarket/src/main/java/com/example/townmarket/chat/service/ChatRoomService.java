package com.example.townmarket.chat.service;

import com.example.townmarket.chat.dto.ChatRoomDto;
import com.example.townmarket.chat.dto.ChatRoomListDtailDto;

public interface ChatRoomService {

  void createRoom(Long productId, String username);

  void deleteChat(Long roomId, String username);

  ChatRoomDto getChatRoom(Long roomId, String username);

  ChatRoomListDtailDto myChatList(Long userId);
}
