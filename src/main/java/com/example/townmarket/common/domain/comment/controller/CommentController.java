package com.example.townmarket.common.domain.comment.controller;

import com.example.townmarket.common.domain.comment.dto.CommentRequestDto;
import com.example.townmarket.common.domain.comment.service.CommentService;
import com.example.townmarket.common.responseMessageData.DefaultResponse;
import com.example.townmarket.common.enums.ResponseMessages;
import com.example.townmarket.common.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/comments")
@RestController
public class CommentController {

  private final CommentService commentService;

  // 댓글 생성
  @PostMapping("/board/{boardsId}")
  public ResponseEntity<DefaultResponse> createComment(@PathVariable Long boardsId,
      @RequestBody CommentRequestDto commentRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    commentService.createComment(boardsId, commentRequestDto, userDetails.getUser());
    return ResponseEntity.status(HttpStatus.CREATED).body(DefaultResponse.valueOf(ResponseMessages.CREATED_SUCCESS));
  }

  // 댓글 수정
  @PutMapping("/{commentId}")
  public ResponseEntity<DefaultResponse> updateComment(@PathVariable Long commentId,
      @RequestBody CommentRequestDto commentRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    commentService.updateComment(commentId, commentRequestDto, userDetails.getUser());
    return ResponseEntity.ok().body(DefaultResponse.valueOf(ResponseMessages.SUCCESS));
  }

  // 댓글 삭제
  @DeleteMapping("/{commentId}")
  public ResponseEntity<DefaultResponse> deleteComment(@PathVariable Long commentId,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    commentService.deleteComment(commentId, userDetails.getUser());
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(DefaultResponse.valueOf(ResponseMessages.DELETE_SUCCESS));
  }
}









