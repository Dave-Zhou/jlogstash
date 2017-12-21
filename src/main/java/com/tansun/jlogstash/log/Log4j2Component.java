/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tansun.jlogstash.log;

import java.io.File;
import java.io.FileInputStream;

import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

/**
 * Reason: TODO ADD REASON(可选)
 * Date: 2016年8月31日 下午1:27:09
 * Company: www.dtstack.com
 *
 * @author sishu.yss
 */
public class Log4j2Component extends LogComponent {

  @Override
  public void setupLogger(){

    //这里需要注意路径中不要出现中文和空格，如果存在中文，请使用url转码
    ConfigurationSource source;

    String config=System.getProperty("user.dir");

    String path = config + File.separator + "config" + File.separator + "log4j2.xml";

    File file = new File(path);

    try {

      source = new ConfigurationSource(new FileInputStream(file));

      Configurator.initialize(Log4j2Component.class.getClassLoader(),source);

    } catch (Exception e) {

      e.printStackTrace();

    }

  }

}
