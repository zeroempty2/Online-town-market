package com.example.townmarket.common.cache;

import com.example.townmarket.common.domain.user.entity.Profile;
import com.example.townmarket.common.domain.user.entity.User;
import com.example.townmarket.common.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CacheService {
private final UserRepository userRepository;
@Cacheable(value = "user", key = "#username",  cacheManager = "cacheManager")
public User cacheUser(String username){
  User user = userRepository.findByUsername(username).orElseThrow(
      () -> new IllegalArgumentException("유저가 없습니다.")
  );
  return User.builder()
      .username(user.getUsername())
      .id(user.getId())
      .role(user.getRole())
      .email(user.getEmail())
      .profile(
          Profile.builder().img_url(user.getProfile().getImg_url()).nickName(user.getProfile().getNickName()).build())
      .password(user.getPassword())
      .build();
}
}
