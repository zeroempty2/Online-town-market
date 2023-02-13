package com.example.townmarket.comment.service;

import com.example.townmarket.comment.dto.CommentRequestDto;
import com.example.townmarket.user.entity.User;

public interface CommentService {

  void createComment(Long boardsId, CommentRequestDto commentRequestDto, User user);

  void updateComment(Long commentId, Long boardsId, CommentRequestDto commentRequestDto,
      User user);

//  CommentResponseDto getComment(long commentId);

  String deleteComment(Long commentId, Long boardsId, User user);

}
