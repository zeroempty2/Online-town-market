package com.example.townmarket.user.controller;

import com.example.townmarket.commons.security.UserDetailsImpl;
import com.example.townmarket.user.dto.LoginRequestDto;
import com.example.townmarket.user.dto.SignupRequestDto;
import com.example.townmarket.user.dto.UserUpateRequestDto;
import com.example.townmarket.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
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
  public ResponseEntity signup(@Validated @RequestBody SignupRequestDto signupRequestDto) {
    return null;

  }

  @PostMapping("/login")
  public ResponseEntity login(@Validated @RequestBody LoginRequestDto loginRequestDto,
      HttpServletResponse response) {
    return null;

  }

  @PostMapping("/logout")
  public ResponseEntity logout(@AuthenticationPrincipal UserDetailsImpl userDetails) {
    return null;
  }

  @PutMapping("/update")
  public ResponseEntity updateUser(@Validated @RequestBody UserUpateRequestDto updateRequestDto,
      @AuthenticationPrincipal
      UserDetailsImpl userDetails) {
    return null;

  }

  @DeleteMapping("/{userId}")
  public ResponseEntity deleteUser(@AuthenticationPrincipal
  UserDetailsImpl userDetails) {
    return null;
  }

}
