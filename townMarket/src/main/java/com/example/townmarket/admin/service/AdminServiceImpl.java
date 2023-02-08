package com.example.townmarket.admin.service;

import com.example.townmarket.admin.dto.PagingUserResponse;
import com.example.townmarket.admin.repository.AdminRepository;
import com.example.townmarket.commons.dto.PageDto;
import com.example.townmarket.user.entity.User;
import com.example.townmarket.user.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
  private final AdminRepository adminRepository;
  private final UserService userService;

  public Page<PagingUserResponse> viewAllUser(PageDto pageDto) {
    List<User> users = userService.findAllUser();
    Pageable pageable = pageDto.toPageable();
    return new PageImpl<>(users.stream().map(PagingUserResponse::new).collect(Collectors.toList()),
        pageable, pageDto.getSize());
  }
}
