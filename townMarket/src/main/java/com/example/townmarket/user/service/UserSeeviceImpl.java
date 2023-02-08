package com.example.townmarket.user.service;

import com.example.townmarket.commons.jwtUtil.JwtUtil;
import com.example.townmarket.user.dto.LoginRequestDto;
import com.example.townmarket.user.dto.SignupRequestDto;
import com.example.townmarket.user.dto.UserUpateRequestDto;
import com.example.townmarket.user.entity.User;
import com.example.townmarket.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserSeeviceImpl implements UserService { // UserServiceImpl로 수정 부탁드립니다.
  private final UserRepository userRepository;
  private final JwtUtil jwtUtil;
  private final PasswordEncoder passwordEncoder;

  @Override
  public void signup(SignupRequestDto request) {
    String username = request.getUsername();
    String phoneNum = request.getPhoneNumber();
    String password = passwordEncoder.encode(request.getPassword());

    // 회원 중복 확인
    Optional<User> foundUser = userRepository.findByUsername(username);
    if (foundUser.isPresent()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "중복된 아이디 입니다.");
    }
    // 휴대폰 번호 중복 확인
    Optional<User> foundPhone = userRepository.findByPhoneNumber(phoneNum);
    if (foundPhone.isPresent()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 존재하는 휴대폰 번호입니다.");
    }

    User user = User.builder()
        .username(username)
        .password(password)
        .phoneNumber(phoneNum)
        .region(request.getRegion())
        .build();
    userRepository.save(user);
  }

  @Override
  public String login(HttpServletResponse response, LoginRequestDto request) {
    // 사용자 확인
    User user = userRepository.findByUsername(request.getUsername()).orElseThrow(
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
  public void logout(User user) {}


  @Override
  public void updateUser(String username, UserUpateRequestDto updateDto) {
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
}