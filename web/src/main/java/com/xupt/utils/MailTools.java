package com.xupt.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class MailTools {

  private final JavaMailSender mailSender;
  private final SimpleMailMessage mailMessage;

  public MailTools(@Autowired JavaMailSender mailSender, @Autowired SimpleMailMessage mailMessage) {
    this.mailSender = mailSender;
    this.mailMessage = mailMessage;
  }

  public void sendSimpleMail(String to, String subject, String content) {
    mailMessage.setSubject(subject);
    sendSimpleMail(to, content);
  }

  public void sendSimpleMail(String to, String content) {
    mailMessage.setTo(to);
    mailMessage.setText(content);
    try {
      mailSender.send(mailMessage);
    } catch (Exception e) {
      log.error("发送邮件{" + mailMessage + "}失败！" + e.getLocalizedMessage());
    }
  }
}
