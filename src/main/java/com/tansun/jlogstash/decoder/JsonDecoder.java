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
package com.tansun.jlogstash.decoder;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 
 * Reason: TODO ADD REASON(可选)
 * Date: 2016年8月31日 下午1:25:56
 * Company: www.dtstack.com
 * @author sishu.yss
 *
 */
public class JsonDecoder implements IDecode {
    private static Logger logger = LoggerFactory.getLogger(JsonDecoder.class);

	private static ObjectMapper objectMapper = new ObjectMapper();

    @SuppressWarnings({ "unchecked", "serial" })
    @Override
    public Map<String, Object> decode(final String message) {
        Map<String, Object> event = null;
        try {
            event = objectMapper.readValue(message, Map.class);
            if(!event.containsKey("message")){
            	event.put("message", message);
            } 
        } catch (Exception e) {
            logger.error(e.getMessage());
            event = new HashMap<String, Object>() {
                {
                    put("message", message);
                }
            };
            return event;
        }
        return event;
    }

	@Override
	public Map<String, Object> decode(String message, String identify) {
		return null;
	}
}