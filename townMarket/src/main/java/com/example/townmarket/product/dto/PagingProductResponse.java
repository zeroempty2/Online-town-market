package com.example.townmarket.product.dto;

import com.example.townmarket.product.entity.Product;

public class PagingProductResponse {

  private String productName;

  private Long productPrice;

  public PagingProductResponse(Product product) {
    this.productName = product.getProductName();
    this.productPrice = product.getProductPrice();
  }
}
