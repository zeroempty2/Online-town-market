package com.example.townmarket.common.domain.s3.controller;

import com.example.townmarket.common.domain.s3.service.S3Service;
import com.example.townmarket.common.domain.s3.service.S3ServiceImpl;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class S3Controller {

  private final S3ServiceImpl s3Service;

  @PostMapping("/api/images/upload")
  public String imageUpload(@RequestParam("data") MultipartFile multipartFile) throws IOException {
    return s3Service.upload(multipartFile, "testfortownmarketproject", "image");
  }
}
