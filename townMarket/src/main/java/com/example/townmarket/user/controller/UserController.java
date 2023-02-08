package com.example.townmarket.user.controller;

import com.example.townmarket.commons.jwtUtil.JwtUtil;
import com.example.townmarket.commons.security.UserDetailsImpl;
import com.example.townmarket.user.dto.LoginRequestDto;
import com.example.townmarket.user.dto.ProfileRequestDto;
import com.example.townmarket.user.dto.ProfileResponseDto;
import com.example.townmarket.user.dto.SignupRequestDto;
import com.example.townmarket.user.dto.UserUpdateRequestDto;
import com.example.townmarket.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

  private final UserService userService;


  @PostMapping("/signup")
  public ResponseEntity<String> signup(@Validated @RequestBody SignupRequestDto signupRequestDto) {
    String signup = userService.signup(signupRequestDto);
    return new ResponseEntity<>(signup, HttpStatus.CREATED);

  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@Validated @RequestBody LoginRequestDto loginRequestDto,
      HttpServletResponse response) {
    userService.login(response, loginRequestDto);
    return new ResponseEntity<>("로그인이 성공하였습니다.", HttpStatus.OK);

  }

  @PostMapping("/logout")
  public ResponseEntity<String> logout(HttpServletResponse response) {
    response.setHeader(JwtUtil.AUTHORIZATION_HEADER, null);
    return new ResponseEntity<>("로그아웃이 완료되었습니다.", HttpStatus.OK);
  }

  @PutMapping("/update")
  public ResponseEntity<String> updateUser(
      @Validated @RequestBody UserUpdateRequestDto updateRequestDto,
      @AuthenticationPrincipal
      UserDetailsImpl userDetails) {
    userService.updateUser(userDetails.getUsername(), updateRequestDto);
    return new ResponseEntity<>("계정 수정이 완료되었습니다.", HttpStatus.OK);
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity<String> deleteUser(@PathVariable Long userId, @AuthenticationPrincipal
  UserDetailsImpl userDetails) {
    userService.deleteUser(userId, userDetails.getUsername());
    return new ResponseEntity<>("계정 삭제가 완료되었습니다.", HttpStatus.OK);
  }


  @PutMapping("/profile/update")
  public ResponseEntity<String> updateProfile(
      @RequestBody ProfileRequestDto request, @AuthenticationPrincipal
  UserDetailsImpl userDetails) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(userService.updateProfile(userDetails.getUserId(), request));
  }

  @GetMapping("/profile/{userId}")
  public ResponseEntity<ProfileResponseDto> showProfile(@PathVariable Long userId
  ) {
    return ResponseEntity.status(HttpStatus.OK).body(userService.showProfile(userId));
  }

}
