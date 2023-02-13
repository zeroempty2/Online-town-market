package com.example.townmarket.product.service;

import com.example.townmarket.commons.dto.PageDto;
import com.example.townmarket.product.dto.PagingProductResponse;
import com.example.townmarket.product.dto.ProductRequestDto;
import com.example.townmarket.product.dto.ProductResponseDto;
import com.example.townmarket.product.entity.Product;
import com.example.townmarket.product.repository.ProductRepository;
import com.example.townmarket.user.entity.User;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  @Override
  @Transactional
  public String addProduct(User user, ProductRequestDto productRequestDto) {
    Product product = Product.builder()
        .productName(productRequestDto.getProductName())
        .productPrice(productRequestDto.getProductPrice())
        .productStatus(productRequestDto.getProductStatus())
        .productCategory(productRequestDto.getProductCategory())
        .productEnum(productRequestDto.getProductEnum())
        .userId(user.getId())
        .build();
    productRepository.save(product);
    return "상품을 성공적으로 등록했습니다";
  }

  @Override
  @Transactional
  public ProductResponseDto showProduct(long productId) {
    Product product = findProductById(productId);

    return new ProductResponseDto(
        product.getId(),
        product.getProductName(),
        product.getProductPrice(),
        product.getProductStatus(),
        product.getProductCategory(),
        product.getProductEnum()
    );
  }

  @Override
  @Transactional
  public Page<PagingProductResponse> viewAllProduct(PageDto pageDto) { //페이지로 받아오게
    List<Product> products = findAllProduct();
    return new PageImpl<>(
        products.stream().map(PagingProductResponse::new).collect(Collectors.toList()),
        pageDto.toPageable(),
        pageDto.getSize());
  }

  @Override
  @Transactional
  public String updateProduct(long productId, ProductRequestDto productDto, User user) {
    Product product = findProductById(productId);
    if (user.getId().equals(productId)) {
      product.update(productDto);
      return "상품이 성공적으로 업데이트되었습니다";
    } else {
      throw new IllegalArgumentException("본인의 상품이 아닙니다");
    }
  }

  @Override
  @Transactional
  public void deleteProduct(long productId, User user) {
    Product product = productRepository.findById(productId).orElseThrow(
        () -> new IllegalArgumentException("존재하지 않는 상품입니다")
    );

    if (user.getId().equals(productId)) {
      productRepository.deleteById(product.getId());
    } else {
      throw new IllegalArgumentException("본인의 상품이 아닙니다");
    }
  }

  @Override
  public List<Product> findAllProduct() {
    return productRepository.findAll();
  }

  public Product findProductById(Long productId) {
    Product product = productRepository.findById(productId).orElseThrow(
        () -> new IllegalArgumentException("존재하지 않는 상품입니다")
    );
    return product;
  }

}
