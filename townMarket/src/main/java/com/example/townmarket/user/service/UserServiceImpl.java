package com.example.townmarket.user.service;

import com.example.townmarket.commons.jwtUtil.JwtUtil;
import com.example.townmarket.user.dto.LoginRequestDto;
import com.example.townmarket.user.dto.ProfileRequestDto;
import com.example.townmarket.user.dto.ProfileResponseDto;
import com.example.townmarket.user.dto.SignupRequestDto;
import com.example.townmarket.user.dto.UserUpdateRequestDto;
import com.example.townmarket.user.entity.Profile;
import com.example.townmarket.user.entity.User;
import com.example.townmarket.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import java.nio.channels.OverlappingFileLockException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{ // UserServiceImpl로 수정 부탁드립니다.

  private final UserRepository userRepository;
  private final JwtUtil jwtUtil;
  private final PasswordEncoder passwordEncoder;

  @Override
  public String signup(SignupRequestDto request) {
    String username = request.getUsername();
    String phoneNum = request.getPhoneNumber();
    String password = passwordEncoder.encode(request.getPassword());

    // 회원 중복 확인
//    User foundUser = userRepository.findByUsername(username).orElseThrow(()->new OverlappingFileLockException(""));
    if (userRepository.existsByUsername(username)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "중복된 아이디 입니다.");
    }
    // 휴대폰 번호 중복 확인
//    Optional<User> foundPhone = userRepository.findByPhoneNumber(phoneNum);
    if (userRepository.existsByPhoneNumber(phoneNum)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 존재하는 휴대폰 번호입니다.");
    }

    Profile profile = new Profile(request.getNickname(), "img");
    User user = User.builder()
        .username(username)
        .password(password)
        .phoneNumber(phoneNum)
        .region(request.getRegion())
        .email(request.getEmail())
        .profile(profile)
        .build();

    userRepository.save(user);
    return "회원가입 성공";
  }

  @Override
  public String login(HttpServletResponse response, LoginRequestDto request) {
    String username = request.getUsername();
    String password = request.getPassword();
    // 사용자 확인
    User user = userRepository.findByUsername(username).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "회원을 찾을 수 없습니다.")
    );

    // 비밀번호 확인
    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new IllegalArgumentException("비밀번호가 틀립니다.");
    }
    String token = jwtUtil.createToken(user.getUsername(), user.getProfile().getNickName());
    response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);
    return "로그인 성공";
  }

  @Override
  public void logout(User user) {
  }


  @Override
  public void updateUser(String username, UserUpdateRequestDto updateDto) {
    User user = userRepository.findByUsername(username).orElseThrow(
        () -> new RuntimeException("회원을 찾을 수 없습니다.")
    );
    if (user.checkAuthorization(user)) {
      user.update(updateDto);
      this.userRepository.save(user);
      return;
    }
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "본인 계정만 수정할 수 있습니다.");

  }

  @Override
  public void deleteUser(Long userId, String username) {
    User user = userRepository.findByUsername(username).orElseThrow(
        () -> new RuntimeException("회원을 찾을 수 없습니다.")
    );
    if (user.checkAuthorization(user)) {
      userRepository.deleteById(userId);
      return;
    }
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "본인 계정만 삭제할 수 있습니다.");
  }

  @Override
  public String updateProfile(Long userId, ProfileRequestDto request) {
    Profile profileSaved = userRepository.findById(userId)
        .orElseThrow(() -> new IllegalArgumentException("회원 없음")).getProfile();
    profileSaved.update(request.getNickname(), request.getImg_url());
    return "해당 프로필이 업데이트 완료되었습니다";
  }

  @Override
  public ProfileResponseDto showProfile(Long userId) {
    Profile profile = userRepository.findById(userId)
        .orElseThrow(() -> new IllegalArgumentException("회원 없음")).getProfile();
    return new ProfileResponseDto(profile);
  }

  @Override
  public List<User> findAllUser() {
    return userRepository.findAll();
  }

}
