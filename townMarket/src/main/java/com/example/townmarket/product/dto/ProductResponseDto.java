package com.example.townmarket.product.dto;

import com.example.townmarket.product.entity.Product.ProductCategory;
import com.example.townmarket.product.entity.Product.ProductEnum;
import com.example.townmarket.product.entity.Product.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ProductResponseDto {

  private long id;

  private String productName;

  private long productPrice;

  private ProductStatus productStatus;

  private ProductCategory productCategory;

  private ProductEnum productEnum;

}
