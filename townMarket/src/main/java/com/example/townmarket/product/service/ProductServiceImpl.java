package com.example.townmarket.product.service;

import com.example.townmarket.product.dto.ProductDto;
import com.example.townmarket.product.repository.ProductRepository;
import com.example.townmarket.user.dto.ProfileRequestDto;
import com.example.townmarket.user.entity.User;
import java.awt.print.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  @Override
  public String addProduct(User user, ProfileRequestDto profileRequestDto) {
    return null;
  }

  @Override
  public ProductDto showProduct(long productId) {
    return null;
  }

  @Override
  public Page<ProductDto> viewAllProducts(Pageable pageable) {
    return null;
  }

  @Override
  public String updateProduct(long productId, ProfileRequestDto request) {
    return null;
  }

  @Override
  public void deleteProduct(long productId) {

  }
}
