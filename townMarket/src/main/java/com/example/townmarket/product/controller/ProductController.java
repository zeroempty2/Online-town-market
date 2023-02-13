package com.example.townmarket.product.controller;

import com.example.townmarket.commons.dto.PageDto;
import com.example.townmarket.commons.dto.StatusResponseDto;
import com.example.townmarket.commons.security.UserDetailsImpl;
import com.example.townmarket.commons.util.SetHttpHeaders;
import com.example.townmarket.product.dto.PagingProductResponse;
import com.example.townmarket.product.dto.ProductRequestDto;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  private final SetHttpHeaders httpHeaders;

  @PostMapping("/products")
  public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new StatusResponseDto(HttpStatus.CREATED.value(),
            productService.addProduct(userDetails.getUser(), productRequestDto)));
  }

  // 단일 상품 조회
  @GetMapping("/products/{productId}")
  public ResponseEntity getProduct(@PathVariable Long productId) {
    return ResponseEntity.status(HttpStatus.OK).body(productService.showProduct(productId));
  }

  // 전체 상품 조회
  @GetMapping("/products")
  public ResponseEntity<Page<PagingProductResponse>> getProducts(@RequestBody PageDto pageDto) {
    HttpHeaders headers = httpHeaders.setHeaderTypeJson();
    return ResponseEntity.status(HttpStatus.OK).headers(headers)
        .body(productService.viewAllProduct(pageDto));
  }

  // 단일 상품 업데이트
  @PutMapping("/products/update")
  public ResponseEntity update(@RequestParam Long productId,
      @RequestBody ProductRequestDto productRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(new StatusResponseDto(HttpStatus.OK.value(),
            productService.updateProduct(productId, productRequestDto, userDetails.getUser())));
  }

  // 단일 상품 삭제
  @DeleteMapping("/products/{productId}")
  public ResponseEntity delete(@PathVariable Long productId,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    HttpHeaders headers = httpHeaders.setHeaderTypeJson();
    productService.deleteProduct(productId, userDetails.getUser());
    return ResponseEntity.status(HttpStatus.OK).headers(headers)
        .body(new StatusResponseDto(HttpStatus.OK.value(), "삭제가 완료되었습니다"));
  }

}
