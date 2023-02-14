package com.example.townmarket.common.domain.user.dto;

import com.example.townmarket.common.domain.user.entity.Profile;
import lombok.Getter;

@Getter
public class ProfileResponseDto {

  private String nickname;
  private String img_url;

  public ProfileResponseDto(Profile profile) {
    this.nickname = profile.getNickName();
    this.img_url = profile.getImg_url();
  }
}
