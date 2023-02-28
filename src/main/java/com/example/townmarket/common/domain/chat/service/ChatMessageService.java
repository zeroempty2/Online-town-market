package com.example.townmarket.common.domain.chat.service;

import com.example.townmarket.common.domain.chat.dto.ChatRoomDto;

public interface ChatMessageService {

  void createChat(ChatRoomDto message);
}
