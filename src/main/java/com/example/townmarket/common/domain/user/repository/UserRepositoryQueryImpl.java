package com.example.townmarket.common.domain.user.repository;


import static com.example.townmarket.common.domain.user.entity.QUser.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class UserRepositoryQueryImpl implements UserRepositoryQuery {

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  @Transactional(readOnly = true)
  public boolean existByNickname(String nickname) {
    return jpaQueryFactory
        .from(user)
        .where(user.profile.nickName.eq(nickname))
        .select(user.profile.nickName)
        .setHint("org.hibernate.readOnly", true)
        .fetchFirst() != null;
  }
}
