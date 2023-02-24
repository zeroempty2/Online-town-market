package com.example.townmarket.fixture;

import com.example.townmarket.common.domain.product.dto.PagingProductResponse;
import com.example.townmarket.common.domain.product.dto.ProductRequestDto;
import com.example.townmarket.common.domain.product.dto.ProductResponseDto;
import com.example.townmarket.common.domain.product.entity.Product.ProductCategory;
import com.example.townmarket.common.domain.product.entity.Product.ProductEnum;
import com.example.townmarket.common.domain.product.entity.Product.ProductStatus;

public class ProductFixture {

  public static final ProductRequestDto PRODUCT_REQUEST_DTO = ProductRequestDto.builder()
      .productName("productName")
      .productPrice(1000)
      .productStatus(ProductStatus.S)
      .productCategory(ProductCategory.CAR)
      .productEnum(ProductEnum.나눔)
      .build();


  public static final ProductResponseDto PRODUCT_RESPONSE_DTO = ProductResponseDto.builder()
      .productId(1L)
      .productName("productName")
      .productPrice(1000)
      .productStatus(ProductStatus.S)
      .productCategory(ProductCategory.CAR)
      .productEnum(ProductEnum.나눔)
      .build();

  public static final PagingProductResponse PAGING_PRODUCT_RESPONSE = PagingProductResponse.builder().productId(1L)
      .productName("상품이름").productPrice(1000L).build();


  public static final Long PRODUCT_ID = 1L;
}
