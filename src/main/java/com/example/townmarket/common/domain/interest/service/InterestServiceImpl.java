package com.example.townmarket.common.domain.interest.service;

import com.example.townmarket.common.domain.interest.dto.InterestPagingResponseDto;
import com.example.townmarket.common.domain.interest.entity.Interest;
import com.example.townmarket.common.domain.interest.repository.InterestRepository;
import com.example.townmarket.common.domain.product.service.ProductServiceImpl;
import com.example.townmarket.common.domain.user.entity.User;
import com.example.townmarket.common.dto.PageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InterestServiceImpl implements InterestService {

  private final InterestRepository interestRepository;
  private final ProductServiceImpl productService;

  @Override
  @Transactional
  public void addInterest(User user, Long productId) {
    if (interestRepository.existsByUserIdAndProductId(user.getId(), productId)) {
      interestRepository.deleteByProductId(productId);
      return;
    }

    Interest interest = Interest.builder()
        .user(user)
        .productId(productId)
        .build();
    interestRepository.save(interest);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<InterestPagingResponseDto> showMyInterestProducts(User user, PageDto pageDto) {
    return interestRepository.searchInterestIndexByUser(user, pageDto.toPageable());
  }
}
