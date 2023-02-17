package com.example.townmarket.common.domain.email.controller;


import com.example.townmarket.common.domain.email.service.EmailService;
import com.example.townmarket.common.domain.user.controller.UserController;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
public class EmailController {

  private final EmailService emailService;

  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  @GetMapping("/mail")
  public ModelAndView emailPage() {
    return new ModelAndView("email");
  }

  @PostMapping("/mail")
  public String emailConfirm(@RequestParam String email) throws Exception {
    logger.info("post emailConfirm");

    return emailService.sendSimpleMessage(email);
  }

  @PostMapping("/verify")
  public Boolean verifyCode(String code) {
    logger.info("Post verifyCode");
    return emailService.verifyCode(code);
  }
}
