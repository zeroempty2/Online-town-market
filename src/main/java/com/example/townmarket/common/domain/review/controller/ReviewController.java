package com.example.townmarket.common.domain.review.controller;

import com.example.townmarket.common.domain.review.dto.CreateReviewRequestDto;
import com.example.townmarket.common.domain.review.dto.ReviewResponseDto;
import com.example.townmarket.common.domain.review.dto.UpdateReviewRequestDto;
import com.example.townmarket.common.domain.review.service.ReviewServiceImpl;
import com.example.townmarket.common.dto.PageDto;
import com.example.townmarket.common.dto.StatusResponse;
import com.example.townmarket.common.enums.ResponseMessages;
import com.example.townmarket.common.security.UserDetailsImpl;
import com.example.townmarket.common.util.SetHttpHeaders;
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
  public ResponseEntity<StatusResponse> createReview(
      @RequestBody CreateReviewRequestDto createReviewRequestDto, @AuthenticationPrincipal
  UserDetailsImpl userDetails) {
    reviewService.createReview(createReviewRequestDto, userDetails.getUser());
    return ResponseEntity.status(HttpStatus.CREATED).body(StatusResponse.valueOf(ResponseMessages.CREATED_SUCCESS));
  }

  @GetMapping("/review/{reviewId}")
  public ResponseEntity<ReviewResponseDto> showSelectReview(@PathVariable Long reviewId
  ) {
    return ResponseEntity.ok().headers(setHttpHeaders.setHeaderTypeJson()).body(reviewService.showSelectReview(reviewId));
  }

  @GetMapping("/reviews")
  public ResponseEntity<Page<ReviewResponseDto>> showMyReviews(PageDto pageDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {
    return ResponseEntity.ok().headers(setHttpHeaders.setHeaderTypeJson()).body(reviewService.showMyReviews(pageDto,
        userDetails.getUser()));
  }

  @PatchMapping("/reviews/update/{reviewId}")
  public ResponseEntity<StatusResponse> updateMyReview(@PathVariable Long reviewId,
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @RequestBody UpdateReviewRequestDto updateReviewRequestDto
  ) {
    reviewService.updateMyReview(reviewId, userDetails.getUser(), updateReviewRequestDto);
    return ResponseEntity.ok().body(StatusResponse.valueOf(ResponseMessages.SUCCESS));
  }

  @DeleteMapping("/reviews/delete/{reviewId}")
  public ResponseEntity<StatusResponse> deleteMyReview(@PathVariable Long reviewId,
      @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {
    reviewService.deleteReview(reviewId, userDetails.getUser());
    StatusResponse statusResponse = StatusResponse.valueOf(ResponseMessages.DELETE_SUCCESS);
    return new ResponseEntity<>(statusResponse, HttpStatus.NO_CONTENT);
  }

}
