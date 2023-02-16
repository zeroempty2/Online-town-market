package com.example.townmarket.common.domain.product.dto;

import com.example.townmarket.common.domain.product.entity.Product.ProductCategory;
import com.example.townmarket.common.domain.product.entity.Product.ProductEnum;
import com.example.townmarket.common.domain.product.entity.Product.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class ProductRequestDto {

  private String productName;

  private long productPrice;

  private ProductStatus productStatus;

  private ProductCategory productCategory;

  private ProductEnum productEnum;

  @Builder
  public ProductRequestDto(String productName, long productPrice, ProductStatus productStatus,
      ProductCategory productCategory, ProductEnum productEnum) {
    this.productName = productName;
    this.productPrice = productPrice;
    this.productStatus = productStatus;
    this.productCategory = productCategory;
    this.productEnum = productEnum;
  }
}
