package com.tansun.jlogstash;

import com.tansun.jlogstash.utils.ConditionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dave on 2017/11/5.
 *
 * @version 1.0 2017/11/5
 * @autor zxd
 */
public class TestConditionUtils {

  public static void main(String[] args) {
    Map<Object,Object> map = new HashMap<>();
    map.put("type","testType");
    map.put("name","xxx");
    String condition = "[name] == xx";
    System.out.println(ConditionUtils.isTrue(map,condition));
  }
}
