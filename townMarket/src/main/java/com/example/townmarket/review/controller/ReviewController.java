package com.example.townmarket.review.controller;

import com.example.townmarket.commons.dto.PageDto;
import com.example.townmarket.commons.responseMessageData.DefaultResponse;
import com.example.townmarket.commons.responseMessageData.ResponseMessages;
import com.example.townmarket.commons.security.UserDetailsImpl;
import com.example.townmarket.commons.util.SetHttpHeaders;
import com.example.townmarket.review.dto.CreateReviewRequestDto;
import com.example.townmarket.review.dto.ReviewResponseDto;
import com.example.townmarket.review.dto.UpdateReviewRequestDto;
import com.example.townmarket.review.service.ReviewServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController {

  private final ReviewServiceImpl reviewService;
  private final SetHttpHeaders setHttpHeaders;

  @PostMapping("/review")
  public ResponseEntity<DefaultResponse> createReview(
      @RequestBody CreateReviewRequestDto createReviewRequestDto, @AuthenticationPrincipal
  UserDetailsImpl userDetails) {
    reviewService.createReview(createReviewRequestDto, userDetails.getUser());
    DefaultResponse defaultResponse = DefaultResponse.valueOf(ResponseMessages.CREATED_SUCCESS);
    return ResponseEntity.ok().body(defaultResponse);
  }

  @GetMapping("/review/{reviewId}")
  public ResponseEntity<ReviewResponseDto> showSelectReview(@PathVariable Long reviewId
  ) {
    ReviewResponseDto reviewResponseDto = reviewService.showSelectReview(reviewId);
    HttpHeaders headers = setHttpHeaders.setHeaderTypeJson();
    return ResponseEntity.ok().headers(headers).body(reviewResponseDto);
  }

  @GetMapping("/reviews")
  public ResponseEntity<Page<ReviewResponseDto>> showMyReviews(PageDto pageDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {
    Page<ReviewResponseDto> reviewResponseDtopage = reviewService.showMyReviews(pageDto,
        userDetails.getUser());
    HttpHeaders headers = setHttpHeaders.setHeaderTypeJson();
    return ResponseEntity.ok().headers(headers).body(reviewResponseDtopage);
  }

  @PatchMapping("/reviews/update/{reviewId}")
  public ResponseEntity<DefaultResponse> updateMyReview(@PathVariable Long reviewId,
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @RequestBody UpdateReviewRequestDto updateReviewRequestDto
  ) {
    reviewService.updateMyReview(reviewId, userDetails.getUser(), updateReviewRequestDto);
    DefaultResponse defaultResponse = DefaultResponse.valueOf(ResponseMessages.SUCCESS);
    return ResponseEntity.ok().body(defaultResponse);
  }

  @DeleteMapping("/reviews/delete/{reviewId}")
  public ResponseEntity<DefaultResponse> deleteMyReview(@PathVariable Long reviewId,
      @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {
    reviewService.deleteReview(reviewId, userDetails.getUser());
    DefaultResponse defaultResponse = DefaultResponse.valueOf(ResponseMessages.DELETE_SUCCESS);
    return new ResponseEntity<>(defaultResponse, HttpStatus.NO_CONTENT);
  }

}
