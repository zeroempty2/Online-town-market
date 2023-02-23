package com.example.townmarket.common.domain.interest.controller;

import static com.example.townmarket.common.domain.interest.controller.InterestController.INTEREST_URI_API;
import static com.example.townmarket.common.util.HttpResponseEntity.RESPONSE_DELETE;
import static com.example.townmarket.common.util.HttpResponseEntity.RESPONSE_OK;

import com.example.townmarket.common.domain.interest.dto.InterestPagingResponseDto;
import com.example.townmarket.common.domain.interest.dto.InterestRequestDto;
import com.example.townmarket.common.domain.interest.service.InterestService;
import com.example.townmarket.common.dto.StatusResponse;
import com.example.townmarket.common.security.UserDetailsImpl;
import com.example.townmarket.common.util.SetHttpHeaders;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping(INTEREST_URI_API)
public class InterestController {

  static final String INTEREST_URI_API = "/interest";
  private final InterestService interestService;
  private final SetHttpHeaders httpHeaders;

  @PostMapping
  public ResponseEntity<StatusResponse> addInterest(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @RequestBody InterestRequestDto interestRequestDto) {
    interestService.addInterest(userDetails.getUser(), interestRequestDto);
    return RESPONSE_OK;
  }

  @DeleteMapping("/{interestId}")
  public ResponseEntity<StatusResponse> deleteInterest(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @PathVariable Long interestId) {
    interestService.deleteInterest(userDetails.getUserId(), interestId);
    return RESPONSE_DELETE;
  }

  @GetMapping("/list")
  @ResponseBody
  public ResponseEntity<Page<InterestPagingResponseDto>> showMyInterestProducts(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @PageableDefault(size = 9, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
    return ResponseEntity.ok()
        .headers(httpHeaders.setHeaderTypeJson())
        .body(interestService.showMyInterestProducts(userDetails.getUser(), pageable));
  }

}
