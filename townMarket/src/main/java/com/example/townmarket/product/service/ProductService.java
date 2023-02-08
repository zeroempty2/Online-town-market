package com.example.townmarket.product.service;

import com.example.townmarket.commons.dto.PageDto;
import com.example.townmarket.product.dto.PagingProductResponse;
import com.example.townmarket.product.dto.ProductDto;
import com.example.townmarket.product.entity.Product;
import com.example.townmarket.user.entity.User;
import java.util.List;
import org.springframework.data.domain.Page;

public interface ProductService {

  // 상품 생성
  String addProduct(User user, ProductDto productDto);

  // 단일 상품 조회
  ProductDto showProduct(long productId);

  // 전체 상품 조회
  Page<PagingProductResponse> viewAllProducts(PageDto pageDto);

  // 상품 내용 수정
  String updateProduct(long productId, ProductDto productDto);

  // 상품 삭제
  void deleteProduct(long productId);

  List<Product> findAllProduct();

}
