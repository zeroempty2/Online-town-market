package com.example.townmarket.review.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.townmarket.commons.dto.PageDto;
import com.example.townmarket.review.domain.Review;
import com.example.townmarket.review.dto.CreateReviewRequestDto;
import com.example.townmarket.review.dto.ReviewResponseDto;
import com.example.townmarket.review.dto.UpdateReviewRequestDto;
import com.example.townmarket.review.repository.ReviewRepository;
import com.example.townmarket.user.entity.User;
import com.example.townmarket.user.service.UserServiceImpl;
import java.util.Objects;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class ReviewServiceImplTest {

  @InjectMocks
  ReviewServiceImpl reviewService;
  @Mock
  UserServiceImpl userService;
  @Mock
  ReviewRepository reviewRepository;


  @Test
  @DisplayName("리뷰 작성")
  void createReview() {
    //given
    var createReviewRequestDto = mock(CreateReviewRequestDto.class);
    var reviewer = mock(User.class);
    when(reviewRepository.existsReviewByProductId(any(Long.class))).thenReturn(false);
    when(userService.findUserById(any(Long.class))).thenReturn(mock(User.class));
    //when
    reviewService.createReview(createReviewRequestDto, reviewer);
    //then

    //verify
    verify(userService).findUserById(any(Long.class));
    verify(reviewRepository).save(isA(Review.class));
  }

  @Test
  @DisplayName("선택 리뷰 조회")
  void showSelectReview() {
    //given
    var reviewResponseDto = mock(ReviewResponseDto.class);
    when(reviewRepository.searchByReviewId(any(Long.class))).thenReturn(
        reviewResponseDto);
    when(reviewRepository.existsReviewByProductId((any(Long.class)))).thenReturn(true);
    //when
    var reviewResponseDtoResult = reviewService.showSelectReview(any(Long.class));
    //then
    assertThat(reviewResponseDtoResult).isEqualTo(reviewResponseDto);
    //verify
    verify(reviewRepository).searchByReviewId(any(Long.class));
  }

  @Test
  @DisplayName("본인이 쓴 리뷰들 조회")
  void showMyReviews() {
    //given
    var pageDto = mock(PageDto.class);
    var user = mock(User.class);
    var pageable = mock(Pageable.class);
    when(pageDto.toPageable()).thenReturn(pageable);
    when(reviewRepository.searchByUserAndPaging(user, pageable)).thenReturn(
        Page.empty());
    //when
    var reviewResponseDtoPage = reviewService.showMyReviews(pageDto, user);
    //then
    assertThat(reviewResponseDtoPage).isEmpty();
  }

  @Test
  @DisplayName("본인이 리뷰 수정")
  void updateMyReview() {
    //given

    var review = mock(Review.class);
    var user = mock(User.class);
    var updateReviewRequestDto = mock(UpdateReviewRequestDto.class);

    when(reviewRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(
        review)); // doNothing().when(myList).add(isA(Integer.class) 과 같은 형식으로 void도 처리가능
    when(Objects.requireNonNull(review).isReviewWriter(user)).thenReturn(true);

    //when
    reviewService.updateMyReview(any(Long.class), user, updateReviewRequestDto);
    //then

    //verify
    verify(review).updateReview(updateReviewRequestDto);
    verify(userService).updateUserGrade(any(), any(Integer.class));
  }

  @Test
  @DisplayName("리뷰 삭제")
  void deleteReview() {
    //given
    var review = mock(Review.class);
    var reviewee = mock(User.class);
    var user = mock(User.class);
    when(reviewRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(
        review));
    when(review.getReviewee()).thenReturn(reviewee);
    when(review.isReviewWriter(user)).thenReturn(true);

    //when
    reviewService.deleteReview(any(Long.class), user);

    //then

    //verify
    verify(reviewRepository).deleteById(any(Long.class));
    verify(userService).setUserGrade(any(), any(Integer.class), any(Integer.class));
  }

  @Test
  @DisplayName("리뷰Id로 리뷰 불러오기")
  void findReviewById() {
    //given
    var review = mock(Review.class);
    when(reviewRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(review));
    //when
    var resultReview = reviewService.findReviewById(any(Long.class));
    //then
    assertThat(resultReview).isEqualTo(review);
    //verify
    verify(reviewRepository).findById(any(Long.class));
  }

  @Test
  @DisplayName("리뷰를 쓴 본인인지 확인하기")
  void reviewWriterCheck() {
    //given
    var review = mock(Review.class);
    var user = mock(User.class);
    when(review.isReviewWriter(user)).thenReturn(true);
    //when
    reviewService.reviewWriterCheck(review, user);
    //then

    //verify
    verify(review).isReviewWriter(user);
  }

}
