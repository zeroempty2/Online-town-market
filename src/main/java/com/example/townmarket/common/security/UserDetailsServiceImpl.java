package com.example.townmarket.common.security;

import com.example.townmarket.common.cache.CacheService;
import com.example.townmarket.common.domain.user.entity.Profile;
import com.example.townmarket.common.domain.user.entity.User;
import com.example.townmarket.common.domain.user.repository.UserRepository;
import com.example.townmarket.common.domain.user.service.UserServiceImpl;
import com.example.townmarket.common.dto.LoadByUsernameResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  private final CacheService cacheService;


  @Override
//  @Cacheable(value = "user", key = "#username",  cacheManager = "cacheManager")
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = cacheService.cacheUser(username);
//    UserInformation userInformation = userRepository.getUserInfoByUsername(username)
//        .orElseThrow(() -> new IllegalArgumentException("해당 사용자는 존재하지 않습니다."));
//    User user = User.builder()
//        .id(userInformation.getUserId())
//        .username(userInformation.getUsername())
//        .password(userInformation.getPassword())
//        .role(userInformation.getRoleEnum())
//        .build();
//    User user = userRepository.findByUsername(username)
//        .orElseThrow(() -> new IllegalArgumentException("해당 사용자는 존재하지 않습니다."));

    User serializerbleUser = User.builder()
        .username(user.getUsername())
        .id(user.getId())
        .role(user.getRole())
        .email(user.getEmail())
        .profile(Profile.builder().img_url(user.getProfile().getImg_url()).nickName(user.getProfile().getNickName()).build())
        .password(user.getPassword())
        .build();

    return new UserDetailsImpl(user);
  }


}
