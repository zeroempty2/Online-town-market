package com.example.townmarket.commons.config;

import com.example.townmarket.commons.jwtUtil.JwtUtil;
import com.example.townmarket.commons.oauth.OAuth2SuccessHandler;
import com.example.townmarket.commons.oauth.OAuth2UserServiceImpl;
import com.example.townmarket.commons.security.AdminDetailsServiceImpl;
import com.example.townmarket.commons.security.CustomAccessDeniedHandler;
import com.example.townmarket.commons.security.CustomAuthenticationEntryPoint;
import com.example.townmarket.commons.security.JwtAuthFilter;
import com.example.townmarket.commons.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

  private final String[] permitAllArray = {
      "/",
      "/users/login/",
      "/users/login2/",
      "/users/signup/",
      "/css/**",
      "/js/**",
      "/images/**",
      "/login/oauth2/code/google",
      "/users/oauth/password/**",
      "profile"};
  private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
  private final JwtUtil jwtUtil;
  private final UserDetailsServiceImpl userDetailsService;
  private final CustomAccessDeniedHandler customAccessDeniedHandler;
  private final AdminDetailsServiceImpl adminDetailsService;

  private final OAuth2UserServiceImpl oAuth2UserService;

  private final OAuth2SuccessHandler oAuth2SuccessHandler;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.ignoring()
        .requestMatchers(PathRequest.toH2Console())
        .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
        .requestMatchers("/api/**")
        .requestMatchers("/docs/**");
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf().disable();

    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.authorizeHttpRequests()
        .requestMatchers("/**").permitAll()
        .requestMatchers(permitAllArray).permitAll()
        .requestMatchers("/admin/users").hasAnyRole("TOP_MANAGER", "MIDDLE_MANAGER")
        .anyRequest().authenticated()
        .and().addFilterBefore(new JwtAuthFilter(jwtUtil, userDetailsService, adminDetailsService),
            UsernamePasswordAuthenticationFilter.class);

//    http.formLogin().loginPage("/api/user/login-page").permitAll();
    http.oauth2Login()//OAuth 로그인 기능에 대한 여러 설정의 진입
        .userInfoEndpoint()// 로그인 성공 이후 사용자 정보를 가져올 때의 설정
        .userService(oAuth2UserService); //소셜 로그인 성공 후 후속 조치를 진행할 서비스의 구현체 등록
    http.oauth2Login().successHandler(oAuth2SuccessHandler);

    //401 인증과정 실패시 에러처리
    http.exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint);
    //403
    http.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);

    return http.build();
  }

}
