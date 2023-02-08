package com.example.townmarket.product.dto;

import com.example.townmarket.product.entity.Product.ProductCategory;
import com.example.townmarket.product.entity.Product.ProductEnum;
import com.example.townmarket.product.entity.Product.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDto {

  private String ProductName;

  private Long ProductPrice;

  private ProductStatus productStatus;

  private ProductCategory productCategory;

  private ProductEnum productEnum;

}
