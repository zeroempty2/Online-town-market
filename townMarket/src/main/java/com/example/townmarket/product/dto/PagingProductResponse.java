package com.example.townmarket.product.dto;

import com.example.townmarket.product.entity.Product;
import com.example.townmarket.product.entity.Product.ProductCategory;
import com.example.townmarket.product.entity.Product.ProductEnum;
import com.example.townmarket.product.entity.Product.ProductStatus;

public class PagingProductResponse {

  private String productName;

  private Long productPrice;

  private ProductStatus productStatus;

  private ProductCategory productCategory;

  private ProductEnum productEnum;

  public PagingProductResponse(Product product) {
    this.productName = product.getProductName();
    this.productPrice = product.getProductPrice();
    this.productStatus = product.getProductStatus();
    this.productCategory = product.getProductCategory();
    this.productEnum = product.getProductEnum();
  }
}
