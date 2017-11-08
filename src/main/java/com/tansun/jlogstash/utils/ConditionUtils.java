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
    //通过type判断是否需要执行type
    if(condition.contains("==")) {
      String[] arr = condition.split("==");
      String key = arr[0].trim().substring(1,arr[0].length()-2);
      Object val = event.get(key);
      if(arr[1].trim().equals(val + "")) {
        return true;
      }
    } else if (condition.contains("exist")) {
      String key = condition.trim().substring(1,condition.trim().indexOf("exist") - 2);
      return event.containsKey(key);
    }
    return false;
  }
}
