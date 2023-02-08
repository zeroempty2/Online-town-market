package com.example.townmarket.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignupRequestDto {

  private String username;
  private String password;
  private String phoneNumber;
  private String region;

}
