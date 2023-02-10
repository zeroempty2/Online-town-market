package com.example.townmarket.comment.service;

import com.example.townmarket.comment.dto.CommentRequestDto;
import com.example.townmarket.user.entity.User;

public interface CommentService {

  String createComment(Long boardsId, CommentRequestDto commentRequestDto, User user);

  String updateComment(Long commentId, Long boardsId, CommentRequestDto commentRequestDto,
      User user);

//  CommentResponseDto getComment(long commentId);

  String deleteComment(Long commentId, Long boardsId, User user);

}
