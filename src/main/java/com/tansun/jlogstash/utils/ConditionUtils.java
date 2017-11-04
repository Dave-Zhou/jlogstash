package com.tansun.jlogstash.utils;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Dave on 2017/11/5.
 *
 * @version 1.0 2017/11/5
 */
public class ConditionUtils {
  public static boolean isTrue(Map<Object,Object> event, String condition) {
    //通过type判断是否需要执行
    if(!StringUtils.isEmpty(condition)) {
      String type = (String)event.get("type");
      if(condition.contains("==")) {
        String[] arr = condition.split("==");
        if(arr[1].trim().equals(type)) {
          return true;
        }
      }
    }
    return false;
  }
}
