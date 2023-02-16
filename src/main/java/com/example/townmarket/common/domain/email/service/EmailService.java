package com.example.townmarket.common.domain.email.service;

public interface EmailService {

  String sendSimpleMessage(String to) throws Exception;
  Boolean verifyCode(String code);
}
