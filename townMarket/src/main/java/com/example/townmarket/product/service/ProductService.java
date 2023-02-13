package com.example.townmarket.product.service;

import com.example.townmarket.commons.dto.PageDto;
import com.example.townmarket.product.dto.PagingProductResponse;
import com.example.townmarket.product.dto.ProductRequestDto;
import com.example.townmarket.product.dto.ProductResponseDto;
import com.example.townmarket.user.entity.User;
import org.springframework.data.domain.Page;

public interface ProductService {

  // 상품 생성
  void addProduct(User user, ProductRequestDto productDto);

  // 단일 상품 조회
  ProductResponseDto showProduct(Long productId);

  // 전체 상품 조회
  Page<PagingProductResponse> viewAllProduct(PageDto pageDto);

  // 상품 내용 수정
  void updateProduct(Long productId, ProductRequestDto productDto, User user);

  // 상품 삭제
  void deleteProduct(Long productId, User user);
}
