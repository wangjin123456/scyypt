package com.scyypt.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

class ThreadPool {
	
	private static Logger logger = Logger.getLogger(ThreadPool.class.getName());

	static private int max = Runtime.getRuntime().availableProcessors();
	
	public ThreadPool() {

	}
	
	static public ExecutorService getPool(String name) {
		
		int size = ThreadPool.max * 10 + 1;
		
		logger.info("申请线程池对象->" + name + "根据CPU个数:" + size + "预计创建线程池大小为:" + (size));
		
		return Executors.newFixedThreadPool(size);
	}
}