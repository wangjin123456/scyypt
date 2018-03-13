package com.scyypt.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.scyypt.redis.RedisCacheUtil;

/**
 * 管理组织架构查询工具
 * @author think
 *
 */

public class MangerOrgUtil {
	private static Logger logger = Logger.getLogger(MangerOrgUtil.class);
	//private static OrganizationServiceHttp  organizationServiceHttp = new OrganizationServiceHttpImpl();
	private static RedisCacheUtil redisCacheUtil = new RedisCacheUtil();
	
	
	String appUuid = Global.getConfig(Global.ICE_SCYYPT_APP_UUID_KEY);
	String appId = Global.getConfig(Global.ICE_SCYYPT_APPID_KEY);
	
	String apiURL = Global.getConfig(Global.ICE_SCYYPT_API_URL_KEY);
	
	private static String ctoken = Global.getConfig(Global.ICE_SCYYPT_TOKEN_KEY);
	
	
	/*
	 * Map<String,String>map= super.getCorpId_token(request, response, super.getSessionKey());
logger.info(map.keySet()+">>>>>>>>>>>>>>>>>"+map.values());
//orgUuid="a78820c4-d6db-491b-bcfd-1e476a21a9c5";
if (orgUuid == null || "".equals(orgUuid)) {
	orgUuid = String.valueOf(0);
}
String jsonResult = organizationServiceHttp.getOrgJson(map, orgUuid, "0");
logger.info("---------组织架构树形列表--------" + jsonResult);

jsonToPage(jsonResult, response);
	 */

//	public static String getManageOrgVo(String manageOrgUuid, HttpServletResponse response,
//			HttpServletRequest request) {
//		Map<String,String>map= getCorpId_token(request, response, getSessionKey());
//		logger.info(map.keySet()+">>>>>>>>>>>>>>>>>"+map.values());
//		//orgUuid="a78820c4-d6db-491b-bcfd-1e476a21a9c5";
//		if (manageOrgUuid == null || "".equals(manageOrgUuid)) {
//			return null;
//		}
//	
//		String jsonResult = organizationServiceHttp.getOrgJson(map, manageOrgUuid, "0");
//
//		return jsonResult;
//	}
	
	private static String getSessionKey() {
		return ctoken;
	}

	protected static Map<String,String>getCorpId_token(HttpServletRequest re,HttpServletResponse res,String cookieName){
		String sessionId = getSessionID(re, res, cookieName);
		logger.info("sessionId:"+sessionId);
		String account = redisCacheUtil.get(Md5Util.md5(Global.ACCOUNT.replace("sessionId",sessionId)));
		JSONObject jObject = JSONObject.parseObject(account);
		JSONObject content = jObject.getJSONObject("content");
		String corpId = content.getString("corpId");//租户ID
		String token = redisCacheUtil.get(Md5Util.md5(Global.TOKEN.replace("sessionId",sessionId)));
		Map<String,String>pamMap=new HashMap<String,String>();
		pamMap.put("token", token);
		pamMap.put("corpId", corpId);
		logger.info("account:"+jObject+",token:"+token);
		return pamMap;
	}

	private static String getSessionID(HttpServletRequest re, HttpServletResponse res, String cookieName) {
		String sessionId=null;
		//正式使用
//		sessionId=getCookieValue(res, re, cookieName);
//		if(sessionId==null){
//			sessionId="";
//		}
		
		//前后台联调时运用
		 sessionId="sessionID." + ctoken;
		
		
		return sessionId;
	}

}
