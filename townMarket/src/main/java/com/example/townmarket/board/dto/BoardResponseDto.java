package com.example.townmarket.board.dto;

import com.example.townmarket.board.entity.Board;
import javax.security.auth.Subject;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardResponseDto {

  private String title;

  private String content;

  private Subject subject;

//  private enum subject {
//    공지사항, 동네사항
//  }

  public BoardResponseDto(Board board) {
    this.title = board.getTitle();
    this.content = board.getContent();
    this.subject = board.getSubject();

  }
}
