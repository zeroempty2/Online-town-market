package com.example.townmarket.common.domain.report.dto;

import com.example.townmarket.common.domain.report.entity.ProductReport.ReportEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductReportRequestDto {

  private ReportEnum reportEnum;
  private String reason;
  private Long productId;

}
