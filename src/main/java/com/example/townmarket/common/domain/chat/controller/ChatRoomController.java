package com.example.townmarket.common.domain.chat.controller;

import com.example.townmarket.common.domain.chat.dto.ChatRoomDto;
import com.example.townmarket.common.domain.chat.dto.ChatRoomListDtailDto;
import com.example.townmarket.common.domain.chat.service.ChatRoomService;
import com.example.townmarket.common.dto.StatusResponse;
import com.example.townmarket.common.enums.ResponseMessages;
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
  public ResponseEntity<StatusResponse> createRoom(@PathVariable Long productId,
      @AuthenticationPrincipal UserDetails userDetails) {
    roomService.createRoom(productId, userDetails.getUsername());
    return ResponseEntity.ok().body(StatusResponse.valueOf(ResponseMessages.CREATED_SUCCESS));
  }

  /* 해당 채팅방 보기 */
  @GetMapping("/chatrooms/{roomId}")
  public ResponseEntity<ChatRoomDto> getChatRoom(@PathVariable Long roomId,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    ChatRoomDto room = roomService.getChatRoom(roomId, userDetails.getUsername());
    return ResponseEntity.status(HttpStatus.OK).body(room);
  }

  /*나의 채팅 리스트*/
  @GetMapping("/chatrooms")
  public ResponseEntity<ChatRoomListDtailDto> myChatList(
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    roomService.myChatList(userDetails.getUserId());
    return ResponseEntity.status(HttpStatus.OK).body(roomService.myChatList(userDetails.getUserId()));
  }

  /* 채팅방 삭제 */
  @DeleteMapping("/chatroom/{roomId}")
  public ResponseEntity<StatusResponse> deleteChat(@PathVariable Long roomId, @AuthenticationPrincipal
  UserDetailsImpl userDetails) {
    roomService.deleteChat(roomId, userDetails.getUsername());
    StatusResponse statusResponse = StatusResponse.valueOf(ResponseMessages.DELETE_SUCCESS);
    return new ResponseEntity<>(statusResponse, HttpStatus.NO_CONTENT);
  }
}
