package com.example.townmarket.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.example.townmarket.commons.jwtUtil.JwtUtil;
import com.example.townmarket.user.dto.LoginRequestDto;
import com.example.townmarket.user.dto.PasswordUpdateRequestDto;
import com.example.townmarket.user.dto.ProfileRequestDto;
import com.example.townmarket.user.dto.RegionUpdateRequestDto;
import com.example.townmarket.user.dto.SignupRequestDto;
import com.example.townmarket.user.entity.Profile;
import com.example.townmarket.user.entity.User;
import com.example.townmarket.user.repository.UserRepository;
import java.util.Optional;
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
        .email("asd@naver.com")
        .region("양평동")
        .build();

    String username = requestDto.getUsername();
    String phoneNumber = requestDto.getPhoneNumber();
    String email = requestDto.getEmail();

    User user = mock(User.class);

    given(userRepository.existsByUsername(username)).willReturn(
        false);
    given(userRepository.existsByPhoneNumber(phoneNumber)).willReturn(false);
    given(userRepository.existsByEmail(email)).willReturn(false);

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
  @DisplayName("비밀번호 수정")
  void updateUser() {
    SignupRequestDto request = SignupRequestDto.builder()
        .username("username1")
        .password("password")
        .phoneNumber("010-xxxx-xxxx")
        .email("asdf@naver.com")
        .build();

    PasswordUpdateRequestDto requestDto = PasswordUpdateRequestDto.builder()
        .password("pass")
        .build();
    String password = passwordEncoder.encode(requestDto.getPassword());
    User newUser = new User(request.getUsername(), password, request.getPhoneNumber(),
        request.getEmail(), null);

    given(userRepository.findByUsername(newUser.getUsername()))
        .willReturn(Optional.of(newUser));

    // when
    userService.updateUser(newUser.getUsername(), requestDto);

    // then
    verify(userRepository, times(1)).save(any(User.class));
  }

  @Test
  @DisplayName("거래 지역 수정")
  void updateRegion() {
    SignupRequestDto request = SignupRequestDto.builder()
        .username("username1")
        .password("password")
        .phoneNumber("010-xxxx-xxxx")
        .email("asdas@naver.com")
        .region("양평동")
        .build();

    RegionUpdateRequestDto requestDto = RegionUpdateRequestDto.builder()
        .region("방화동")
        .build();
    String region = requestDto.getRegion();
    User newUser = new User(request.getUsername(), request.getPassword(), request.getPhoneNumber(),
        request.getEmail(),
        region);

    given(userRepository.findByUsername(newUser.getUsername()))
        .willReturn(Optional.of(newUser));

    // when
    userService.updateRegion(newUser.getUsername(), requestDto);

    // then
    verify(userRepository, times(1)).save(any(User.class));
  }

  @Test
  @DisplayName("계정 삭제")
  void deleteUser() {
    // given
    SignupRequestDto request = SignupRequestDto.builder()
        .username("username1")
        .password("password")
        .phoneNumber("010-xxxx-xxxx")
        .email("asd@naver.com")
        .region("양평동")
        .build();

    User newUser = new User(request.getUsername(), request.getPassword(), request.getPhoneNumber(),
        request.getEmail(),
        request.getRegion());

    given(userRepository.findByUsername(newUser.getUsername()))
        .willReturn(Optional.of(newUser));

    // when
    userService.deleteUser(newUser.getId(), newUser.getUsername());

    // then
    verify(userRepository, times(1)).deleteById(newUser.getId());
  }
}
