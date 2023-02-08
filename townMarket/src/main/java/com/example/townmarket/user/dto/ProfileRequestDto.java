package com.example.townmarket.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfileRequestDto {

  private String nickname;
  private String img_url;

}
