package com.example.townmarket.common.domain.chat.controller;

import com.example.townmarket.common.domain.chat.dto.ChatRoomDto;
import com.example.townmarket.common.domain.chat.dto.ChatRoomListDtailDto;
import com.example.townmarket.common.domain.chat.service.ChatRoomService;
import com.example.townmarket.common.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatRoomController {

  private final ChatRoomService roomService;

  /* 채팅방 등록 */
  @PostMapping("/chatroom/{productId}")
  public ResponseEntity<String> createRoom(@PathVariable Long productId,
      @AuthenticationPrincipal UserDetails userDetails) {
    roomService.createRoom(productId, userDetails.getUsername());
    return new ResponseEntity<>("채팅방 생성 완료", HttpStatus.CREATED);
  }

  /* 해당 채팅방 보기 */
  @GetMapping("/chatroom/{roomId}")
  public ResponseEntity<ChatRoomDto> getChatRoom(@PathVariable Long roomId,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    ChatRoomDto room = roomService.getChatRoom(roomId, userDetails.getUsername());
    return ResponseEntity.status(HttpStatus.OK).body(room);
  }

  /*나의 채팅 리스트*/
  @GetMapping("/chatrooms/{userId}")
  public ResponseEntity<ChatRoomListDtailDto> myChatList(@PathVariable Long userId,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    if (!userId.equals(userDetails.getUserId())) {
      new ResponseEntity<>("채팅 리스트는 본인만 조회할 수 있습니다.", HttpStatus.FORBIDDEN);
    }
    ChatRoomListDtailDto roomList = roomService.myChatList(userDetails.getUserId());
    return ResponseEntity.status(HttpStatus.OK).body(roomList);
  }

  /* 채팅방 삭제 */
  @DeleteMapping("/chatroom/{roomId}")
  public ResponseEntity<String> deleteChat(@PathVariable Long roomId, @AuthenticationPrincipal
  UserDetailsImpl userDetails) {
    roomService.deleteChat(roomId, userDetails.getUsername());
    return new ResponseEntity<>("채팅 삭제가 완료되었습니다.", HttpStatus.OK);
  }
}
