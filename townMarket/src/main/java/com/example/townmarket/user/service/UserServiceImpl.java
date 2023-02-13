package com.example.townmarket.user.service;

import com.example.townmarket.commons.jwtUtil.JwtUtil;
import com.example.townmarket.user.dto.LoginRequestDto;
import com.example.townmarket.user.dto.PasswordUpdateRequestDto;
import com.example.townmarket.user.dto.ProfileRequestDto;
import com.example.townmarket.user.dto.ProfileResponseDto;
import com.example.townmarket.user.dto.RegionUpdateRequestDto;
import com.example.townmarket.user.dto.SignupRequestDto;
import com.example.townmarket.user.entity.Profile;
import com.example.townmarket.user.entity.User;
import com.example.townmarket.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService { // UserServiceImpl로 수정 부탁드립니다.


  private final UserRepository userRepository;
  private final JwtUtil jwtUtil;
  private final PasswordEncoder passwordEncoder;

  @Override
  public String signup(SignupRequestDto request) {
    String username = request.getUsername();
    String phoneNum = request.getPhoneNumber();
    String email = request.getEmail();
    String nickname = request.getNickname() + UUID.randomUUID().toString();
    String password = passwordEncoder.encode(request.getPassword());

    // 회원 중복 확인
    if (userRepository.existsByUsername(username)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "중복된 아이디 입니다.");
    }
    // 휴대폰 번호 중복 확인
    if (userRepository.existsByPhoneNumber(phoneNum)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 존재하는 휴대폰 번호입니다.");
    }
    // 이메일 중복 확인
    if (userRepository.existsByEmail(email)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다.");
    }
    // 닉네임 중복 확인
//    if (userRepository.existsByNickname(nickname)) {
//      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 존재하는 닉네임입니다.");
//    }
    Profile profile = new Profile(request.getNickname());

    User user = User.builder()
        .username(username)
        .password(password)
        .phoneNumber(phoneNum)
        .email(email)
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
    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
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
  public void updateUser(String username, PasswordUpdateRequestDto updateDto) {
    User user = userRepository.findByUsername(username).orElseThrow(
        () -> new RuntimeException("회원을 찾을 수 없습니다.")
    );
    if (user.checkAuthorization(user)) {
      user.updatePassword(updateDto);
      this.userRepository.save(user);
      return;
    }
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
  }

  @Override
  public void updateRegion(String username, RegionUpdateRequestDto updateRequestDto) {
    User user = userRepository.findByUsername(username).orElseThrow(
        () -> new RuntimeException("회원을 찾을 수 없습니다.")
    );
    if (user.checkAuthorization(user)) {
      user.updateRegion(updateRequestDto);
      this.userRepository.save(user);
      return;
    }
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
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
  public Profile updateProfile(Long userId, ProfileRequestDto request) {
    Profile profileSaved = userRepository.findById(userId)
        .orElseThrow(() -> new IllegalArgumentException("회원 없음")).getProfile();
    profileSaved.update(request.getNickname(), request.getImg_url());
    return profileSaved;
  }

  @Override
  public ProfileResponseDto showProfile(Long userId) {
    Profile profile = userRepository.findById(userId)
        .orElseThrow(() -> new IllegalArgumentException("회원 없음")).getProfile();
    return new ProfileResponseDto(profile);
  }

  @Override
  public void setUserGrade(User reviewee, int grade, int count) {
    reviewee.getGrade().setUserGrade(grade, count);
  }

  @Override
  public List<User> findAllUser() {
    return userRepository.findAll();
  }

  @Override
  public User findUserById(Long userId) {
    return userRepository.findById(userId).orElseThrow(
        () -> new RuntimeException("회원을 찾을 수 없습니다.")
    );
  }

  @Override
  public void updateUserGrade(User reviewee, int grade) {
    reviewee.getGrade().updateUserGrade(grade);
  }

}
