package com.example.townmarket.common.domain.chat.repository;

import com.example.townmarket.common.domain.chat.dto.ChatRoomResponse;

public interface ChatRoomRepositoryQuery {

  ChatRoomResponse searchChatRoomByChatRoomId(Long chatRoomId);
}
