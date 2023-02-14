package com.example.townmarket.user.dto;


import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasswordUpdateRequestDto {

  @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$", message = "소문자와 대문자 그리고 숫자를 포함하여 8자 이상 15자 이하로 적어주세요.")
  private String password;

}
