package com.example.townmarket.board.dto;

import com.example.townmarket.board.entity.Board;
import com.example.townmarket.board.entity.Board.BoardSubject;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardResponseDto {

  private String title;

  private String content;

  private BoardSubject subject;

//  private enum subject {
//    공지사항, 동네사항
//  }

  public BoardResponseDto(Board board) {
    this.title = board.getTitle();
    this.content = board.getContent();
    this.subject = board.getSubject();

  }
}
