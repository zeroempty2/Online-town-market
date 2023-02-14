package com.example.townmarket.review.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateReviewRequestDto {

  private Long revieweeId;
  private String reviewContents;
  private int grade;
  private Long productId;

}
