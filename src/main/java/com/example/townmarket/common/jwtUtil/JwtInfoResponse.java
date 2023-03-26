package com.example.townmarket.common.jwtUtil;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JwtInfoResponse {
  private String username;
  private String refreshToken;

}
