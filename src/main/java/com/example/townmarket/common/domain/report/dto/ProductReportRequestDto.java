package com.example.townmarket.common.domain.report.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductReportRequestDto {

  private String reason;
  private Long productId;

}
