package com.example.townmarket.comment.entity;

import com.example.townmarket.board.entity.Board;
import com.example.townmarket.comment.dto.CommentRequestDto;
import com.example.townmarket.commons.entity.TimeStamped;
import com.example.townmarket.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

  private Long userId;

  private Long boardId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "users_id")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "board_id")
  private Board board;

  public Comment(User user, Board board, CommentRequestDto commentRequestDto) {
    this.comment = commentRequestDto.getCommentContents();
    this.username = user.getUsername();
    this.userId = user.getId();
    this.boardId = board.getId();
  }

  // 댓글 수정 로직
  public void update(String comment) {
    this.comment = comment;
  }
}