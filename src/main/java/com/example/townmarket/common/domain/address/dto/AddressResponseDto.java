package com.example.townmarket.common.domain.address.dto;

import com.example.townmarket.common.domain.address.entity.Address;
import lombok.Getter;

@Getter
public class AddressResponseDto {
  private String address;

  private String address2;

  private String address3;

  public AddressResponseDto(Address address) {
    this.address = address.getAddress();
    this.address2 = address.getAddress2();
    this.address3 = address.getAddress3();
  }
}