package com.example.townmarket.user.controller;

import com.example.townmarket.commons.jwtUtil.JwtUtil;
import com.example.townmarket.commons.responseMessageData.DefaultResponse;
import com.example.townmarket.commons.responseMessageData.ResponseMessages;
import com.example.townmarket.commons.security.UserDetailsImpl;
import com.example.townmarket.user.dto.LoginRequestDto;
import com.example.townmarket.user.dto.PasswordUpdateRequestDto;
import com.example.townmarket.user.dto.ProfileRequestDto;
import com.example.townmarket.user.dto.ProfileResponseDto;
import com.example.townmarket.user.dto.RegionUpdateRequestDto;
import com.example.townmarket.user.dto.SignupRequestDto;
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
  public ResponseEntity<DefaultResponse> signup(
      @Validated @RequestBody SignupRequestDto signupRequestDto) {
    userService.signup(signupRequestDto);
    DefaultResponse defaultResponse = DefaultResponse.valueOf(ResponseMessages.CREATED_SUCCESS);
    return ResponseEntity.ok().body(defaultResponse);

  }

  @PostMapping("/login")
  public ResponseEntity<DefaultResponse> login(
      @Validated @RequestBody LoginRequestDto loginRequestDto,
      HttpServletResponse response) {
    userService.login(response, loginRequestDto);
    DefaultResponse defaultResponse = DefaultResponse.valueOf(ResponseMessages.SUCCESS);
    return ResponseEntity.ok().body(defaultResponse);

  }

  @PostMapping("/logout")
  public ResponseEntity<DefaultResponse> logout(HttpServletResponse response) {
    response.setHeader(JwtUtil.AUTHORIZATION_HEADER, null);
    DefaultResponse defaultResponse = DefaultResponse.valueOf(ResponseMessages.SUCCESS);
    return ResponseEntity.ok().body(defaultResponse);
  }

  @PutMapping("/update/pw")
  public ResponseEntity<DefaultResponse> updateUser(
      @Validated @RequestBody PasswordUpdateRequestDto updateRequestDto,
      @AuthenticationPrincipal
      UserDetailsImpl userDetails) {
    userService.updateUser(userDetails.getUsername(), updateRequestDto);
    DefaultResponse defaultResponse = DefaultResponse.valueOf(ResponseMessages.SUCCESS);
    return ResponseEntity.ok().body(defaultResponse);
  }

  @PutMapping("/update/region")
  public ResponseEntity<DefaultResponse> updateRegion(
      @RequestBody RegionUpdateRequestDto updateRequestDto,
      @AuthenticationPrincipal
      UserDetailsImpl userDetails) {
    userService.updateRegion(userDetails.getUsername(), updateRequestDto);
    DefaultResponse defaultResponse = DefaultResponse.valueOf(ResponseMessages.SUCCESS);
    return ResponseEntity.ok().body(defaultResponse);
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity<DefaultResponse> deleteUser(@PathVariable Long userId,
      @AuthenticationPrincipal
      UserDetailsImpl userDetails) {
    userService.deleteUser(userId, userDetails.getUsername());
    DefaultResponse defaultResponse = DefaultResponse.valueOf(ResponseMessages.DELETE_SUCCESS);
    return new ResponseEntity<>(defaultResponse, HttpStatus.NO_CONTENT);
  }


  @PutMapping("/profile/update")
  public ResponseEntity<DefaultResponse> updateProfile(
      @RequestBody ProfileRequestDto request, @AuthenticationPrincipal
  UserDetailsImpl userDetails) {
    userService.updateProfile(userDetails.getUserId(), request);
    DefaultResponse defaultResponse = DefaultResponse.valueOf(ResponseMessages.SUCCESS);
    return ResponseEntity.ok().body(defaultResponse);
  }

  @GetMapping("/profile/{userId}")
  public ResponseEntity<ProfileResponseDto> showProfile(@PathVariable Long userId
  ) {
    return ResponseEntity.status(HttpStatus.OK).body(userService.showProfile(userId));
  }
}
