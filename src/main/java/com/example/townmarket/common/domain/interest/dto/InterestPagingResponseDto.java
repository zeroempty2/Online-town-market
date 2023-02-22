package com.example.townmarket.common.domain.interest.dto;

import lombok.Getter;

@Getter
public class InterestPagingResponseDto {

  private String productName;
  private Long productPrice;

  public InterestPagingResponseDto(String productName, Long productPrice) {
    this.productName = productName;
    this.productPrice = productPrice;
  }
}
