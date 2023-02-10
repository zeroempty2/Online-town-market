package com.example.townmarket.commons.responseMessageData;

import lombok.Getter;

@Getter
public class DefaultResponse {

  private int statusCode;
  private String message;

  public static DefaultResponse valueOf(ResponseMessages responseMessages) {
    return new DefaultResponse(responseMessages.getStatusCode(), responseMessages.getMessage());
  }

  public DefaultResponse(int statusCode, String message) {
    this.statusCode = statusCode;
    this.message = message;
  }
}
