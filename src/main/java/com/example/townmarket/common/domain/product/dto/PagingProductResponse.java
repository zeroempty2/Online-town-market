package com.example.townmarket.common.domain.product.dto;

import com.example.townmarket.common.domain.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagingProductResponse {

  private String productName;

  private Long productPrice;

  public PagingProductResponse(Product product) {
    this.productName = product.getProductName();
    this.productPrice = product.getProductPrice();
  }
}
