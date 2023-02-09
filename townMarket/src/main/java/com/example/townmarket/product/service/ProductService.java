package com.example.townmarket.product.service;

import com.example.townmarket.commons.dto.PageDto;
import com.example.townmarket.product.dto.PagingProductResponse;
import com.example.townmarket.product.dto.ProductRequestDto;
import com.example.townmarket.product.dto.ProductResponseDto;
import com.example.townmarket.product.entity.Product;
import com.example.townmarket.user.entity.User;
import java.util.List;
import org.springframework.data.domain.Page;

public interface ProductService {

  // 상품 생성
  String addProduct(User user, ProductRequestDto productDto);

  // 단일 상품 조회
  ProductResponseDto showProduct(long productId);

  // 전체 상품 조회
  Page<PagingProductResponse> viewAllProduct(PageDto pageDto);

  // 상품 내용 수정
  String updateProduct(long productId, ProductRequestDto productDto, User user);

  // 상품 삭제
  void deleteProduct(long productId, User user);

  List<Product> findAllProduct();

}
