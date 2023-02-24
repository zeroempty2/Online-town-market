package com.example.townmarket.common.domain.interest.dto;

import lombok.Getter;

@Getter
public class InterestPagingResponseDto {

  private String productName;
  private Long productPrice;
  private Long productId;

  public InterestPagingResponseDto(String productName, Long productPrice, Long productId) {
    this.productName = productName;
    this.productPrice = productPrice;
    this.productId = productId;
  }
}
