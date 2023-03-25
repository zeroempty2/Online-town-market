package com.example.townmarket.common.dto;

import com.example.townmarket.common.domain.user.entity.User;
import com.example.townmarket.common.enums.RoleEnum;
import java.time.format.DateTimeFormatter;
import lombok.Getter;

@Getter
public class LoadByUsernameResponse {
  private Long id;
  private String email;
  private String password;
  private String img_url;
  private String nickname;
  private RoleEnum role;
  private String username;

  public LoadByUsernameResponse(Long id, String email, String password,
      String img_url, String nick_name, RoleEnum role, String username) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.img_url = img_url;
    this.nickname = nick_name;
    this.role = role;
    this.username = username;
  }

  public static LoadByUsernameResponse valueOf(User user){
    return new LoadByUsernameResponse(user.getId(),
            user.getEmail(),
            user.getPassword(),
            user.getProfile().getImg_url(),
            user.getProfile().getNickName(),
            user.getRole(),
            user.getUsername());
  }
}
