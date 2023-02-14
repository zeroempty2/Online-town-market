package com.example.townmarket.product.service;

import com.example.townmarket.commons.dto.PageDto;
import com.example.townmarket.product.dto.PagingProductResponse;
import com.example.townmarket.product.dto.ProductRequestDto;
import com.example.townmarket.product.dto.ProductResponseDto;
import com.example.townmarket.product.entity.Product;
import com.example.townmarket.product.repository.ProductRepository;
import com.example.townmarket.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  @Override
  @Transactional
  public void addProduct(User user, ProductRequestDto productRequestDto) {
    Product product = Product.builder()
        .productName(productRequestDto.getProductName())
        .productPrice(productRequestDto.getProductPrice())
        .productStatus(productRequestDto.getProductStatus())
        .productCategory(productRequestDto.getProductCategory())
        .productEnum(productRequestDto.getProductEnum())
        .user(user)
        .build();
    productRepository.save(product);
  }

  @Override
  @Transactional(readOnly = true)
  public ProductResponseDto showProduct(Long productId) {
    Product product = findProductById(productId);
    return ProductResponseDto.builder()
        .productId(product.getId())
        .productName(product.getProductName())
        .productPrice(product.getProductPrice())
        .productStatus(product.getProductStatus())
        .productCategory(product.getProductCategory())
        .build();
  }

  @Override
  @Transactional
  public Page<PagingProductResponse> viewAllProduct(PageDto pageDto) {
    Page<Product> products = productRepository.findAll(pageDto.toPageable());
    return products.map(PagingProductResponse::new);
  }

  @Override
  @Transactional
  public void updateProduct(Long productId, ProductRequestDto productDto, User user) {
    Product product = findProductById(productId);
    if (!user.checkAuthorization(user)) {
      throw new IllegalArgumentException("본인의 상품이 아닙니다");
    }
    product.update(productDto);
  }

  @Override
  @Transactional
  public void deleteProduct(Long productId, User user) {
    Product product = findProductById(productId);

    if (!user.checkAuthorization(user)) {
      throw new IllegalArgumentException("본인의 상품이 아닙니다");
    }
    productRepository.deleteById(product.getId());
  }

  public Product findProductById(Long productId) {
    return productRepository.findById(productId).orElseThrow(
        () -> new IllegalArgumentException("존재하지 않는 상품입니다")
    );
  }

}
