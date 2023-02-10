package com.example.townmarket.review.service;

import com.example.townmarket.commons.dto.PageDto;
import com.example.townmarket.review.domain.Review;
import com.example.townmarket.review.dto.CreateReviewRequestDto;
import com.example.townmarket.review.dto.ReviewResponseDto;
import com.example.townmarket.review.dto.UpdateReviewRequestDto;
import com.example.townmarket.review.repository.ReviewRepository;
import com.example.townmarket.user.entity.User;
import com.example.townmarket.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Lombok
@RequiredArgsConstructor
//jpa
@Service
public class ReviewServiceImpl implements ReviewService {

  private final ReviewRepository reviewRepository;
  private final UserServiceImpl userService;

  @Override
  @Transactional
  public void createReview(CreateReviewRequestDto createReviewRequestDto, User reviewer) {
    if (reviewRepository.existsReviewByProductId(createReviewRequestDto.getProductId())) {
      throw new IllegalArgumentException("작성된 리뷰가 있습니다");
    }
    User reviewee = userService.findUserById(createReviewRequestDto.getRevieweeId());
    Review review = Review.builder()
        .grade(createReviewRequestDto.getGrade())
        .review(createReviewRequestDto.getReview())
        .reviewee(reviewee)
        .reviewer(reviewer)
        .productId(createReviewRequestDto.getProductId())
        .build();
    reviewRepository.save(review);
    setRevieweeGrade(createReviewRequestDto, reviewee);
  }

  @Override
  @Transactional(readOnly = true)
  public ReviewResponseDto showSelectReview(Long reviewId) {
    return reviewRepository.findProfileAndReviewByReviewId(reviewId);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<ReviewResponseDto> showMyReviews(PageDto pageDto, User user) {
    Pageable pageable = pageDto.toPageable();
    return reviewRepository.findProfileAndReviewByReviewIdPaging(pageable, user);
  }

  @Override
  @Transactional
  public void updateMyReview(Long reviewId, User user,
      UpdateReviewRequestDto updateReviewRequestDto) {
    Review review = findReviewById(reviewId);
    reviewWriterCheck(review, user);
    review.updateReview(updateReviewRequestDto);
    updateRevieweeGrade(review, updateReviewRequestDto.getGrade());
  }

  @Override
  @Transactional
  public void deleteReview(Long reviewId, User user) {
    Review review = findReviewById(reviewId);
    reviewWriterCheck(review, user);
    reviewRepository.deleteById(reviewId);
  }

  @Override
  public Review findReviewById(Long id) {
    return reviewRepository.findById(id).orElseThrow(
        () -> new IllegalArgumentException("잘못된 Id입니다")
    );
  }

  private void reviewWriterCheck(Review review, User user) {
    if (!review.isReviewWriter(user)) {
      throw new IllegalArgumentException("작성자가 아닙니다");
    }
  }

  private void setRevieweeGrade(CreateReviewRequestDto createReviewRequestDto, User reviewee) {
    int reviewCount = reviewRepository.countByRevieweeId(createReviewRequestDto.getRevieweeId());
    userService.SetUserGrade(reviewee, createReviewRequestDto.getGrade(), reviewCount);
  }

  private void updateRevieweeGrade(Review review, int updateGrade) {
    User reviewee = review.getReviewee();
    int grade = review.getGrade() - updateGrade;
    userService.updateUserGrade(reviewee, grade);
  }
}
