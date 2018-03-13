package com.scyypt.InitData;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.scyypt.util.Global;
import com.scyypt.util.ItemFollowupThread;
import com.scyypt.util.MyLoggerThread;

import cn.bestsign.mixed.sdk.MixedSdk;

/**
 * ServletContextListener是对ServeltContext的一个监听.
 * servelt容器启动,serveltContextListener就会调用contextInitialized方法.
 * 在方法里面调用event.getServletContext()可以获取ServletContext,ServeltContext是一个上下文对象,
 * 他的数据供所有的应用程序共享,进行一些业务的初始化.servelt容器关闭,serveltContextListener就会调用contextDestroyed.
 * @author think
 *
 */
@Component
public  class InitData implements ServletContextListener{

	private static Logger logger = Logger.getLogger(InitData.class.getName());

	private static MyLoggerThread myLogger = MyLoggerThread.getInstance();
	
	private static ItemFollowupThread itemFollowup = ItemFollowupThread.getInstance();
	
	protected static MixedSdk mixedSdk;
	public MixedSdk getMixedSdk() {
		return mixedSdk;
	}

	
	protected static String projectPath;


	public static String getProjectPath() {
		return projectPath;
	}

	public static void setProjectPath(String projectPath) {
		InitData.projectPath = projectPath;
	}
	/**
	 * 
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		projectPath = sce.getServletContext().getRealPath("") + "/";
				
		boolean isOk = Global.initConfig(projectPath);
		if(isOk == false) {
			
			return;
		}
		
		Global.setBasePath(projectPath);
		
		projectPath = sce.getServletContext().getRealPath("") + "/";
		
		logger.info("=======》Init_ProjectPath_Parameter:"+projectPath);
		
		// 初始化日志翻译字典
		Global.initLogInfo();		
		
		Global.setBasePath(projectPath);
		
		myLogger.runStart();
		
		Global.setMyLogger(myLogger);
		
		itemFollowup.runStart();
	}
	
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
		myLogger.runStop();
		
		itemFollowup.runStop();
	}
}
