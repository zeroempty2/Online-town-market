package com.example.townmarket.common.domain.product.dto;

import lombok.Builder;
import lombok.Getter;


@Getter
public class PagingProductResponse {

  private String productName;

  private long productPrice;


  @Builder
  public PagingProductResponse(String productName, long productPrice) {
    this.productName = productName;
    this.productPrice = productPrice;
  }
}
