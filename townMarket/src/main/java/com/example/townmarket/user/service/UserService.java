package com.example.townmarket.user.service;

import com.example.townmarket.user.dto.LoginRequestDto;
import com.example.townmarket.user.dto.SignupRequestDto;
import com.example.townmarket.user.entity.User;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {

  void signup(SignupRequestDto request);

  String login(HttpServletResponse response, LoginRequestDto request);

  void logout(String username);

  String updateUser(String username);

  String deleteUser(String username);

  List<User> findAllUser();
}
