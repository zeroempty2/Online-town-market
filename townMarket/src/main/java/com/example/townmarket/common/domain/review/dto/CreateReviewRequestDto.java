package com.example.townmarket.common.domain.review.dto;

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
