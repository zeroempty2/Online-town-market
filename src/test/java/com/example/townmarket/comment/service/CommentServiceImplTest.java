package com.example.townmarket.comment.service;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.townmarket.common.domain.board.entity.Board;
import com.example.townmarket.common.domain.board.repository.BoardRepository;
import com.example.townmarket.common.domain.board.service.BoardService;
import com.example.townmarket.common.domain.comment.dto.CommentRequestDto;
import com.example.townmarket.common.domain.comment.entity.Comment;
import com.example.townmarket.common.domain.comment.repository.CommentRepository;
import com.example.townmarket.common.domain.comment.service.CommentServiceImpl;
import com.example.townmarket.common.domain.user.entity.User;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

  @Mock
  CommentRepository commentRepository;

  @Mock
  BoardService boardService;

  @InjectMocks
  CommentServiceImpl commentService;

  @Test
  @DisplayName("댓글 생성 성공")
  void createComment() {
    // given
    CommentRequestDto commentRequestDto = mock(CommentRequestDto.class);
    Board board = mock(Board.class);
    User user = mock(User.class);

    when(boardService.findBoardById(board.getId())).thenReturn(board);
//    when(boardService.findById(board.getId()).thenReturn(Optional.of(board);

    // when
    commentService.createComment(board.getId(), commentRequestDto, user);

    // then
    verify(commentRepository).save(isA(Comment.class));
  }

  @Test
  @DisplayName("댓글 수정 성공")
  void updateComment() {
    // given
    User user = mock(User.class);
    Board board = mock(Board.class);
    Comment comment = mock(Comment.class);
    CommentRequestDto commentRequestDto = mock(CommentRequestDto.class);

//    when(boardRepository.findById(board.getId())).thenReturn(Optional.of(board));
//    when(boardService.findById(board.getId()).thenReturn(Optional.of(board);
    when(commentRepository.findById(comment.getId())).thenReturn(Optional.of(comment));
    when(Optional.of(comment).get().checkCommentWriter(user)).thenReturn(true);

    // when
    commentService.updateComment(comment.getId(), commentRequestDto, user);

    // then
    verify(comment).update(commentRequestDto);

  }

  @Test
  @DisplayName("댓글 삭제 성공")
  void deleteComment() {
    // given
    User user = mock(User.class);
    Board board = mock(Board.class);
    Comment comment = mock(Comment.class);

//    when(boardRepository.findById(board.getId())).thenReturn(Optional.of(board));
//    when(boardService.findById(board.getId()).thenReturn(Optional.of(board);
    when(commentRepository.findById(comment.getId())).thenReturn(Optional.of(comment));
    when(Optional.of(comment).get().checkCommentWriter(user)).thenReturn(true);

    // when
    commentService.deleteComment(comment.getId(), user);

    // then
    verify(commentRepository).deleteById(anyLong());

  }
}
