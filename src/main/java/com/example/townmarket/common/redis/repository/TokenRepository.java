package com.example.townmarket.common.redis.repository;


import com.example.townmarket.common.redis.entity.Tokens;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<Tokens, Long> {


  boolean existsByAccessToken(String token);

  boolean existsByRefreshToken(String token);
}
