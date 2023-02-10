package com.example.townmarket.product.entity;

import com.example.townmarket.product.dto.ProductRequestDto;
import com.example.townmarket.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Product {

  /**
   * 컬럼 - 연관관계 컬럼을 제외한 컬럼을 정의합니다.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private long id;

  private long userId;

  private String productName;

  private long productPrice;

  @Enumerated(EnumType.STRING)
  private ProductEnum productEnum;

  @Enumerated(EnumType.STRING)
  private ProductStatus productStatus;

  @Enumerated(EnumType.STRING)
  private ProductCategory productCategory;

  public enum ProductEnum {
    나눔, 나눔_완료, 판매_중, 예약, 판매완료
  }

  public enum ProductStatus {
    S, A, B, C, D
  }

  public enum ProductCategory {
    IT, WEAR, FOOD, GAME, CAR, TICKET
  }

  /**
   * 생성자 - 약속된 형태로만 생성가능하도록 합니다.
   */
  @Builder
  public Product(String productName, Long productPrice, ProductStatus productStatus,
      ProductCategory productCategory, ProductEnum productEnum, long userId) {
    this.productName = productName;
    this.productPrice = productPrice;
    this.productStatus = productStatus;
    this.productEnum = productEnum;
    this.productCategory = productCategory;
    this.userId = userId;
  }

  @Builder
  public Product(User user) {
    this.user = user;
  }

  /**
   * 연관관계 - Foreign Key 값을 따로 컬럼으로 정의하지 않고 연관 관계로 정의합니다.
   */
  @ManyToOne
  @JoinColumn(name = "users_id")
  private User user;

  /**
   * 연관관계 편의 메소드 - 반대쪽에는 연관관계 편의 메소드가 없도록 주의합니다.
   */

  /**
   * 서비스 메소드 - 외부에서 엔티티를 수정할 메소드를 정의합니다. (단일 책임을 가지도록 주의합니다.)
   */

  public void update(ProductRequestDto productRequestDto) {
    this.productName = productRequestDto.getProductName();
    this.productPrice = productRequestDto.getProductPrice();
    this.productStatus = productRequestDto.getProductStatus();
    this.productEnum = productRequestDto.getProductEnum();
    this.productCategory = productRequestDto.getProductCategory();
  }
}
