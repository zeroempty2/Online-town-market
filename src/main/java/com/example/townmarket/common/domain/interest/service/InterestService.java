package com.example.townmarket.common.domain.interest.service;

import com.example.townmarket.common.domain.interest.dto.InterestPagingResponseDto;
import com.example.townmarket.common.domain.interest.dto.InterestRequestDto;
import com.example.townmarket.common.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InterestService {

  void addInterest(User user, InterestRequestDto interestRequestDto);

  void deleteInterest(Long userId, Long productId);

  Page<InterestPagingResponseDto> showMyInterestProducts(User user, Pageable pageable);
}
