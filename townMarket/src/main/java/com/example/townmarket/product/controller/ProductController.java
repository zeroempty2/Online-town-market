package com.example.townmarket.product.controller;

import com.example.townmarket.commons.dto.PageDto;
import com.example.townmarket.commons.responseMessageData.DefaultResponse;
import com.example.townmarket.commons.responseMessageData.ResponseMessages;
import com.example.townmarket.commons.security.UserDetailsImpl;
import com.example.townmarket.commons.util.SetHttpHeaders;
import com.example.townmarket.product.dto.PagingProductResponse;
import com.example.townmarket.product.dto.ProductRequestDto;
import com.example.townmarket.product.dto.ProductResponseDto;
import com.example.townmarket.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;

  private final SetHttpHeaders httpHeaders;

  // 상품 생성
  @PostMapping
  public ResponseEntity<DefaultResponse> addProduct(
      @RequestBody ProductRequestDto productRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    productService.addProduct(userDetails.getUser(), productRequestDto);
    DefaultResponse defaultResponse = DefaultResponse.valueOf(ResponseMessages.CREATED_SUCCESS);
    return ResponseEntity.status(HttpStatus.CREATED).body(defaultResponse);
  }

  // 단일 상품 조회
  @GetMapping("/{productId}")
  public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long productId) {
    HttpHeaders headers = httpHeaders.setHeaderTypeJson();
    return ResponseEntity.ok().headers(headers)
        .body(productService.showProduct(productId));
  }

  // 전체 상품 조회
  @GetMapping
  public ResponseEntity<Page<PagingProductResponse>> getProducts(@RequestBody PageDto pageDto) {
    HttpHeaders headers = httpHeaders.setHeaderTypeJson();
    return ResponseEntity.ok().headers(headers)
        .body(productService.viewAllProduct(pageDto));
  }

  // 단일 상품 업데이트
  @PutMapping("/update")
  public ResponseEntity<DefaultResponse> update(@RequestParam Long productId,
      @RequestBody ProductRequestDto productRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    productService.updateProduct(productId, productRequestDto, userDetails.getUser());
    DefaultResponse defaultResponse = DefaultResponse.valueOf(ResponseMessages.SUCCESS);
    return ResponseEntity.ok().body(defaultResponse);
  }

  // 단일 상품 삭제
  @DeleteMapping("/{productId}")
  public ResponseEntity<DefaultResponse> delete(@PathVariable Long productId,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    productService.deleteProduct(productId, userDetails.getUser());
    DefaultResponse defaultResponse = DefaultResponse.valueOf(ResponseMessages.DELETE_SUCCESS);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(defaultResponse);
  }

}
