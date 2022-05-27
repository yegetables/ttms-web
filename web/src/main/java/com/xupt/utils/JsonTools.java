package com.xupt.utils;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class JsonTools {

  //    private final Gson json;

  //    public JsonTools(Gson gson) {this.json = gson;}

  public static boolean isBadJson(String json) {
    return !isGoodJson(json);
  }

  public static boolean isGoodJson(String json)F {
    if (StringUtils.isBlank(json)) return false;
    json = json.trim();
    if (json.startsWith("{") && json.endsWith("}")) {
      try {
        return JSON.toJSON(json) != null;
      } catch (Exception e) {
        return false;
      }
    }
    return false;
  }

  public static boolean isJson(String json) {
    return isGoodJson(json);
  }

  public Map JsonToMap(String json) {
    if (isBadJson(json)) {
      return new HashMap();
    }
    try {
      return JSON.parseObject(json, Map.class);
    } catch (Exception e) {
      log.error("json转换为map失败：" + json);
    }
    return new HashMap();
  }
}
