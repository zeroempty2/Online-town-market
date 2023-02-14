package com.example.townmarket.comment.service;

import com.example.townmarket.board.service.BoardService;
import com.example.townmarket.comment.dto.CommentRequestDto;
import com.example.townmarket.comment.entity.Comment;
import com.example.townmarket.comment.repository.CommentRepository;
import com.example.townmarket.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;

  private final BoardService boardService;


  // 댓글 생성
  @Override
  @Transactional
  public void createComment(Long boardsId, CommentRequestDto commentRequestDto, User user) {
//    Board board = boardService.findBoardById(boardsId);
//    Comment comment = Comment.builder()
//        .comment(commentRequestDto.getCommentContents())
//        .username(user.getUsername())
//        .user(user)
//        .board(board)
//        .build();
//    commentRepository.save(comment);
  }

  // 댓글 수정
  @Override
  @Transactional
  public void updateComment(Long commentId, Long boardId, CommentRequestDto commentRequestDto,
      User user) {
//    boardService.findBoardById(boardId);
    Comment comment = findCommentById(commentId);

    if (!comment.checkCommentWriter(user)) { // comment 안에 서비스 구현
      throw new IllegalArgumentException("수정이 완료되지 않았습니다.");
    }
    comment.update(commentRequestDto);
  }

  // 댓글 삭제
  @Override
  @Transactional
  public void deleteComment(Long commentId, Long boardId, User user) {
    Comment comment = findCommentById(commentId);
//    boardService.findBoardById(boardId);

    if (!comment.checkCommentWriter(user)) {
      throw new IllegalArgumentException("댓글 삭제가 불가능합니다.");
    }
    commentRepository.deleteById(comment.getId());
  }

  // 댓글 검색 메서드
  public Comment findCommentById(Long commentId) {
    return commentRepository.findById(commentId).orElseThrow(() ->
        new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
  }
}
