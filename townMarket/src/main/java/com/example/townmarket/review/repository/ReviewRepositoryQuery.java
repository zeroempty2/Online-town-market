package com.example.townmarket.review.repository;

import com.example.townmarket.review.dto.ReviewResponseDto;
import com.example.townmarket.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewRepositoryQuery {

  ReviewResponseDto searchByReviewId(Long reviewId);

  Page<ReviewResponseDto> searchByUserAndPaging(User reviewer, Pageable pageable);
}
