package com.example.townmarket.admin.controller;

import com.example.townmarket.admin.dto.PagingUserResponse;
import com.example.townmarket.admin.service.AdminServiceImpl;
import com.example.townmarket.commons.dto.PageDto;
import com.example.townmarket.commons.util.SetHttpHeaders;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

  private final AdminServiceImpl adminService;
  private final SetHttpHeaders httpHeaders;

  @GetMapping("/users")
  public ResponseEntity<Page<PagingUserResponse>> viewAllUser(PageDto pageDto) {
    Page<PagingUserResponse> pagingUser = adminService.viewAllUser(pageDto);
    HttpHeaders headers = httpHeaders.setHeaderTypeJson();
    return ResponseEntity.ok().headers(headers).body(pagingUser);
  }

}
