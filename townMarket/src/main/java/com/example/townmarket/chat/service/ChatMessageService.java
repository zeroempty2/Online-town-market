package com.example.townmarket.chat.service;

import com.example.townmarket.chat.dto.ChatMessageDto;

public interface ChatMessageService {

  void createChat(ChatMessageDto message);
}