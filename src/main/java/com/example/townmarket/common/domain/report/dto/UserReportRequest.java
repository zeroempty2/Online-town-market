package com.example.townmarket.common.domain.report.dto;

import com.example.townmarket.common.domain.report.entity.UserReport.ReportEnum;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserReportRequest {

  private Long reportedUserId;

  private ReportEnum reportEnum;

  private String reason;


}
