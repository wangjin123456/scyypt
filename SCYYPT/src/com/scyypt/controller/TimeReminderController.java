package com.scyypt.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.scyypt.entity.ItemInfoEntity;
import com.scyypt.service.ICEInterfaceService;
import com.scyypt.service.ItemInfoService;
import com.scyypt.util.Global;


/**
 * 时间提醒控制器
 * 
 * @Description:
 * @author ChengChuanPing
 * @Time 2018年1月11日上午10:41:07
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TimeReminderController extends BaseController {

	private static Logger logger = Logger.getLogger(TimeReminderController.class.getName());

	@Autowired
	ItemInfoService itemInfoService;
	
	@Autowired
	private ICEInterfaceService iceInterfaceService;

	/**
	 * 时间提醒设置， 在时间提醒后发送邮件
	 * 
	 * @param request
	 * @param response
	 * @param oaList
	 *            收件人列表
	 * @param time
	 *            时间
	 * @param days
	 *            天数
	 * @param body
	 *            正文内容
	 */
	@RequestMapping("/setTimeReminder")
	@ResponseBody
	public void setTimeReminder(HttpServletRequest request, HttpServletResponse response, String accountUuid, Integer id, String oaList, String time,
			String days, String body) {

		if(oaList == null || "".equals(oaList.trim()) || 
		   time == null || "".equals(time.trim()) ||
		   days == null || "".equals(days.trim()) || 
		   body == null || "".equals(body.trim())) {
					
			String message = "时间提醒所需要参数不能为空!";
			String jsonResult = Global.getProtocol(Global.ICE_UNKNOW, message);

			jsonToPage(jsonResult, response);
		}
		
		String corpId = Global.getUserCorpId(accountUuid);
		String user = Global.getUserName(accountUuid);
		String name = Global.getName(accountUuid); 
		
		Date nowDate = new Date();
		Date date = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {

			date = dateFormat.parse(time);

			logger.info(date);
			// 调用Date里面的before方法来做判断
			boolean flag = date.before(nowDate);

			if (flag) {

				String message = "该日期不能早于现在!";

				String jsonResult = Global.getProtocol(Global.ICE_UNKNOW, message);

				jsonToPage(jsonResult, response);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		
		ItemInfoEntity itemInfoEntity = new ItemInfoEntity();
		itemInfoEntity.setItemId(id);
		itemInfoEntity.setReminder_oa(oaList);
		itemInfoEntity.setReminder_expire(date);
		itemInfoEntity.setReminder_days(Integer.parseInt(days));
		itemInfoEntity.setReminder_content(body);
	    
	    int r = itemInfoService.updateReminder(itemInfoEntity);
	    
	    if(r <= 0) {
	    	
	    		logger.info("时间提醒信息保存失败");
	    }
	    
	    String jsonResult = iceInterfaceService.setTimeReminder(oaList, time, days, body, corpId, user, name);

		String iceProtocol = Global.getIceProtocolInfo(jsonResult);

		logger.info(iceProtocol);

		jsonToPage(jsonResult, response);
	}

	@RequestMapping("/doTimeReminderCallback")
	@ResponseBody
	public void doTimeReminderCallback(HttpServletRequest request,HttpServletResponse response, String oaList, String body, String corpId, String user, String name) {
		 
		logger.info("=======================时间提醒 callback (doTimeReminderCallback) 被调用 看来时间点被成功触发==========================");
		
		
		logger.info("时间提醒 callback 被调用发邮件所需要参数信息");
		logger.info("oaList:" + oaList );
		logger.info("body:" + body);
		logger.info("corpId:" + corpId);
		logger.info("userName:" + user);
		logger.info("name:" + name);
		


		if(oaList == null || "".equals(oaList.trim())) {
			
			String message = "发时间提醒邮件所需要参数(OA列表)不能为空!";
		 	String jsonResult = Global.getProtocol(Global.ICE_UNKNOW, message);
    		
		 	 jsonToPage(jsonResult, response);
		}
		
		
		if(body == null || "".equals(body.trim())) {
			
			String message = "发时间提醒邮件正文不能为空!";
		 	String jsonResult = Global.getProtocol(Global.ICE_UNKNOW, message);
    		
		 	 jsonToPage(jsonResult, response);
		}
		
		if(corpId == null || "".equals(corpId.trim())) {
			
			String message = "发时间提醒邮件租户ID不能为空!";
		 	String jsonResult = Global.getProtocol(Global.ICE_UNKNOW, message);
    		
		 	 jsonToPage(jsonResult, response);
		}

		String jsonResult = iceInterfaceService.sendEmail(oaList, body, corpId, user, name);

		logger.info(jsonResult);

		jsonToPage(jsonResult, response);
	}

	

	@RequestMapping("/doUserSearchKey")
	@ResponseBody
	public void selectEmployee(HttpServletRequest request,HttpServletResponse response,String accountUuid, String keyword){
		
		
		String accessToken = Global.getAccessToken(accountUuid);
		logger.info("accessToken:" + accessToken);
		
		if(accessToken == null || accessToken.trim().equals("")) {
			
			String message = Global.RELOGIN_HINT;
			
			String jsonString = Global.getProtocol(Global.ICE_UNKNOW, message);
			
			jsonToPage(jsonString, response);
			
			return;
		}
		
	    String jsonResult = iceInterfaceService.doUserSearchKey(accountUuid, keyword);
	    
	    if(!Global.isICESuccessed(jsonResult)) {
	    	
		    	String message = Global.getICEMessage(jsonResult);
		    	
		    	String jsonString = Global.getProtocol(Global.ICE_UNKNOW, message);
		    	
			jsonToPage(jsonString, response);

	    }
	    
	    String content = Global.getICEContent(jsonResult);
	    
	    if(content == null || content.trim().equals("")) {
	    	
		    	String message = "协议包体不能为空!";
		    	
		    	String jsonString = Global.getProtocol(Global.ICE_UNKNOW, message);
		    	
		    	jsonToPage(jsonString, response);
	    }
	    
	    
	    JSONArray a  = JSONArray.parseArray(content);
	    JSONArray b = new JSONArray();
	    
	    int len = a.size();
	    
	    for(int i = 0; i < len; i ++) {
	    	
		    	JSONObject o = a.getJSONObject(i);
		    	
		    	String username = o.getString("username");
		    	String name = o.getString("name");
		    	
		    	JSONObject temp = new JSONObject();
		    	temp.put("uid", username);
		    	temp.put("uname", name);
		    	temp.put("rType", "0");
		    	
		    	b.add(temp);
	    }
	    
	    String jsonString = b.toJSONString();
	    
	    String jsonProtocol = Global.getProtocol(jsonString);
		
		jsonToPage(jsonProtocol, response);
	}
}
