package com.example.townmarket.common.domain.board.dto;

import com.example.townmarket.common.domain.board.entity.Board;
import com.example.townmarket.common.domain.board.entity.Board.BoardSubject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PagingBoardResponse {

  private String title;
  private BoardSubject subject;

  public PagingBoardResponse(Board board) {
    this.title = board.getTitle();
    this.subject = board.getSubject();
  }
}
