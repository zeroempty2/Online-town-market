package com.example.townmarket.board.dto;


import com.example.townmarket.board.entity.Board.BoardSubject;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
public class BoardRequestDto {

  private String title;
  private String content;
  private BoardSubject subject;

  public BoardRequestDto(String title, String content, BoardSubject subject) {
    this.title = title;
    this.content = content;
    this.subject = subject;
  }
}
