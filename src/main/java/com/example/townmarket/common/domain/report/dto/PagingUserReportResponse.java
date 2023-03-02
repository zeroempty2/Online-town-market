package com.example.townmarket.common.domain.report.dto;

import com.example.townmarket.common.domain.report.entity.UserReport.ReportEnum;
import lombok.Getter;

@Getter
public class PagingUserReportResponse {

  private String reportedName;
  private String reason;
  private ReportEnum reportEnum;
}
