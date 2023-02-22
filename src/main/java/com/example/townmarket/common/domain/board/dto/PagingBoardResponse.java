package com.example.townmarket.common.domain.board.dto;

import com.example.townmarket.common.domain.board.entity.Board;
import com.example.townmarket.common.domain.board.entity.Board.BoardSubject;
import lombok.Builder;
import lombok.Getter;


@Getter
public class PagingBoardResponse {

  private String title;
  private BoardSubject subject;

  @Builder
  public PagingBoardResponse(Board board) {
    this.title = board.getTitle();
    this.subject = board.getSubject();
  }
}
