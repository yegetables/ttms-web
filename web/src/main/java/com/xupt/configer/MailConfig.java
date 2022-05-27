package com.xupt.configer;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@EnableAutoConfiguration
public class MailConfig {
  @Value("${mail.host}")
  private String host;

  @Value("${mail.password}")
  private String password;

  @Value("${mail.defaultEncoding}")
  private String defaultEncoding;

  @Value("${mail.username}")
  private String username;

  @Value("${mail.port}")
  private String port;

  @Value("${mail.subject}")
  private String subject;

  @Bean(name = "mailSender")
  public JavaMailSenderImpl getMailSender() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost(host);
    mailSender.setUsername(username);
    mailSender.setPassword(password);
    mailSender.setDefaultEncoding(defaultEncoding);
    mailSender.setJavaMailProperties(getMailProperties());
    return mailSender;
  }

  private Properties getMailProperties() {
    Properties properties = new Properties();
    properties.setProperty("mail.smtp.auth", "true");
    properties.setProperty("mail.smtp.ssl.enable", "true");
    properties.setProperty("mail.smtp.port", port);
    return properties;
  }

  @Bean(name = "mailMessage")
  public SimpleMailMessage getMailMessage() {
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setFrom(username);
    mailMessage.setSubject(subject);
    return mailMessage;
  }
}
