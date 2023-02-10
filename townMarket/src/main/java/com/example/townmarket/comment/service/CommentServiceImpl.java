package com.example.townmarket.comment.service;

import com.example.townmarket.board.entity.Board;
import com.example.townmarket.board.repository.BoardRepository;
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
  private final BoardRepository boardRepository;


  // 댓글 생성
  @Override
  @Transactional
  public String createComment(Long boardsId, CommentRequestDto commentRequestDto, User user) {
    Board board = boardRepository.findById(boardsId).orElseThrow(
        () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
    );

    Comment comment = new Comment(user, board, commentRequestDto);

    commentRepository.save(comment);
    return "댓글 생성에 성공했습니다.";
  }

//  // 댓글 조회
//  @Override
//  @Transactional
//  public CommentResponseDto getComment(long commentId) {
//    Comment comment = commentRepository.findById(commentId)
//        .orElseThrow(() -> new IllegalArgumentException("해당 댓글 없음"));
//    return new CommentResponseDto(comment);
//  }

  // 댓글 수정
  @Override
  @Transactional
  public String updateComment(Long commentId, Long boardId, CommentRequestDto commentRequestDto,
      User user) {
    Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
        new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

    boardRepository.findById(boardId).orElseThrow(
        () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
    );

    if (user.equals(comment.getUser())) {
      comment.update(commentRequestDto.getCommentContents());
      return "댓글이 수정되었습니다.";
    } else {
      throw new IllegalArgumentException("수정이 완료되지 않았습니다.");
    }
  }


  // 댓글 삭제
  @Override
  @Transactional
  public String deleteComment(Long commentId, Long boardId, User user) {
    Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
        new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

    boardRepository.findById(boardId).orElseThrow(
        () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
    );

    if (user.equals(comment.getUser())) {
      commentRepository.deleteById(comment.getId());
      return "댓글 삭제가 완료 되었습니다.";
    } else {
      throw new IllegalArgumentException("댓글 삭제가 불가능합니다.");
    }
  }
}
