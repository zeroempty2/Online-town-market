package com.example.townmarket.comment.controller;

import com.example.townmarket.comment.dto.CommentRequestDto;
import com.example.townmarket.comment.service.CommentService;
import com.example.townmarket.commons.responseMessageData.DefaultResponse;
import com.example.townmarket.commons.responseMessageData.ResponseMessages;
import com.example.townmarket.commons.security.UserDetailsImpl;
import jakarta.validation.Valid;
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
      @Valid @RequestBody CommentRequestDto commentRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    commentService.createComment(boardsId, commentRequestDto, userDetails.getUser());
    DefaultResponse defaultResponse = DefaultResponse.valueOf(ResponseMessages.CREATED_SUCCESS);
    return ResponseEntity.status(HttpStatus.CREATED).body(defaultResponse);
  }

  // 댓글 수정
  @PutMapping("/{commentId}/board/{boardsId}")
  public ResponseEntity<DefaultResponse> updateComment(@PathVariable Long commentId,
      @PathVariable Long boardsId,
      @RequestBody CommentRequestDto commentRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    commentService.updateComment(commentId, boardsId, commentRequestDto,
        userDetails.getUser());
    DefaultResponse defaultResponse = DefaultResponse.valueOf(ResponseMessages.SUCCESS);
    return ResponseEntity.ok().body(defaultResponse);
  }

  // 댓글 삭제
  @DeleteMapping("/{commentId}/board/{boardsId}")
  public ResponseEntity<DefaultResponse> deleteComment(@PathVariable Long commentId,
      @PathVariable Long boardsId,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    commentService.deleteComment(commentId, boardsId, userDetails.getUser());
    DefaultResponse defaultResponse = DefaultResponse.valueOf(ResponseMessages.DELETE_SUCCESS);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(defaultResponse);
  }

}









