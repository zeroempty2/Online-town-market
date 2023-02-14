package com.example.townmarket.admin.dto;

import com.example.townmarket.common.domain.user.entity.Profile;
import com.example.townmarket.common.domain.user.entity.User;

public class PagingUserResponse {

  private final String username;

  private final String phoneNumber;

  private final String region;

  private final Profile profile;

  public PagingUserResponse(User user) {
    this.username = user.getUsername();
    this.phoneNumber = user.getPhoneNumber();
    this.region = user.getRegion();
    this.profile = user.getProfile();
  }
}
