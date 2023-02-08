package com.example.townmarket.user.service;

import com.example.townmarket.user.dto.ProfileRequestDto;
import com.example.townmarket.user.dto.ProfileResponseDto;
import com.example.townmarket.user.entity.Profile;
import com.example.townmarket.user.repository.ProfileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

  private final ProfileRepository profileRepository;

  @Transactional
  public String updateProfile(Long profileId, ProfileRequestDto request) {
    Profile profileSaved = profileRepository.findById(profileId)
        .orElseThrow(() -> new IllegalArgumentException("회원 없음"));
    profileSaved.update(request.getNickname(), request.getImg_url());
    return "해당 프로필이 업데이트 완료되었습니다";
  }

  @Transactional
  public ProfileResponseDto showProfile(Long profileId) {
    Profile profile = profileRepository.findById(profileId)
        .orElseThrow(() -> new IllegalArgumentException("회원 없음"));
    return new ProfileResponseDto(profile);
  }
}
