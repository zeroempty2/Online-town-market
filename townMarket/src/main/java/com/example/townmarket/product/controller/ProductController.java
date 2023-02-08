package com.example.townmarket.product.controller;

import com.example.townmarket.commons.dto.PageDto;
import com.example.townmarket.commons.security.UserDetailsImpl;
import com.example.townmarket.product.dto.ProductDto;
import com.example.townmarket.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @PostMapping("/products")
  public ResponseEntity addProduct(@RequestBody ProductDto productRequestDto,
      @AuthenticationPrincipal
      UserDetailsImpl userDetails) {
    return null;
  }

  // 단일 상품 조회
  @GetMapping("/products/{productId}")
  public ResponseEntity getProduct(@RequestParam Long productId, @AuthenticationPrincipal
  UserDetailsImpl userDetails) {
    return null;
  }

  // 전체 상품 조회
  @GetMapping("/products")
  public ResponseEntity getProducts(@RequestBody PageDto pageDto) {
    return null;
  }

  // 단일 상품 업데이트
  @PutMapping("/products/update")
  public ResponseEntity update(@RequestParam Long productId,
      @RequestBody ProductDto productRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    return null;
  }

  // 단일 상품 삭제
  @DeleteMapping("/products/{productId}")
  public ResponseEntity delete(@RequestParam Long productId,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    return null;
  }

}
