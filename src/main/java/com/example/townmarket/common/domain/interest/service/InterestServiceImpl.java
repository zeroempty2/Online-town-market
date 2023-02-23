package com.example.townmarket.common.domain.interest.service;

import com.example.townmarket.common.domain.interest.dto.InterestPagingResponseDto;
import com.example.townmarket.common.domain.interest.dto.InterestRequestDto;
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
  public void addInterest(User user, InterestRequestDto interestRequestDto) {
    if (interestRepository.existsByUserIdAndProductId(user.getId(),
        interestRequestDto.getProductId())) {
      deleteInterest(user.getId(), interestRequestDto.getProductId());
    }

    Interest interest = Interest.builder()
        .user(user)
        .productId(interestRequestDto.getProductId())
        .build();
    interestRepository.save(interest);
    productService.findProductById(interestRequestDto.getProductId()).plusInterest();
  }

  @Override
  @Transactional
  public void deleteInterest(Long userId, Long productId) {
    Interest interest = interestRepository.findByUserIdAndProductId(userId, productId).orElseThrow(
        () -> new IllegalArgumentException("존재하지 않습니다")
    );
    if (!interest.isInterestWriter(userId)) {
      throw new IllegalArgumentException("유효한 계정이 아닙니다.");
    }
    interestRepository.delete(interest);
    productService.findProductById(productId).minusInterest();
  }

  @Override
  @Transactional(readOnly = true)
  public Page<InterestPagingResponseDto> showMyInterestProducts(User user, PageDto pageDto) {
    return interestRepository.searchInterestIndexByUser(user, pageDto.toPageable());
  }
}
