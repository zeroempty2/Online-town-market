package com.example.townmarket.user.service;

import com.example.townmarket.user.dto.ProfileRequestDto;
import com.example.townmarket.user.dto.ProfileResponseDto;

public interface ProfileService {

  String updateProfile(Long profileId, ProfileRequestDto request);

  ProfileResponseDto showProfile(Long profileId);
}
