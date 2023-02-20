package com.example.townmarket.common.domain.user.repository;

import com.example.townmarket.common.domain.user.dto.ProfileResponseDto;

public interface UserRepositoryQuery {

  boolean existByNickname(String nickname);

  ProfileResponseDto getProfileByUsername(String username);

}
