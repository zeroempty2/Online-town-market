package com.example.townmarket.user.controller;

import com.example.townmarket.commons.security.UserDetailsImpl;
import com.example.townmarket.user.dto.ProfileRequestDto;
import com.example.townmarket.user.dto.ProfileResponseDto;
import com.example.townmarket.user.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

  private final ProfileService profileService;

  @PutMapping("/update/{profileId}")
  public ResponseEntity<String> update(@Validated @PathVariable Long profileId, @RequestBody ProfileRequestDto request, @AuthenticationPrincipal
  UserDetailsImpl userDetails) {
    return ResponseEntity.status(HttpStatus.OK).body(profileService.updateProfile(profileId, request));
  }

  @GetMapping("/{profileId}")
  public ResponseEntity<ProfileResponseDto> showProfile(@PathVariable Long profileId, @AuthenticationPrincipal
  UserDetailsImpl userDetails) {
    return ResponseEntity.status(HttpStatus.OK).body(profileService.showProfile(profileId));
  }
}
