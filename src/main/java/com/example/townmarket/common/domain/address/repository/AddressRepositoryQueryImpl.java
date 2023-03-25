package com.example.townmarket.common.domain.address.repository;

import static com.example.townmarket.common.domain.address.entity.QAddress.address1;

import com.example.townmarket.common.domain.address.entity.Address;
import com.example.townmarket.common.domain.address.entity.QAddress.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddressRepositoryQueryImpl implements AddressRepositoryQuery{
  private final JPAQueryFactory jpaQueryFactory;
  @Override
  public Address findAddressByUsername(String username) {
    return jpaQueryFactory.select(address1).from(address1).where(address1.user.username.eq(username)).fetchOne();
  }
}
