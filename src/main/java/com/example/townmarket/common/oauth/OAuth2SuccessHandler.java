package com.example.townmarket.common.oauth;


import com.example.townmarket.common.enums.RoleEnum;
import com.example.townmarket.common.jwtUtil.JwtUtil;
import com.example.townmarket.common.domain.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

  private final UserRepository userRepository;
  private final JwtUtil jwtUtil;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

    String email = (String) oAuth2User.getAttributes().get("email");
    String username = (String) oAuth2User.getAttributes().get("name");

    System.out.println(request.getRequestURI());
    System.out.println(request.getQueryString());

    username = username.toLowerCase();

    if (userRepository.existsByEmail(email)) {
      String refreshToken = jwtUtil.createRefreshToken(username, RoleEnum.MEMBER);
      String accessToken = jwtUtil.createAccessToken(username, RoleEnum.MEMBER);
      // 프론트 엔드에 전달할 응답 생성
//      Map<Object, Object> data = new HashMap<>();
//
//      data.put("username", username);
//      data.put("accessToken", accessToken);
//      data.put("refreshToken", refreshToken);
//
//      response.setContentType("application/json");
////      ResponseEntity responseEntity = ResponseEntity.status(HttpStatus.OK).body(model);
//      new ObjectMapper().writeValue(response.getOutputStream(), data);
      String redirectUrl = "http://localhost:5500/index.html";
      redirectUrl += "?username=" + username + "&role=" + RoleEnum.MEMBER;
      response.sendRedirect(redirectUrl);
//      response.sendRedirect("http://localhost:5500/index.html");
    } else {
      //패스워드 입력하도록 리다이렉트
      response.sendRedirect("/users/oauth/password/" + email + "/" + username);
    }
  }
}
