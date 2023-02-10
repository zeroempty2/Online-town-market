package com.example.townmarket.board.dto;

import javax.security.auth.Subject;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateBoardRequestDto {
  private String title;
  private String content;

  private Subject subject;

  public CreateBoardRequestDto(String title, String content, Subject subject) {
    this.title = title;
    this.content = content;
    this.subject = subject;
  }
}
