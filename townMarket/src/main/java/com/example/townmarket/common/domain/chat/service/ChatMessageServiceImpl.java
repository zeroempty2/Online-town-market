package com.example.townmarket.common.domain.chat.service;

import com.example.townmarket.common.domain.chat.dto.ChatMessageDto;
import com.example.townmarket.common.domain.chat.entity.ChatMessage;
import com.example.townmarket.common.domain.chat.entity.ChatRoom;
import com.example.townmarket.common.domain.chat.repository.ChatMessageRepository;
import com.example.townmarket.common.domain.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {

  private final ChatMessageRepository messageRepository;
  private final ChatRoomRepository roomRepository;

  /**
   * 채팅 생성
   */
  public void createChat(ChatMessageDto message) {

    ChatRoom room = roomRepository.findById(message.getRoomId()).orElseThrow(
        () -> new IllegalArgumentException("채팅방이 존재하지 않습니다.")
    );

    if (message.getProductId() != room.getProductId()) {
      throw new IllegalArgumentException("해당 상품의 채팅방이 존재하지 않습니다.");
    } else {
      ChatMessage messages = new ChatMessage(message.getSender(), message.getReceiver(),
          message.getMessage(), room);
      messageRepository.save(messages);
    }
  }
}
