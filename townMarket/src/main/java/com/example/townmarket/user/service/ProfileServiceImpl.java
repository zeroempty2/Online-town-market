package com.example.townmarket.user.service;

import com.example.townmarket.user.dto.ProfileRequestDto;
import com.example.townmarket.user.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

  private final ProfileRepository profileRepository;

  public void updateProfile(Long profileId, ProfileRequestDto request) {
  }

  public void showProfile(Long profileId) {
  }

}
