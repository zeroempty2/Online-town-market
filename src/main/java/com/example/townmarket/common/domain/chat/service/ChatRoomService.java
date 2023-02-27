package com.example.townmarket.common.domain.chat.service;

import com.example.townmarket.common.domain.chat.dto.ChatRoomDto;
import com.example.townmarket.common.domain.chat.dto.ChatRoomListDtailDto;
import com.example.townmarket.common.domain.chat.dto.ChatRoomResponse;

public interface ChatRoomService {

  ChatRoomResponse createRoom(Long productId, String username);

  void deleteChat(Long roomId, String username);

  ChatRoomDto getChatRoom(Long roomId, String username);

  ChatRoomListDtailDto myChatList(Long userId);
}
