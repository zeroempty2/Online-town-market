package com.example.townmarket.common.domain.address.repository;

import com.example.townmarket.common.domain.address.entity.Address;

public interface AddressRepositoryQuery {

  Address findAddressByUsername(String username);
}
