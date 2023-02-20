package com.example.townmarket.common.domain.address.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class AddressRequest {
  double x;
  double y;
}