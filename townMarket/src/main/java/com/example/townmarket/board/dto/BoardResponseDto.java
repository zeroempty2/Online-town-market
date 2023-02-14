package com.example.townmarket.board.dto;

import com.example.townmarket.board.entity.Board;
import com.example.townmarket.board.entity.Board.BoardSubject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardResponseDto {

  private String title;

  private String content;

  private BoardSubject subject;

}
