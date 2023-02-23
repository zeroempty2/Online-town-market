package com.example.townmarket.common.domain.user.repository;


import static com.example.townmarket.common.domain.user.entity.QUser.user;

import com.example.townmarket.common.domain.user.dto.ProfileResponseDto;
import com.example.townmarket.common.dto.UserInformation;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
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

  @Override
  public Optional<UserInformation> getUserInfoByUsername(String username) {
    return Optional.ofNullable(jpaQueryFactory.select(Projections.constructor(UserInformation.class,
            user.id,
            user.username,
            user.password,
            user.role))
        .from(user)
        .where(user.username.eq(username))
        .setHint("org.hibernate.readOnly", true)
        .fetchFirst());
  }

  @Override
  @Transactional(readOnly = true)
  public ProfileResponseDto getProfileByUsername(String username) {
    return jpaQueryFactory.select(Projections.constructor(ProfileResponseDto.class,
            user.profile))
        .from(user)
        .where(user.username.eq(username))
        .setHint("org.hibernate.readOnly", true)
        .fetchFirst();
  }
}
