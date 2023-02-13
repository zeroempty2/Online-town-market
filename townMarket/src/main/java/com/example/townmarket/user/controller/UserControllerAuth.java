package com.example.townmarket.user.controller;

import com.example.townmarket.user.dto.LoginRequestDto;
import com.example.townmarket.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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


}
