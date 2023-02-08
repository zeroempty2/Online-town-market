package com.example.townmarket.user.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequestDto {

  @Size(min = 4, max = 10)
  @Pattern(regexp = "^[a-z0-9]*$", message = "소문자와 숫자를 포함하여 4자 이상 10자 이하로 적어주세요.")
  private String username;
  @Size(min= 8, max= 15)
  @Pattern(regexp = "^[a-zA-Z0-9]$", message = "소문자와 대문자 그리고 숫자를 포함하여 8자 이상 15자 이하로 적어주세요.")
  private String password;

}
