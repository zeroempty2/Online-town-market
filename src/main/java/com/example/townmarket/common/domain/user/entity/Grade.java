package com.example.townmarket.common.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class Grade {

  /**
   * 컬럼 - 연관관계 컬럼을 제외한 컬럼을 정의합니다.
   */
  @Column
  private double averageGrade;
  @Column
  private int count;
  @Column
  private int totalGrade;

  /**
   * 생성자 - 약속된 형태로만 생성가능하도록 합니다.
   */

  /**
   * 연관관계 - Foreign Key 값을 따로 컬럼으로 정의하지 않고 연관 관계로 정의합니다.
   */

  /**
   * 연관관계 편의 메소드 - 반대쪽에는 연관관계 편의 메소드가 없도록 주의합니다.
   */


  public Grade() {
    this.averageGrade = 0;
    this.count = 0;
    this.totalGrade = 0;
  }

  /**
   * 서비스 메소드 - 외부에서 엔티티를 수정할 메소드를 정의합니다. (단일 책임을 가지도록 주의합니다.)
   */

  public void setUserGrade(int grade, int count) {
    this.totalGrade += grade;
    this.count += count;
    this.averageGrade = Math.abs(this.totalGrade / this.count);
  }

  public void updateUserGrade(int grade) {
    this.totalGrade -= grade;
    this.averageGrade = Math.abs(this.totalGrade / this.count);
  }

}
