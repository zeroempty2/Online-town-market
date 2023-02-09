package com.example.townmarket.user.service;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.core.Is.isA;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.townmarket.commons.jwtUtil.JwtUtil;
import com.example.townmarket.user.dto.LoginRequestDto;
import com.example.townmarket.user.dto.ProfileRequestDto;
import com.example.townmarket.user.dto.SignupRequestDto;
import com.example.townmarket.user.dto.UserUpdateRequestDto;
import com.example.townmarket.user.entity.Profile;
import com.example.townmarket.user.entity.User;
import com.example.townmarket.user.repository.UserRepository;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

  @Mock
  UserRepository userRepository;

  @Mock
  JwtUtil jwtUtil;

  @Mock
  PasswordEncoder passwordEncoder;

  @InjectMocks
  UserServiceImpl userService;

  @Test
  @DisplayName("회원가입 성공 테스트")
  void signup() {

    //given
    SignupRequestDto requestDto = SignupRequestDto.builder()
        .username("username1")
        .password("password")
        .phoneNumber("010-xxxx-xxxx")
        .build();

    String username = requestDto.getUsername();
    String phoneNumber = requestDto.getPhoneNumber();

    User user = mock(User.class);

    given(userRepository.existsByUsername(username)).willReturn(
        false);
    given(userRepository.existsByPhoneNumber(phoneNumber)).willReturn(false);

    //when
    String signup = userService.signup(requestDto);

    //then
    verify(userRepository, times(1)).save(any());
    assertThat(signup).isEqualTo("회원가입 성공");
  }

  @Test
  @DisplayName("로그인 성공 테스트")
  void login() {
    // given
    ProfileRequestDto rs = ProfileRequestDto.builder()
        .nickname("nick")
        .img_url("1212")
        .build();
    LoginRequestDto requestDto = LoginRequestDto.builder()
        .username("username1")
        .password("password")
        .build();

    String username = requestDto.getUsername();
    String password = passwordEncoder.encode(requestDto.getPassword());

    Profile profile = new Profile(rs.getNickname(), rs.getImg_url());
    User user = new User(username, passwordEncoder.encode(password), profile);

    given(userRepository.findByUsername(username))
        .willReturn(Optional.of(user));
    given(!passwordEncoder.matches(password, user.getPassword())).willReturn(true);

    MockHttpServletResponse servletResponse = new MockHttpServletResponse();

    // when
    String login = userService.login(servletResponse, requestDto);
    String token = jwtUtil.createToken(username, profile.getNickName());
    servletResponse.addHeader("Authorization", token);

    // then
    assertThat(login).isEqualTo("로그인 성공");
  }

  @Test
  @DisplayName("계정 업데이트 - 비밀번호 수정")
  void updateUser() {
    UserUpdateRequestDto requestDto = UserUpdateRequestDto.builder()
        .password("pass")
        .build();
    String password = passwordEncoder.encode(requestDto.getPassword());
    User newUser = new User("user1", password, null, null);

    // when
    userService.updateUser(newUser.getUsername(), requestDto);

    // then
    verify(userRepository, times(1)).save(any(User.class));
  }

  @Test
  @DisplayName("계정 삭제")
  void deleteUser() {
    // given

    // when

    // then
  }
}
