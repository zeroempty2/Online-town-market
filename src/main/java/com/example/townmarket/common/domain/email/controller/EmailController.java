package com.example.townmarket.common.domain.email.controller;


import com.example.townmarket.common.domain.email.service.EmailService;
import com.example.townmarket.common.domain.email.service.EmailServiceImpl;
import com.example.townmarket.common.domain.user.controller.UserController;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class EmailController {

  private final EmailService emailService;

  private static final Logger logger = LoggerFactory.getLogger(UserController.class);


  @PostMapping("/mail")
  @ResponseBody
  public String emailConfirm(@RequestParam String email) throws Exception {
    logger.info("post emailConfirm");

    return emailService.sendSimpleMessage(email);
  }

  @PostMapping("/verify")
  @ResponseBody
  public Boolean verifyCode(String code) {
    logger.info("Post verifyCode");

    boolean result = false;
    System.out.println("code : " + code);
    System.out.println("code match : " + EmailServiceImpl.ePw.equals(code));
    if (EmailServiceImpl.ePw.equals(code)) {
      result = true;
    }
    return result;
  }
}
