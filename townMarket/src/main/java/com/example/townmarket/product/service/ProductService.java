package com.example.townmarket.product.service;

import com.example.townmarket.product.dto.ProductDto;
import com.example.townmarket.user.dto.ProfileRequestDto;
import com.example.townmarket.user.entity.User;
import java.awt.print.Pageable;
import org.springframework.data.domain.Page;

public interface ProductService {

  // 상품 생성
  String addProduct(User user, ProfileRequestDto profileRequestDto);

  // 단일 상품 조회
  ProductDto showProduct(long productId);

  // 전체 상품 조회
  Page<ProductDto> viewAllProducts(Pageable pageable);

  // 상품 내용 수정
  String updateProduct(long productId, ProfileRequestDto request);

  // 상품 삭제
  void deleteProduct(long productId);

}
