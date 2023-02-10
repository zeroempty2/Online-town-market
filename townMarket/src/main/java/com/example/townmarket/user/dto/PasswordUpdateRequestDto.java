package com.example.townmarket.user.dto;


import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasswordUpdateRequestDto { // Update로 수정 부탁드립니다.

  @Size(min = 8, max = 15)
  @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$", message = "소문자와 대문자 그리고 숫자를 포함하여 8자 이상 15자 이하로 적어주세요.")
  private String password;

}
