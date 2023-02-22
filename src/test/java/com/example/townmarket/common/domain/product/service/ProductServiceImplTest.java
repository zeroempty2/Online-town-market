package com.example.townmarket.common.domain.product.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.townmarket.common.domain.product.dto.PagingProductResponse;
import com.example.townmarket.common.domain.product.dto.ProductRequestDto;
import com.example.townmarket.common.domain.product.dto.ProductResponseDto;
import com.example.townmarket.common.domain.product.entity.Product;
import com.example.townmarket.common.domain.product.repository.ProductRepository;
import com.example.townmarket.common.domain.user.entity.User;
import com.example.townmarket.common.dto.PageDto;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
  void getProduct() {
    // given
    ProductRequestDto productRequestDto = mock(ProductRequestDto.class);
    Product product = mock(Product.class);

    when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

    // when
    ProductResponseDto productResponse = productService.getProduct(product.getId());

    // then
    assertThat(productResponse.getProductName()).isEqualTo(productRequestDto.getProductName());
  }

  @Test
  @DisplayName("상품목록 조회")
  void getProducts() {
    // given
    Pageable pageable = mock(Pageable.class);
    PageDto pageDto = mock(PageDto.class);

    when(pageDto.toPageable()).thenReturn(pageable);
    when(productRepository.findAll(pageable)).thenReturn(Page.empty());

    // when
    Page<PagingProductResponse> pagingProductResponse = productService.getProducts(pageDto);

    // then
    assertThat(pagingProductResponse).isNotNull();
  }

  @Test
  @DisplayName("상품 업데이트 성공")
  void updateProduct() {
    // given
    User user = mock(User.class);
    Product product = mock(Product.class);
    ProductRequestDto productRequestDto = mock(ProductRequestDto.class);

    when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
    when(Optional.of(product).get().checkProductWriter(isA(Long.class))).thenReturn(true);

    // when
    productService.updateProduct(product.getId(), productRequestDto, isA(Long.class));

    // then
    verify(product).update(productRequestDto);
  }


  @Test
  @DisplayName("상품 삭제 성공")
  void deleteProduct() {
    // given
    User user = mock(User.class);
    Product product = mock(Product.class);
    Long id = mock(Long.class);

    when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
    when(Optional.of(product).get().checkProductWriter(isA(Long.class))).thenReturn(true);

    // when
    productService.deleteProduct(product.getId(), isA(Long.class));

    // then
    verify(productRepository).deleteById(product.getId());
  }
}
