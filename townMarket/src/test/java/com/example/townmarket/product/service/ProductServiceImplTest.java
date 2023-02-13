package com.example.townmarket.product.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.townmarket.product.dto.ProductRequestDto;
import com.example.townmarket.product.dto.ProductResponseDto;
import com.example.townmarket.product.entity.Product;
import com.example.townmarket.product.repository.ProductRepository;
import com.example.townmarket.user.entity.User;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

  @Mock
  ProductRepository productRepository;

  @InjectMocks
  ProductServiceImpl productService;

  @Test
  @DisplayName("상품 등록 성공")
  void addProduct() {
    // given
    ProductRequestDto productRequestDto = mock(ProductRequestDto.class);
    User user = mock(User.class);

    // when
    productService.addProduct(user, productRequestDto);

    // then
    verify(productRepository).save(isA(Product.class));
  }

  @Test
  @DisplayName("상품 단일 조회 성공")
  void showProduct() {
    // given
    ProductRequestDto productRequestDto = mock(ProductRequestDto.class);
    Product product = mock(Product.class);

    when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

    // when
    ProductResponseDto productResponse = productService.showProduct(product.getId());

    // then
    assertThat(productResponse.getProductName()).isEqualTo(productRequestDto.getProductName());
  }

  @Test
  @DisplayName("상품목록 조회")
  void viewAllProduct() {
    // given
    Product product = mock(Product.class);
    Product product2 = mock(Product.class);

    // when

    // then
  }

  @Test
  @DisplayName("상품 업데이트 성공")
  void updateProduct() {
    // given
    User user = mock(User.class);
    Product product = mock(Product.class);
    ProductRequestDto productRequestDto = mock(ProductRequestDto.class);

    when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
    when(Optional.of(user).get().checkAuthorization(user)).thenReturn(true);

    // when
    productService.updateProduct(product.getId(), productRequestDto, user);

    // then
    verify(product).update(productRequestDto);
  }

  @Test
  @DisplayName("상품 삭제 성공")
  void deleteProduct() {
    // given
    User user = mock(User.class);
    Product product = mock(Product.class);

    when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
    when(Optional.of(user).get().checkAuthorization(user)).thenReturn(true);

    // when
    productService.deleteProduct(product.getId(), user);

    // then
    verify(productRepository).deleteById(any(Long.class));
  }
}