package com.example.townmarket.common.domain.user.controller;

import com.example.townmarket.common.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserControllerAuth {

  private final UserService userService;


  @GetMapping("/")
  public String mainPage(Model model) {
    return "index";
  }

  @GetMapping("/users/login2")
  public String loginForm( Model model) {
//      User user = userDetails.getUser();
//      model.addAttribute("user", user.getUsername());
    return "login";
  }
  //OAuth로 로그인 시 비밀번호 입력 창으로
  @GetMapping("/users/oauth/password/{email}/{username}")
  public String oauth(@PathVariable("email") String email, @PathVariable("username") String username, Model model) {
    System.out.println("email = " + email);
    System.out.println("username = " + username);
    model.addAttribute("email", email);
    model.addAttribute("username", username);
    return "oauthPassword";
  }


}
