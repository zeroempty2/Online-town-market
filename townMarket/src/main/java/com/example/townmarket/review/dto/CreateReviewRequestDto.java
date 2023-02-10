package com.example.townmarket.review.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateReviewRequestDto {

  private Long revieweeId;
  private String review;
  private int grade;
  private Long productId;

}
