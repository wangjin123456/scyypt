package com.scyypt.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.scyypt.entity.ConversationType;
import com.scyypt.service.ConversationTypeService;
import com.scyypt.util.Global;

/**
 * 洽谈类型控制器
 * @Description: 负责处理用户请求洽谈类型的一系列操作
 * @author ChengChuanPing
 * @Time 2017年12月19日上午9:26:48
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ConversationTypeController extends BaseController {
	private static Logger logger = Logger.getLogger(LoginController.class.getName());

	@Autowired
	private ConversationTypeService conversationTypeService;
	/**
	 * 新增洽谈类型
	 * @param response
	 * @param conversationType 洽谈类型对象
	 */
	@RequestMapping("/addConversationType")
	@ResponseBody
	public void addConversationType(HttpServletResponse response, ConversationType conversationType) {
		if(Global.isNull(conversationType)){
			String message = "前端传值为空!请核对";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message,true);
			jsonToPage(protocol, response);
			return;
		}
		
		int result = conversationTypeService.addConversationType(conversationType);
		if (result == 0){
			String message = "添加洽谈类型失败";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message,true);
			jsonToPage(protocol, response);
		} else{
			String json = JSON.toJSONString(result);
			String jsonResult = Global.getProtocol(json);
			logger.info("添加:"+json+"条洽谈类型");
			jsonToPage(jsonResult, response);
		}
	}
	/**
	 * 查询全部洽谈类型
	 * @param response
	 */
	@RequestMapping("/queryAllConversationType")
	@ResponseBody
	public void queryAllConversationType(HttpServletResponse response) {
		List<ConversationType> result=conversationTypeService.queryAll();
		if (result ==null){
			String message = "未找到洽谈类型";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message,true);
			jsonToPage(protocol, response);
		} else{
			String json = JSON.toJSONString(result);
			String jsonResult = Global.getProtocol(json);
			logger.info("查询洽谈类型数据:"+json);
			jsonToPage(jsonResult, response);
		}
	}
	/**
	 * 修改洽谈类型
	 * @param response
	 * @param conversationType 洽谈类型对象
	 */
	@RequestMapping("/updateConversationType")
	@ResponseBody
	public void updateConversationType(HttpServletResponse response,ConversationType conversationType) {
		if(Global.isNull(conversationType)){
			String message = "前端传值为空!请核对";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message,true);
			jsonToPage(protocol, response);
			return;
		}
		int result=conversationTypeService.updateConversationType(conversationType);
		if (result ==0){
			String message = "修改洽谈类型失败";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message,true);
			jsonToPage(protocol, response);
		} else{
			String json = JSON.toJSONString(result);
			String jsonResult = Global.getProtocol(json);
			logger.info("修改"+json+"条洽谈类型,编号为:"+conversationType.getConversationId());
			jsonToPage(jsonResult, response);
		}
	}
	@RequestMapping("/delConversationType")
	@ResponseBody
	public void delConversationType(HttpServletResponse response,String conversationId) {
		if(Global.isNull(conversationId)){
			String message = "前端传值为空!请核对";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message,true);
			jsonToPage(protocol, response);
			return;
		}
		int result=conversationTypeService.delConversationType(conversationId);
		if (result ==0){
			String message = "删除洽谈类型失败";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message,true);
			jsonToPage(protocol, response);
		} else{
			String json = JSON.toJSONString(result);
			String jsonResult = Global.getProtocol(json);
			logger.info("删除"+json+"条洽谈类型,编号为:"+conversationId);
			jsonToPage(jsonResult, response);
		}
	}
}
