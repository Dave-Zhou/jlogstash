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
package com.tansun.jlogstash;

import org.apache.commons.cli.CommandLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tansun.jlogstash.assembly.AssemblyPipeline;
import com.tansun.jlogstash.assembly.CmdLineParams;
import com.tansun.jlogstash.exception.ExceptionUtil;
import com.tansun.jlogstash.log.LogComponent;
import com.tansun.jlogstash.log.LogbackComponent;

/**
 * 
 * Reason: TODO ADD REASON(可选)
 * Date: 2016年8月31日 下午1:24:26
 * Company: www.dtstack.com
 * @author sishu.yss
 *
 */
public class Main {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	
	private static AssemblyPipeline assemblyPipeline = new AssemblyPipeline();
	
	private static LogComponent logbackComponent = new LogbackComponent();
	
	public static void main(String[] args) {
		try {
			CommandLine cmdLine = OptionsProcessor.parseArg(args);
			CmdLineParams.setLine(cmdLine);
			//logger config
            logbackComponent.setupLogger();
            //assembly pipeline
            assemblyPipeline.assemblyPipeline();
		} catch (Exception e) {
			logger.error("jlogstash start error:{}",ExceptionUtil.getErrorMessage(e));
			System.exit(-1);
		}
	}
}