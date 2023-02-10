package com.example.townmarket.user.entity;

import com.example.townmarket.product.entity.Product;
import com.example.townmarket.user.dto.UserUpdateRequestDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Objects;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//lombok
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder

//jpa
@Entity
@Table(name = "users")
//@DynamicInsert
public class User {

  /**
   * 컬럼 - 연관관계 컬럼을 제외한 컬럼을 정의합니다.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column
  private Long kakaoId;

  @Column(length = 25, nullable = false, unique = true)
  private String username;

  @Column( nullable = false, unique = true)
  private String password;

  @Column(nullable = false)
  private String phoneNumber;

  @Column(nullable = false)
  private String email;
  @Column(nullable = false)
  private String region;


  @Embedded
  private Profile profile;

  /**
   * 생성자 - 약속된 형태로만 생성가능하도록 합니다.
   */

  @Builder
  public User(String username, String password, String phoneNumber, String region) {
    this.username = username;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.region = region;
  }

  @Builder
  public User(String username, String password, Profile profile) {
    this.username = username;
    this.password = password;
    this.profile = profile;
  }



  /**
   * 연관관계 - Foreign Key 값을 따로 컬럼으로 정의하지 않고 연관 관계로 정의합니다.
   */
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private Set<Product> products;

  /**
   * 연관관계 편의 메소드 - 반대쪽에는 연관관계 편의 메소드가 없도록 주의합니다.
   */

  /**
   * 서비스 메소드 - 외부에서 엔티티를 수정할 메소드를 정의합니다. (단일 책임을 가지도록 주의합니다.)
   */

  public boolean checkAuthorization(User user) {
    return Objects.equals(this.id, user.getId());
  }

  public void update(UserUpdateRequestDto updateDto) {
    this.password = updateDto.getPassword();
  }

  public User kakaoIdUpdate(Long kakaoId) {
    this.kakaoId = kakaoId;
    return this;
  }
}
