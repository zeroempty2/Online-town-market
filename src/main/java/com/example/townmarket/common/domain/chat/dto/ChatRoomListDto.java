package com.example.townmarket.common.domain.chat.dto;

import com.example.townmarket.common.domain.chat.entity.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomListDto {

  private long roomId;
  private String profileImg;
  private String nickname;
  private String region;
  private String productName;

  public ChatRoomListDto(ChatRoom room) {
    this.roomId = room.getId();
    this.productName = room.getProduct().getProductName();
    this.profileImg = room.getProduct().getUser().getProfile().getImg_url();
    this.nickname = room.getProduct().getUser().getProfile().getNickName();
    this.region = room.getProduct().getUser().getRegion();
  }
}