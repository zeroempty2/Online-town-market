package com.example.townmarket.board.entity;

import com.example.townmarket.comment.entity.Comment;
import com.example.townmarket.commons.entity.TimeStamped;
import com.example.townmarket.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import javax.security.auth.Subject;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Board extends TimeStamped {

  /**
   * 컬럼 - 연관관계 컬럼을 제외한 컬럼을 정의합니다.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  private String content;

  private long userId;

  @Enumerated(EnumType.STRING)
  private Subject subject;

  private enum subject {
    공지사항, 동네사항
  }

  /**
   * 생성자 - 약속된 형태로만 생성가능하도록 합니다.
   */
  public Board(String title, String content, Subject subject, User user) {
    this.title = title;
    this.content = content;
    this.subject = subject;
    this.userId = user.getId();
  }

  /**
   * 연관관계 - Foreign Key 값을 따로 컬럼으로 정의하지 않고 연관 관계로 정의합니다.
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @OneToMany
//  @OrderBy("commentId asc") // 댓글 정렬
  private List<Comment> comment = new ArrayList<>();

  /**
   * 연관관계 편의 메소드 - 반대쪽에는 연관관계 편의 메소드가 없도록 주의합니다.
   */

  /**
   * 서비스 메소드 - 외부에서 엔티티를 수정할 메소드를 정의합니다. (단일 책임을 가지도록 주의합니다.)
   */
  public void update(String title, String content, Subject subject) {
    this.title = title;
    this.content = content;
    this.subject = subject;
  }


}
