package com.example.townmarket.common.domain.chat.service;

import com.example.townmarket.common.domain.chat.dto.ChatRoomDto;
import com.example.townmarket.common.domain.chat.dto.ChatRoomListDtailDto;
import com.example.townmarket.common.domain.chat.entity.ChatRoom;
import com.example.townmarket.common.domain.chat.repository.ChatRoomRepository;
import com.example.townmarket.common.domain.product.entity.Product;
import com.example.townmarket.common.domain.product.repository.ProductRepository;
import com.example.townmarket.common.domain.user.entity.User;
import com.example.townmarket.common.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatRoomServiceImpl implements ChatRoomService {

  private final ChatRoomRepository roomRepository;
  private final ProductRepository productRepository;
  private final UserRepository userRepository;

  /*채팅방 생성*/
  @Override
  public void createRoom(Long productId, String username) {
    Product product = productRepository.findById(productId).orElseThrow(
        () -> new IllegalArgumentException("상품이 존재하지 않습니다.")
    );
    User user = userRepository.findByUsername(username).orElseThrow(
        () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
    );

    ChatRoom chatRooms = new ChatRoom(product, user);
    roomRepository.save(chatRooms);
  }

  /* 채팅방 보기 */
  @Override
  public ChatRoomDto getChatRoom(Long roomId, String username) {
    User user = userRepository.findByUsername(username).orElseThrow(
        () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
    );
    ChatRoom room = roomRepository.findById(roomId).orElseThrow(
        () -> new IllegalArgumentException("이미 삭제된 채팅방 입니다.")
    );

    if (user.checkAuthorization(user)) {
      return new ChatRoomDto(room);
    }
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "본인이 속한 채팅만 볼 수 있습니다.");
  }

  /*나의 채팅 리스트*/
  @Override
  public ChatRoomListDtailDto myChatList(Long userId) {
    User user = userRepository.findById(userId).orElseThrow(
        () -> new RuntimeException("회원을 찾을 수 없습니다.")
    );

    return new ChatRoomListDtailDto(user);
  }


  /* 채팅방 삭제 */
  @Override
  public void deleteChat(Long roomId, String username) {
    User user = userRepository.findByUsername(username).orElseThrow(
        () -> new RuntimeException("회원을 찾을 수 없습니다.")
    );
    ChatRoom room = roomRepository.findById(roomId).orElseThrow(
        () -> new RuntimeException("존재하지 않는 방입니다.")
    );

    if (user.checkAuthorization(user)) {
      roomRepository.deleteById(room.getId());
      return;
    }
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "본인이 속한 채팅만 삭제할 수 있습니다.");
  }
}
