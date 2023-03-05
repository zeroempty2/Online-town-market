package com.example.townmarket.common.domain.address.dto;

import com.example.townmarket.common.domain.address.entity.Address;
import lombok.Getter;

@Getter
public class AddressResponseDto {
  private String address;

  public AddressResponseDto(Address address) {
    this.address = address.getAddress();
  }
}