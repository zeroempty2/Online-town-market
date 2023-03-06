package com.example.townmarket.common.domain.address.entity;

import com.example.townmarket.common.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(nullable = false)
  private String address;
  @Column(nullable = false)
  private String address2;
  @Column(nullable = false)
  private String address3;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Address(String regionName1, String regionName2, String regionName3, User user) {
    this.address = regionName1;
    this.address2 = regionName2;
    this.address3 = regionName3;
    this.user = user;
  }

  public void updateAddress(String regionName1, String regionName2, String regionName3) {
    this.address = regionName1;
    this.address2 = regionName2;
    this.address3 = regionName3;
  }
}

