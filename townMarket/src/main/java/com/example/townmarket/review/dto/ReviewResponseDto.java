package com.example.townmarket.review.dto;

import com.example.townmarket.user.entity.Profile;
import lombok.Builder;


@Builder
public class ReviewResponseDto {

  private int grade;
  private String review;
  private Profile reviewerProfile;
  private Profile revieweeProfile;
  private String productName;


  public ReviewResponseDto(int grade, String review, Profile reviewerProfile,
      Profile revieweeProfile, String productName) {
    this.grade = grade;
    this.review = review;
    this.reviewerProfile = reviewerProfile;
    this.revieweeProfile = revieweeProfile;
    this.productName = productName;
  }
}
