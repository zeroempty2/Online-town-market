package com.example.townmarket.user.controller;

import com.example.townmarket.commons.jwtUtil.JwtUtil;

import com.example.townmarket.user.dto.LoginRequestDto;
import com.example.townmarket.user.dto.SignupRequestDto;
import com.example.townmarket.user.service.KakaoService;
import com.example.townmarket.user.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserControllerAuth {

  private final KakaoService kakaoService;

  private final UserService userService;

  @GetMapping("/signup")
  public ModelAndView signupPage() {
    return new ModelAndView("signup");
  }

  @GetMapping("/login-page")
  public ModelAndView loginPage() {
    return new ModelAndView("login");
  }

  @PostMapping("/signup")
  public String signup(SignupRequestDto signupRequestDto) {
    String s = userService.signup(signupRequestDto);
    return "redirect:/api/user/login-page";
  }

  @ResponseBody
  @PostMapping("/login")
  public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
    String success = userService.login( response, loginRequestDto);
    return success;
  }

  @GetMapping("/kakao/callback")
  public String kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
    // code: 카카오 서버로부터 받은 인가 코드
    String createToken = kakaoService.kakaoLogin(code, response);

    // Cookie 생성 및 직접 브라우저에 Set
    Cookie cookie = new Cookie(JwtUtil.AUTHORIZATION_HEADER, createToken.substring(7));
    cookie.setPath("/");
    response.addCookie(cookie);

    return "kakao-login 성공";
  }

}
