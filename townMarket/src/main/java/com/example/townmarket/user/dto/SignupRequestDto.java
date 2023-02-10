package com.example.townmarket.user.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignupRequestDto {

  @Size(min = 4, max = 10)
  @Pattern(regexp = "^[a-z0-9]*$", message = "소문자와 숫자를 포함하여 4자 이상 10자 이하로 적어주세요.")
  @NotEmpty(message = "아이디를 입력해주세요.")
  private String username;

  @Size(min= 8, max= 15)
  @Pattern(regexp = "^[a-zA-Z0-9]$", message = "소문자와 대문자 그리고 숫자를 포함하여 8자 이상 15자 이하로 적어주세요.")
  @NotEmpty(message = "비밀번호를 입력해주세요.")
  private String password;

  @Pattern(regexp = "^[0-9]{3}-[0-9]{3,4}-[0-9]{4}$", message = "숫자와 - 를 포함하여 휴대폰 번호를 적어주세요.")
  @NotEmpty(message = "휴대폰 번호를 입력해주세요.")
  private String phoneNumber;

  @Size(min = 4, max = 10)
  @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$")
  private String email;

  @NotEmpty(message = "거래할 지역의 읍,면,동 까지 적어주세요.")
  private String region;

  private String nickname;


}
