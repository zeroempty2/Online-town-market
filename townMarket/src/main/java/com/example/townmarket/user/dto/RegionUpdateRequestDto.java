package com.example.townmarket.user.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegionUpdateRequestDto { // Update로 수정 부탁드립니다.

  @NotEmpty(message = "거래할 지역의 읍,면,동 까지 적어주세요.")
  private String region;
}
