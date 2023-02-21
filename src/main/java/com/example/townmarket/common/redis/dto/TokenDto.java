package com.example.townmarket.common.redis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TokenDto {

  private String accessToken;
  private String refreshToken;

  public boolean checkRefreshToken(String token) {
    return refreshToken.equals(token);
  }

}
