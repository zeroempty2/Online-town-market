package com.example.townmarket.board.entity;

import com.example.townmarket.board.dto.BoardRequestDto;
import com.example.townmarket.comment.entity.Comment;
import com.example.townmarket.commons.TimeStamped;
import com.example.townmarket.user.entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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


  @Enumerated(EnumType.STRING)
  private BoardSubject subject;

  public enum BoardSubject {
    공지사항, 동네소식;
  }

  /**
   * 생성자 - 약속된 형태로만 생성가능하도록 합니다.
   */
  @Builder
  public Board(String title, String content, BoardSubject subject, User user) {
    this.title = title;
    this.content = content;
    this.subject = subject;
    this.user = user;
  }

  /**
   * 연관관계 - Foreign Key 값을 따로 컬럼으로 정의하지 않고 연관 관계로 정의합니다.
   */
  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Comment> comment = new LinkedHashSet<>();

  /**
   * 연관관계 편의 메소드 - 반대쪽에는 연관관계 편의 메소드가 없도록 주의합니다.
   */

  /**
   * 서비스 메소드 - 외부에서 엔티티를 수정할 메소드를 정의합니다. (단일 책임을 가지도록 주의합니다.)
   */
  public void update(BoardRequestDto boardRequestDto) {
    this.title = boardRequestDto.getTitle();
    this.content = boardRequestDto.getContent();
    this.subject = boardRequestDto.getSubject();
  }

  public boolean checkBoardWriter(User user) {
    return this.user.equals(user); // board가 가지고 있는 유저와 파라미터로 받은 유저를 비교해서 true false반환
  }

}
