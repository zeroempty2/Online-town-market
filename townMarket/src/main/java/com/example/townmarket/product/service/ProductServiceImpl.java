package com.example.townmarket.product.service;

import com.example.townmarket.commons.dto.PageDto;
import com.example.townmarket.product.dto.ProductDto;
import com.example.townmarket.product.entity.Product;
import com.example.townmarket.product.repository.ProductRepository;
import com.example.townmarket.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  @Override
  @Transactional
  public String addProduct(User user, ProductDto productDto) {
    Product product = Product.builder()
        .productName(productDto.getProductName())
        .productPrice(productDto.getProductPrice())
        .productStatus(productDto.getProductStatus())
        .productCategory(productDto.getProductCategory())
        .productEnum(productDto.getProductEnum())
        .build();
    productRepository.save(product);
    return "상품을 성공적으로 등록했습니다";
  }

  @Override
  @Transactional
  public ProductDto showProduct(long productId) {
    Product product = productRepository.findById(productId).orElseThrow(
        () -> new IllegalArgumentException("존재하지 않는 상품입니다")
    );

    return new ProductDto(
        product.getProductName(),
        product.getProductPrice(),
        product.getProductStatus(),
        product.getProductCategory(),
        product.getProductEnum()
    );
  }

  @Override
  @Transactional
  public Page<ProductDto> viewAllProducts(PageDto pageDto) {
    return productRepository.findAll(makePage(pageDto))
        .map(product -> new ProductDto(
            product.getProductName(),
            product.getProductPrice(),
            product.getProductStatus(),
            product.getProductCategory(),
            product.getProductEnum()));
  }

  @Override
  @Transactional
  public String updateProduct(long productId, ProductDto productDto) {
    Product product = productRepository.findById(productId).orElseThrow(
        () -> new IllegalArgumentException("존재하지 않는 상품입니다")
    );
    product.update(productDto);
    return "상품이 성공적으로 업데이트되었습니다";
  }

  @Override
  @Transactional
  public void deleteProduct(long productId) {
    Product product = productRepository.findById(productId).orElseThrow(
        () -> new IllegalArgumentException("존재하지 않는 상품입니다")
    );

    productRepository.deleteById(product.getId());
  }

  // Pageable 생성 메서드
  public Pageable makePage(PageDto pageDto) {
    Sort.Direction direction = pageDto.isAsc() ? Sort.Direction.ASC : Sort.Direction.DESC;
    Sort sort = Sort.by(direction, pageDto.getSortBy());
    return PageRequest.of(pageDto.getPage() - 1, pageDto.getSize(), sort);
  }
}
