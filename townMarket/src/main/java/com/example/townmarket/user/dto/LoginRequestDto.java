package com.example.townmarket.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequestDto {

  private String userId;
  private String password;

}
