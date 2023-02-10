package com.example.townmarket.review.service;

import com.example.townmarket.commons.dto.PageDto;
import com.example.townmarket.review.domain.Review;
import com.example.townmarket.review.dto.CreateReviewRequestDto;
import com.example.townmarket.review.dto.ReviewResponseDto;
import com.example.townmarket.review.dto.UpdateReviewRequestDto;
import com.example.townmarket.user.entity.User;
import org.springframework.data.domain.Page;

public interface ReviewService {

  void createReview(CreateReviewRequestDto createReviewRequestDto, User reviewer);

  ReviewResponseDto showSelectReview(Long reviewId);

  Page<ReviewResponseDto> showMyReviews(PageDto pageDto, User user);

  void updateMyReview(Long reviewId, User user, UpdateReviewRequestDto updateReviewRequestDto);

  void deleteReview(Long reviewId, User user);

  Review findReviewById(Long id);

}
