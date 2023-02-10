package com.example.townmarket.comment.entity;

import com.example.townmarket.board.entity.Board;
import com.example.townmarket.comment.dto.CommentRequestDto;
import com.example.townmarket.commons.TimeStamped;
import com.example.townmarket.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Comment extends TimeStamped {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "TEXT", nullable = false)
  private String comment;

  private String username;

  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  private Board board;

  public Comment(User user, Board board, CommentRequestDto commentRequestDto) {
    this.comment = commentRequestDto.getCommentContents();
    this.username = user.getUsername();
    this.user = user;
    this.board = board;
  }

  // 댓글 수정 로직
  public void update(String comment) {
    this.comment = comment;
  }
}
