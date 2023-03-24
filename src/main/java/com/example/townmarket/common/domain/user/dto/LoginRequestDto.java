package com.example.townmarket.common.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class LoginRequestDto {


  private String username;


  private String password;

  public LoginRequestDto(String username, String password) {
    this.username = username;
    this.password = password;
  }
}
