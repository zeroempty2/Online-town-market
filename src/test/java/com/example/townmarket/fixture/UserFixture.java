package com.example.townmarket.fixture;

import com.example.townmarket.common.domain.user.dto.DuplicateCheckRequestDto;
import com.example.townmarket.common.domain.user.dto.DuplicateCheckResponseDto;
import com.example.townmarket.common.domain.user.dto.LoginRequestDto;
import com.example.townmarket.common.domain.user.dto.PasswordUpdateRequestDto;
import com.example.townmarket.common.domain.user.dto.ProfileResponseDto;
import com.example.townmarket.common.domain.user.dto.RegionUpdateRequestDto;
import com.example.townmarket.common.domain.user.dto.SignupRequestDto;
import com.example.townmarket.common.domain.user.entity.User;
import com.example.townmarket.common.enums.RoleEnum;

public class UserFixture {

  public static final User USER1 = User.builder()
      .username("username1")
      .password("Password!2")
      .username("서울")
      .email("username1@google.com")
      .role(RoleEnum.MEMBER)
      .build();

  public static final SignupRequestDto SIGNUP_REQUEST_DTO =
      SignupRequestDto.builder()
          .username("username1")
          .password("Password!2")
          .email("xxx0011@gmail.com")
          .nickname("nickname1")
          .region("서울")
          .build();

  public static final LoginRequestDto LOGIN_REQUEST_DTO =
      LoginRequestDto.builder()
          .username("username1")
          .password("Password1!")
          .build();

  public static final PasswordUpdateRequestDto PASSWORD_UPDATE_REQUEST_DTO =
      PasswordUpdateRequestDto.builder()
          .password("PassWord1!")
          .build();

  public static final RegionUpdateRequestDto REGION_UPDATE_REQUEST_DTO =
      RegionUpdateRequestDto.builder()
          .region("서울")
          .build();

  public static final ProfileResponseDto PROFILE_RESPONSE_DTO =
      ProfileResponseDto.builder()
          .img_url("img_url")
          .nickname("nickname1")
          .build();

  public static final DuplicateCheckRequestDto DUPLICATE_CHECK_REQUEST_DTO =
      DuplicateCheckRequestDto.builder()
          .duplicateField("username")
          .content("중복 확인을 위한 내용")
          .build();

  public static final DuplicateCheckResponseDto DUPLICATE_CHECK_RESPONSE_DTO =
      new DuplicateCheckResponseDto(false);

  public static final Long MEMBER_UNIQUE_ID = 1L;



}
