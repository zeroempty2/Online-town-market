package com.example.townmarket.product.controller;

import com.example.townmarket.commons.dto.PageDto;
import com.example.townmarket.commons.dto.StatusResponseDto;
import com.example.townmarket.commons.security.UserDetailsImpl;
import com.example.townmarket.product.dto.ProductDto;
import com.example.townmarket.product.service.ProductService;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

  @PostMapping("/products")
  public ResponseEntity addProduct(@RequestBody ProductDto productRequestDto,
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
  public ResponseEntity getProducts(@RequestBody PageDto pageDto) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return ResponseEntity.status(HttpStatus.OK).headers(headers)
        .body(productService.viewAllProducts(pageDto));
  }

  // 단일 상품 업데이트
  @PutMapping("/products/update")
  public ResponseEntity update(@RequestParam Long productId,
      @RequestBody ProductDto productRequestDto) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(new StatusResponseDto(HttpStatus.OK.value(),
            productService.updateProduct(productId, productRequestDto)));
  }

  // 단일 상품 삭제
  @DeleteMapping("/products/{productId}")
  public ResponseEntity delete(@PathVariable Long productId) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    productService.deleteProduct(productId);
    return ResponseEntity.status(HttpStatus.OK).headers(headers)
        .body(new StatusResponseDto(HttpStatus.OK.value(), "삭제가 완료되었습니다"));
  }

}
