package com.example.townmarket.common.domain.product.dto;

import lombok.Builder;
import lombok.Getter;


@Getter
public class PagingProductResponse {

  private String productName;

  private long productPrice;

  private long productId;

  @Builder
  public PagingProductResponse(String productName, long productPrice, long productId) {
    this.productName = productName;
    this.productPrice = productPrice;
    this.productId = productId;
  }
}
