package com.example.townmarket.commons.oauth;

import com.example.townmarket.commons.jwtUtil.JwtUtil;
import com.example.townmarket.commons.security.UserDetailsImpl;
import com.example.townmarket.user.entity.User;
import com.example.townmarket.user.repository.UserRepository;
import io.jsonwebtoken.Jwt;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class OAuth2UserServiceImpl extends DefaultOAuth2UserService {


  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

    OAuth2User oAuth2User = super.loadUser(userRequest);

    String userNameAttributeName = userRequest.getClientRegistration()
        .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

    OAuthDto oAuthDto =
        OAuthDto.ofGoogle(userNameAttributeName, oAuth2User.getAttributes());

    log.info("{}", oAuthDto);

    Map<String, Object> memberAttribute = oAuthDto.convertToMap();

    return new DefaultOAuth2User(
        Collections.singleton(new SimpleGrantedAuthority("ROLE_MEMBER")),
        memberAttribute, "email");
  }
}


