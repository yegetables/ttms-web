package com.xupt.interceptor;

import com.xupt.utils.RedisUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Log4j2
public class loginInterceptor implements HandlerInterceptor {
  @Autowired RedisUtils redisUtils;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    log.info("===》请求的url：" + request.getRequestURI());
    if (request.getRequestURI().contains("/login")
        || request.getRequestURI().contains("/register")
        || request.getRequestURI().contains("/static")) return true;
    final String headerToken = request.getHeader("token");
    log.info("获得token:" + headerToken);
    if (null == headerToken || headerToken.trim().equals("")) {
      response.sendRedirect("/login403");
      return false;
    }
    System.out.println("========================");
    try {
      Claims claims =
          Jwts.parser()
              .setSigningKey("cereshuzhitingnizhenbangcereshuzhitingnizhenbang")
              .parseClaimsJws(headerToken)
              .getBody();
      String tokenUserId = (String) claims.get("userId");
      log.info("解析到uid:" + tokenUserId);
      String RedisToken = redisUtils.get(tokenUserId);
      if (RedisToken == null) {
        log.info("[tokenError]该用户没有token值");
        //   request.setAttribute("uid",tokenUserId);
        response.sendRedirect("/login403");
        return false;
      }
      if (!headerToken.equals(RedisToken)) {
        log.info("[tokenError]token值不匹配");
        response.sendRedirect("/login403");
        return false;
      }
      log.info("[Success]token匹配成功");
      return true;
    } catch (Exception e) {
      System.out.println(e);
      return false;
    }
  }

  @Override
  public void postHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      ModelAndView modelAndView)
      throws Exception {}

  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {}
}
