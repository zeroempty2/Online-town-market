package com.example.townmarket.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOrigins("http://localhost:8080", "http://127.0.0.1:5500") // 허용할 출처
        .allowedMethods("GET", "POST", "PATCH", "OPTIONS", "DELETE") // 허용할 HTTP method
        .allowCredentials(true) // 쿠키 인증 요청 허용
        .exposedHeaders("Authorization")
        .maxAge(3000);// 원하는 시간만큼 pre-flight 리퀘스트를 캐싱
  }

}
