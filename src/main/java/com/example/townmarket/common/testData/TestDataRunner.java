package com.example.townmarket.common.testData;

import com.example.townmarket.common.domain.product.entity.Product;
import com.example.townmarket.common.domain.product.entity.Product.ProductCategory;
import com.example.townmarket.common.domain.product.entity.Product.ProductEnum;
import com.example.townmarket.common.domain.product.entity.Product.ProductStatus;
import com.example.townmarket.common.domain.product.repository.ProductRepository;
import com.example.townmarket.common.domain.user.dto.SignupRequestDto;
import com.example.townmarket.common.domain.user.entity.Profile;
import com.example.townmarket.common.domain.user.entity.User;
import com.example.townmarket.common.domain.user.repository.UserRepository;
import com.example.townmarket.common.domain.user.service.UserServiceImpl;
import com.example.townmarket.common.enums.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class TestDataRunner implements ApplicationRunner {

  private final UserRepository userRepository;
  private final ProductRepository productRepository;

  private final UserServiceImpl userService;

  @Override
  @Transactional
  public void run(ApplicationArguments args) throws Exception {
    Profile profile = Profile.builder().nickName("testUser").build();

    User user = User.builder()
        .username("user")
        .password("Password!23")
        .email("asda11as@gmail.com")
        .role(RoleEnum.MEMBER)
        .profile(profile)
        .build();
    userRepository.save(user);

    SignupRequestDto user1 = SignupRequestDto.builder()
        .username("user1")
        .password("Password!23")
        .email("asdaas@gmail.com")
        .nickname("nick1")
        .build();
    userService.signup(user1);

    SignupRequestDto user2 = SignupRequestDto.builder()
        .username("user2")
        .password("Password!23")
        .email("asd1aas@gmail.com")
        .nickname("nick12")
        .build();
    userService.signup(user2);

    Product product1 = Product.builder().productName("product").productPrice(11111L)
        .productStatus(ProductStatus.S)
        .productCategory(ProductCategory.IT)
        .productEnum(ProductEnum.나눔)
        .user(user)
        .build();
    productRepository.save(product1);

    for (int i = 0; i < 50; i++) {
      String productName = "product" + i;
      Product product = Product.builder().productName(productName).productPrice(11111L)
          .productStatus(ProductStatus.S)
          .productCategory(ProductCategory.IT)
          .productEnum(ProductEnum.나눔)
          .user(user)
          .build();
      productRepository.save(product);
    }

  }
}
