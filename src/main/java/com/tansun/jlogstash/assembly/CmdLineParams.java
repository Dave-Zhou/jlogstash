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
package com.tansun.jlogstash.assembly;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tansun.jlogstash.monitor.MonitorInfo;
import com.tansun.jlogstash.monitor.MonitorService;
import com.tansun.jlogstash.property.SystemProperty;
import com.tansun.jlogstash.utils.Public;


/**
 * 
 * Reason: TODO ADD REASON(可选)
 * Date: 2016年11月30日 下午1:25:18
 * Company: www.dtstack.com
 * @author sishu.yss
 *
 */
public class CmdLineParams {
	
	private static Logger logger  = LoggerFactory.getLogger(CmdLineParams.class);

	private static MonitorInfo monitorInfo = new MonitorService().getMonitorInfoBean();
	
	private static CommandLine line;
	
	public static void setLine(CommandLine line) {
		CmdLineParams.line = line;
	}
	

	public static double getInputQueueCoefficient(){
		String number =line.getOptionValue("c");
		double coefficient =StringUtils.isNotBlank(number)?Double.parseDouble(number):SystemProperty.getInputProportion();
		logger.info("input queue coefficient:{}",String.valueOf(coefficient));
		return coefficient;
	}
	

	public static double getOutputQueueCoefficient(){
		String number =line.getOptionValue("i");
		double coefficient =StringUtils.isNotBlank(number)?Double.parseDouble(number):SystemProperty.getOutputProportion();	
		logger.info("output queue coefficient:{}",String.valueOf(coefficient));
		return coefficient;
	}
	

	public static int getFilterWork(){
		String number =line.getOptionValue("w");
        int works =StringUtils.isNotBlank(number)?Integer.parseInt(number):getInputBase();	
		logger.info("filter works:{}",String.valueOf(works));
        return works;
	}
	
	
	public static int getOutputWork(){
		String number =line.getOptionValue("o");
        int works =StringUtils.isNotBlank(number)?Integer.parseInt(number):getOutputBase();	
		logger.info("output works:{}",String.valueOf(works));
        return works;
	}
	

	public static int getInputQueueSize(){
		float number = getFilterWork();
        int size = Public.getIntValue(monitorInfo.getJvmMaxMemory()*getInputQueueCoefficient()*((float)getInputBase()/number));
		size = size<=0?10:size;
        logger.info("input queue size:{}",String.valueOf(size));
        return size;
	}
	
	private static int getInputBase(){
		int process = monitorInfo.getProcessors();
		return process + process/2;
	}
	
	private static int getOutputBase(){
		int process = monitorInfo.getProcessors();
		return process;
	}
		

	public static int getOutputQueueSize(){
		float number =getOutputWork();
		int size = Public.getIntValue(monitorInfo.getJvmMaxMemory()*getOutputQueueCoefficient()*((float)getOutputBase()/number));
		size = size<=0?10:size;
		logger.info("output queue size:{}",String.valueOf(size));
		return size;	
	}
	
	public static String getConfigFilePath(){
		return line.getOptionValue("f");
	}
	
	public static boolean isDev() {
		return line.hasOption("dev");
	}
	

	public static String getLogFilePath(){
		return line.getOptionValue("l");
	}
	
	public static boolean hasOptionTrace(){
		return line.hasOption("vvvvv");
	}
	
	public static boolean hasOptionDebug(){
		return line.hasOption("vvvv");
	}
	
	public static boolean hasOptionInfo(){
		return line.hasOption("vvv");
	}
	

	public static boolean hasOptionWarn(){
		return line.hasOption("vv");
	}
	
	public static boolean hasOptionError(){
		return line.hasOption("v");
	}
}
