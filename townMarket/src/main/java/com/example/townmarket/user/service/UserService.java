package com.example.townmarket.user.service;

import com.example.townmarket.user.dto.LoginRequestDto;
import com.example.townmarket.user.dto.ProfileRequestDto;
import com.example.townmarket.user.dto.ProfileResponseDto;
import com.example.townmarket.user.dto.SignupRequestDto;
import com.example.townmarket.user.dto.UserUpdateRequestDto;
import com.example.townmarket.user.entity.User;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {

  String signup(SignupRequestDto request);

  String login(HttpServletResponse response, LoginRequestDto request);

  void logout(User user);

  void updateUser(String username, UserUpdateRequestDto updateDto);

  void deleteUser(Long userId, String username);

  String updateProfile(Long profileId, ProfileRequestDto request);

  ProfileResponseDto showProfile(Long profileId);

  List<User> findAllUser();

  void SetUserGrade(User reviewee, int grade, int count);

  User findUserById(Long userId);

  void updateUserGrade(User reviewee, int grade);

}
