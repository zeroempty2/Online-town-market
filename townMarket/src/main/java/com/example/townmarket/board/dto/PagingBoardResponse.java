package com.example.townmarket.board.dto;

import com.example.townmarket.board.entity.Board;
import com.example.townmarket.board.entity.Board.BoardSubject;

public class PagingBoardResponse {

  private String title;
  private BoardSubject subject;

  public PagingBoardResponse(Board board) {
    this.title = board.getTitle();
    this.subject = board.getSubject();
  }
}
