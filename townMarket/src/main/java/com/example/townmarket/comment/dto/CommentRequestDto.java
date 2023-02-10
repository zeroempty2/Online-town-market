package com.example.townmarket.comment.dto;

import com.example.townmarket.board.entity.Board;
import com.example.townmarket.comment.entity.Comment;
import com.example.townmarket.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CommentRequestDto {
  private String commentContents;

  public CommentRequestDto(String commentContents) {
    this.commentContents = commentContents;
  }
}

