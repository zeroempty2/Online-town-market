package com.example.townmarket.common.domain.chat.repository;

import com.example.townmarket.common.domain.chat.dto.ChatRoomResponse;
import java.util.List;

public interface ChatRoomRepositoryQuery {

  List<ChatRoomResponse> searchChatRoomByUsername(String username);
}
