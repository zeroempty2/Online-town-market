package com.example.townmarket.commons.oauth;


import com.example.townmarket.commons.jwtUtil.JwtUtil;
import com.example.townmarket.user.entity.User;
import com.example.townmarket.user.repository.UserRepository;
import com.example.townmarket.user.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
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

    username = username.toLowerCase();

    if(userRepository.existsByEmail(email)) {
      String token = jwtUtil.createToken(username, username+"#"+UUID.randomUUID().toString().substring(0,4));
      response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);
    }else{
      //패스워드 입력하도록 리다이렉트
      response.sendRedirect("/users/oauth/password/" + email + "/" + username);
    }
  }
}
