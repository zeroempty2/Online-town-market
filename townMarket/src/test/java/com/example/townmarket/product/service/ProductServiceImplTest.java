package com.example.townmarket.product.service;

import static com.example.townmarket.product.entity.Product.ProductCategory.IT;
import static com.example.townmarket.product.entity.Product.ProductEnum.판매_중;
import static com.example.townmarket.product.entity.Product.ProductStatus.S;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
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
    ProductRequestDto productRequestDto = ProductRequestDto.builder()
        .productName("상품입니다")
        .productPrice(15000L)
        .productStatus(S)
        .productCategory(IT)
        .productEnum(판매_중)
        .build();

    User user = mock(User.class);

    // when
    String addProductResponse = productService.addProduct(user, productRequestDto);

    // then
    assertThat(addProductResponse).isNotEmpty();
  }

  @Test
  @DisplayName("상품 단일 조회 성공")
  void showProduct() {
    // given
    ProductRequestDto productRequestDto = ProductRequestDto.builder()
        .productName("상품입니다")
        .productPrice(15000L)
        .productStatus(S)
        .productCategory(IT)
        .productEnum(판매_중)
        .build();

    User user = mock(User.class);

    Product product = new Product(
        productRequestDto.getProductName(),
        productRequestDto.getProductPrice(),
        productRequestDto.getProductStatus(),
        productRequestDto.getProductCategory(),
        productRequestDto.getProductEnum(),
        user.getId()
    );

    when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

    // when
    ProductResponseDto productResponse = productService.showProduct(product.getId());

    // then
    assertThat(productResponse.getProductName()).isEqualTo(productRequestDto.getProductName());
  }

  @Test
  void viewAllProduct() {
    // given

    // when

    // then
  }

  @Test
  @DisplayName("상품 업데이트 성공")
  void updateProduct() {
    // given
    ProductRequestDto productRequestDto = ProductRequestDto.builder()
        .productName("상품입니다")
        .productPrice(15000L)
        .productStatus(S)
        .productCategory(IT)
        .productEnum(판매_중)
        .build();

    User user = mock(User.class);

    Product product = new Product(
        productRequestDto.getProductName(),
        productRequestDto.getProductPrice(),
        productRequestDto.getProductStatus(),
        productRequestDto.getProductCategory(),
        productRequestDto.getProductEnum(),
        user.getId()
    );

    when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

    // when
    String updateResponse = productService.updateProduct(product.getId(), productRequestDto, user);

    // then
    assertThat(updateResponse).isEqualTo("상품이 성공적으로 업데이트되었습니다");
  }

//  @Test
//  @DisplayName("상품 삭제 성공")
//  void deleteProduct() {
//    // given
//    ProductRequestDto productRequestDto = ProductRequestDto.builder()
//        .productName("상품입니다")
//        .productPrice(15000L)
//        .productStatus(S)
//        .productCategory(IT)
//        .productEnum(판매_중)
//        .build();
//
//    User user = mock(User.class);
//
//    Product product = new Product(
//        productRequestDto.getProductName(),
//        productRequestDto.getProductPrice(),
//        productRequestDto.getProductStatus(),
//        productRequestDto.getProductCategory(),
//        productRequestDto.getProductEnum(),
//        user.getId()
//    );
//
//    when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
//
//    // when & then
//    verify(productRepository, times(1)).deleteById(product.getId());
//  }
}
