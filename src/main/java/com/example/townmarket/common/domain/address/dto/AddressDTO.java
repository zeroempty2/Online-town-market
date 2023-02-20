package com.example.townmarket.common.domain.address.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddressDTO {
  double x;
  double y;

  @Builder
  public AddressDTO(double x, double y) {
    this.x = x;
    this.y = y;
  }
}
