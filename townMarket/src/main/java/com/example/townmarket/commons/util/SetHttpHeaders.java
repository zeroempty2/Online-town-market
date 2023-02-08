package com.example.townmarket.commons.util;

import java.nio.charset.StandardCharsets;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class SetHttpHeaders {

  public HttpHeaders setHeaderTypeJson() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return headers;
  }

}
