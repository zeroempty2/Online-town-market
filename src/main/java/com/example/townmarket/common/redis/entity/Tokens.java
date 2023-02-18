package com.example.townmarket.common.redis.entity;


import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@Getter
@NoArgsConstructor
@RedisHash(value = "AccessRefreshTK", timeToLive = 60 * 60 * 60 * 1000)
public class Tokens {

  @Id
  private Long userId;

  private String accessToken;
  private String refreshToken;

  @Builder
  public Tokens(String accessToken, String refreshToken, Long userId) {
    this.userId = userId;
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
  }
}
