package com.example.townmarket.common.domain.chat.dto;

import lombok.Getter;

@Getter
public class ChatRoomResponse {

  private Long productId;
  private String profileImg;
  private String nickname;
  private String region;
  private String productName;
  private Long roomId;

  public ChatRoomResponse(Long productId, String profileImg, String nickname, String region,
      String productName, Long roomId) {
    this.productId = productId;
    this.profileImg = profileImg;
    this.nickname = nickname;
    this.region = region;
    this.productName = productName;
    this.roomId = roomId;
  }
}
