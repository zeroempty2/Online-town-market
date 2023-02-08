package com.example.townmarket.user.dto;

import com.example.townmarket.user.entity.Profile;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProfileResponseDto {

  private String newNickname;
  private String img_url;

  public ProfileResponseDto(Profile profile) {
    this.newNickname = profile.getNickName();
    this.img_url = profile.getImg_url();
  }
}
