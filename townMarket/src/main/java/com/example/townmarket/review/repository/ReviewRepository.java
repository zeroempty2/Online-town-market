package com.example.townmarket.review.repository;

import com.example.townmarket.review.domain.Review;
import com.example.townmarket.review.dto.ReviewResponseDto;
import com.example.townmarket.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Long> {

  int countByRevieweeId(Long revieweeId);

  boolean existsReviewByProductId(Long productId);

  @Query(
      "select new com.example.townmarket.review.dto.ReviewResponseDto(r.grade,r.review,r.reviewer.profile,r.reviewee.profile,p.productName) "
          + "from Review r join fetch User u "
          + "left join Product p on r.productId = p.id "
          + "where r.id = :reviewId")
  ReviewResponseDto findProfileAndReviewByReviewId(Long reviewId);

  @Query(
      "select new com.example.townmarket.review.dto.ReviewResponseDto(r.grade,r.review,r.reviewer.profile,r.reviewee.profile,p.productName) "
          + "from Review r join fetch User u "
          + "left join Product p on r.productId = p.id "
          + "where r.reviewer = :user")
  Page<ReviewResponseDto> findProfileAndReviewByReviewIdPaging(Pageable pageable, User user);

}
