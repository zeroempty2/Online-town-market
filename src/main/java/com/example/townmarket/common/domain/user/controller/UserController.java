package com.example.townmarket.common.domain.user.controller;

import com.example.townmarket.common.domain.email.service.EmailService;
import com.example.townmarket.common.domain.user.dto.LoginRequestDto;
import com.example.townmarket.common.domain.user.dto.PasswordUpdateRequestDto;
import com.example.townmarket.common.domain.user.dto.ProfileRequestDto;
import com.example.townmarket.common.domain.user.dto.ProfileResponseDto;
import com.example.townmarket.common.domain.user.dto.RegionUpdateRequestDto;
import com.example.townmarket.common.domain.user.dto.SignupRequestDto;
import com.example.townmarket.common.domain.user.service.UserService;
import com.example.townmarket.common.dto.StatusResponse;
import com.example.townmarket.common.enums.ResponseMessages;
import com.example.townmarket.common.jwtUtil.JwtUtil;
import com.example.townmarket.common.security.UserDetailsImpl;
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
  private final EmailService emailService;


  @PostMapping("/signup")
  public ResponseEntity<StatusResponse> signup(
      @Validated @RequestBody SignupRequestDto signupRequestDto) {
    userService.signup(signupRequestDto);
    return ResponseEntity.ok().body(StatusResponse.valueOf(ResponseMessages.CREATED_SUCCESS));
  }

  @PostMapping("/login")
  public ResponseEntity<StatusResponse> login(
      @RequestBody LoginRequestDto loginRequestDto,
      HttpServletResponse response) {
    userService.login(response, loginRequestDto);
    return ResponseEntity.ok().body(StatusResponse.valueOf(ResponseMessages.SUCCESS));

  }

  @PostMapping("/logout")
  public ResponseEntity<StatusResponse> logout(HttpServletResponse response) {
    response.setHeader(JwtUtil.AUTHORIZATION_HEADER, null);
    return ResponseEntity.ok().body(StatusResponse.valueOf(ResponseMessages.SUCCESS));
  }

  @PutMapping("/update/pw")
  public ResponseEntity<StatusResponse> updateUser(
      @Validated @RequestBody PasswordUpdateRequestDto updateRequestDto,
      @AuthenticationPrincipal
      UserDetailsImpl userDetails) {
    userService.updateUser(userDetails.getUsername(), updateRequestDto);
    return ResponseEntity.ok().body(StatusResponse.valueOf(ResponseMessages.SUCCESS));
  }

  @PutMapping("/update/region")
  public ResponseEntity<StatusResponse> updateRegion(
      @RequestBody RegionUpdateRequestDto updateRequestDto,
      @AuthenticationPrincipal
      UserDetailsImpl userDetails) {
    userService.updateRegion(userDetails.getUsername(), updateRequestDto);
    return ResponseEntity.ok().body(StatusResponse.valueOf(ResponseMessages.SUCCESS));
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity<StatusResponse> deleteUser(@PathVariable Long userId,
      @AuthenticationPrincipal
      UserDetailsImpl userDetails) {
    userService.deleteUser(userId, userDetails.getUsername());
    StatusResponse statusResponse = StatusResponse.valueOf(ResponseMessages.DELETE_SUCCESS);
    return new ResponseEntity<>(statusResponse, HttpStatus.NO_CONTENT);
  }


  @PutMapping("/profile/update")
  public ResponseEntity<StatusResponse> updateProfile(
      @RequestBody ProfileRequestDto request, @AuthenticationPrincipal
  UserDetailsImpl userDetails) {
    userService.updateProfile(userDetails.getUserId(), request);
    return ResponseEntity.ok().body(StatusResponse.valueOf(ResponseMessages.SUCCESS));
  }

  @GetMapping("/profile/{userId}")
  public ResponseEntity<ProfileResponseDto> showProfile(@PathVariable Long userId
  ) {
    return ResponseEntity.status(HttpStatus.OK).body(userService.showProfile(userId));
  }
}
