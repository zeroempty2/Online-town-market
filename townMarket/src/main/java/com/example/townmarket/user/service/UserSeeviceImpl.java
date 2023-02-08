package com.example.townmarket.user.service;

import com.example.townmarket.user.dto.LoginRequestDto;
import com.example.townmarket.user.dto.SignupRequestDto;
import com.example.townmarket.user.entity.User;
import com.example.townmarket.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSeeviceImpl implements UserService { // UserServiceImpl로 수정 부탁드립니다.

  private final UserRepository userRepository;

  @Override
  public void signup(SignupRequestDto request) {

  }

  @Override
  public String login(HttpServletResponse response, LoginRequestDto request) {
    return null;
  }

  @Override
  public void logout(String username) {
  }

  @Override
  public String updateUser(String username) {
    return null;
  }

  @Override
  public String deleteUser(String username) {
    return null;
  }

  @Override
  public List<User> findAllUser() {
    return userRepository.findAll();
  }
}
