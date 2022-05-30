package com.xupt.utils;

import com.xupt.pojo.Users;

public class UsersUtils {
  public static Users removeSecret(Users user) {
    if (user != null) {
      user.setPassword(null);
    }
    return user;
  }
}
