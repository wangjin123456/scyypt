package com.scyypt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.scyypt.redis.RedisCacheUtil;
import com.scyypt.util.Global;
import com.scyypt.util.Md5Util;

/**
 * 
 * @ClassName: BaseController
 * @Description: Controller基类，所有后续开发的Controller都必须继承这个基类
 * @author wangdekun
 * @date 2017年8月18日 下午1:42:46
 *
 */
//@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class BaseController {

	private static Logger logger = Logger.getLogger(BaseController.class.getName());

	@Autowired
	RedisCacheUtil redisCacheUtil;
	
	String sessionID;
	
	

	/**
	 * 
	 * @Description 跳转到WEB-INF/下的某个文件夹下的jsp页面 folder ： 文件夹名 jspPage ：
	 *              目标jsp页面名，不带扩展名
	 *
	 * @param folder
	 *            文件夹名
	 * @param jspPage
	 *            目标jsp页面名，不带扩展名
	 * @return
	 */
	protected String toPage(String folder, String jspPage) {

		return (folder + '/' + jspPage);
	}

	/**
	 * 
	 * @Description 重定向到另一个controller
	 *
	 * @param redirect
	 * @return
	 */
	protected String redirectPage(String redirect) {
		return "redirect:/" + redirect;

	}
	
	
    /**
	 * 
	 * @Description 用于返回json数据
	 *
	 * @param jsonResult
	 *            需要返回的json 字符串数据
	 * @param response
	 */
	protected void jsonToPage(String jsonResult, HttpServletResponse response) {

		response.setContentType("application/json;charset=UTF-8");
		// 获取jsonResult并发送响应
		PrintWriter out;
		try {
			out = response.getWriter();
			out.println(jsonResult);
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.error("输出失败", e);
		}
	}
	/**
	 * 返回list json数据
	 * @param <T>
	 * @param list
	 * @param response
	 */
	public <T> void leistJsonToPag(List<T> list, HttpServletResponse response) {
		String json = JSON.toJSONString(list);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.println(json);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			logger.error("输出失败", e);
			e.printStackTrace();
		}
		
		
	}
	
	public void beanJsonToPage(Object bean, HttpServletResponse response) {
		String jsonString = JSON.toJSONString(bean);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter writer = null;
		try {            
			writer = response.getWriter();
			writer.println(jsonString);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			logger.error("输出失败", e);
			e.printStackTrace();
		}
		
	}
	
	 protected String getSessionKey(){
		 
		 String token = Global.getConfig(Global.ICE_SCYYPT_TOKEN_KEY);
		 
		 return token;
	 }
	
	 /**
	 * 获取CorpId和token
	 * @return
	 */
	protected Map<String,String>getCorpId_token(HttpServletRequest re,HttpServletResponse res,String cookieName){
		String sessionId = getSessionID(re, res, cookieName);
		logger.info("sessionId:"+sessionId);
		String account = redisCacheUtil.get(Md5Util.md5(Global.ACCOUNT.replace("sessionId",sessionId)));
		JSONObject jObject = JSONObject.parseObject(account);
		if (jObject == null) {
			return null;
		}
		JSONObject content = jObject.getJSONObject("content");
		String corpId = content.getString("corpId");//租户ID
		String token = redisCacheUtil.get(Md5Util.md5(Global.TOKEN.replace("sessionId",sessionId)));
		Map<String,String>pamMap=new HashMap<String,String>();
		pamMap.put("token", token);
		pamMap.put("corpId", corpId);
		logger.info("account:"+jObject+",token:"+token);
		return pamMap;
		
	}
	protected JSONObject getAccount(HttpServletRequest re,HttpServletResponse res,String cookieName){
		String sessionId = getSessionID(re, res, cookieName);
		logger.info("sessionId:"+sessionId);
		String account = redisCacheUtil.get(Md5Util.md5(Global.ACCOUNT.replace("sessionId",sessionId)));
		JSONObject jObject = JSONObject.parseObject(account);
		JSONObject content = jObject.getJSONObject("content");
		return content;
	}
	
	/**
	 * 获取cookie中的sessionId
	 */
	protected String getSessionID(HttpServletRequest re,HttpServletResponse res,String cookieName){
		String sessionId=null;
		//正式使用
//		sessionId=getCookieValue(res, re, cookieName);
//		if(sessionId==null){
//			sessionId="";
//		}
		
		//前后台联调时运用
		
		
		 String token = Global.getConfig(Global.ICE_SCYYPT_TOKEN_KEY);
		 
		 sessionId="sessionID."+ token;
		
		return sessionId;
	}
	/**
	 * 将sessionId存到cookie中
	 * @param re
	 * @param res
	 * @param cookieName
	 */
	protected void setSessionID( HttpServletRequest re,HttpServletResponse res,String cookieName){
		//String cookieName=Md5Util.md5("sessionID."+APP.token);
		String cookieValue=re.getSession().getId();
		saveCookie("save", cookieName, cookieValue, res, re);
		
		//暂时调试用
//		String key="sessionID."+APP.token;
//		String sessionId = req.getSession().getId();
//		redisCacheUtil.set(key, sessionId);
//		logger.info("Session Id:" + sessionId);
	}
	
	
	/**
	 * 保存或者删除指定cookic
	 */
	public boolean saveCookie(String isDeleteOrSave,String cookieName,String cookieValue,HttpServletResponse res,HttpServletRequest re){
		if(isDeleteOrSave.trim().equals("save")){//保存到Cookie
			 //Cookie cookieAdmin = new Cookie(Md5Util.md5("sessionID."+APP.token),re.getSession().getId());
			logger.info("cookieValue:" + cookieValue);
			Cookie cookieAdmin=new Cookie(cookieName,cookieValue);
			cookieAdmin.setPath("/");
			cookieAdmin.setHttpOnly(true);
			res.addCookie(cookieAdmin);
			logger.info(cookieAdmin);
			// res.setHeader("session_Id", cookieValue);
		}else{
			Cookie[] c=re.getCookies();
			String[] pam=null; 
			if(c.length>0){
				for (int i = 0; i < c.length; i++) {
					Cookie cook = c[i];
					if(cook.getName().equalsIgnoreCase(cookieName)){
						Cookie newCookie=new Cookie(cook.getName(),null); //要删除的Cookie
						newCookie.setMaxAge(0); //立即删除
						newCookie.setPath("/");
						res.addCookie(newCookie);
					};
				}
			}
		}
		return true;
	}
	/**
	 * 获取指定cookicValue
	 */
	public String getCookieValue(HttpServletResponse res,HttpServletRequest re,String cookieName){
		// 检查Cookie
				Cookie[] c = re.getCookies();
				logger.info("valueC:" + c);
				String value = null;
				if (c != null && c.length > 0) {
					for (int i = 0; i < c.length; i++) {
						Cookie cook = c[i];
						if (cook.getName() != null) {
							if (cook.getName().equalsIgnoreCase(cookieName)) {
								value = cook.getValue();
								break;
							}
						}
					}
				}
				logger.info("value:" + value);
				return value;
	}
}
