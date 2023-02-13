package com.example.townmarket.commons.dto;

import java.util.Objects;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
@RequiredArgsConstructor
public class PageDto {

  int page;

  int size;

  String sortBy;

  boolean isAsc;

  public Pageable toPageable() {
    if (Objects.isNull(sortBy)) {
      return PageRequest.of(page - 1, size,
          Sort.by("createdAt").descending()); //sortBy 입력안하면 작성일자 기준으로 내림차순
    } else {
      return PageRequest.of(page - 1, size, Sort.by(sortBy).descending());
    }
  }

}
