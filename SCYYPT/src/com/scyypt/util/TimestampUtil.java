package com.scyypt.util;

import java.util.Date;

import org.apache.log4j.Logger;

 

/**
 * 
 * @ClassName: TimestampUtil
 * @Description: 用于向微服务获取 时间戳数据
 * @author wangdekun
 * @date 2017年8月21日 下午5:09:15
 *
 */
public class TimestampUtil {
	private static Logger logger = Logger.getLogger(TimestampUtil.class.getName());

	public static String getTs() {
 
		Date date = new Date();
		
		long unixTimestamp = date.getTime() / 1000;
		
		logger.info(unixTimestamp);
		
		String timestamp = String.valueOf(unixTimestamp);
		
		return timestamp;
	}

}

