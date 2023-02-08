package com.example.townmarket.user.service;

import com.example.townmarket.user.dto.ProfileRequestDto;

public interface ProfileService {

  void updateProfile(Long profileId, ProfileRequestDto request);

  void showProfile(Long profileId);
}
