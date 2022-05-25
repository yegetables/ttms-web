package com.xupt.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class User {
  private Integer id;
  private String username;
  private String password;
  private String email;
  private String phoneNum;
}
