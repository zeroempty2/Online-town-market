package com.example.townmarket.commons.oauth;


import com.example.townmarket.commons.jwtUtil.JwtUtil;
import com.example.townmarket.user.entity.User;
import com.example.townmarket.user.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
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
      Authentication authentication)
      throws IOException, ServletException, IOException {
    OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

    User user = User.builder()
        .email(oAuth2User.getAttribute("email"))
        .password(UUID.randomUUID().toString())
        .username(oAuth2User.getAttribute("name"))
//        .phoneNumber(oAuth2User.getAttribute("phoneNumber"))
        .build();

//    if (!userRepository.existsByEmail(user.getEmail())) {
//      userRepository.save(user);
//    }
    String token = jwtUtil.createToken(user.getUsername(),
        user.getUsername() + "#" + UUID.randomUUID().toString().substring(0, 4));
    response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);
  }
}
