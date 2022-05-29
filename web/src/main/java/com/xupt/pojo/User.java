package com.xupt.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@TableName("users")
public class User {

  private String uid;
  private String username;
  private String password;
  private String email;
  private String phoneNum;
  private String headUrl;
  private long age;
  private String gender;

}
