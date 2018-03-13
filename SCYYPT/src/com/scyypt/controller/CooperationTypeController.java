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
import com.scyypt.entity.CooperationTypeEntity;
import com.scyypt.service.CooperationTypeService;
import com.scyypt.util.Global;

/**
 * 
 * 合作类型控制器
 * 
 * @Description: 负责处理用户请求合作类型的一系列操作
 * @author ChengChuanPing
 * @Time 2017年12月18日上午10:01:33
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CooperationTypeController extends BaseController {
	private static Logger logger = Logger.getLogger(LoginController.class.getName());

	@Autowired
	private CooperationTypeService cooperationTypeService;

	/**
	 * 查询全部合作类型
	 * 
	 */
	@RequestMapping("/queryAllCooperationType")
	@ResponseBody
	public void queryAllCooperationType(HttpServletResponse response) {
		List<CooperationTypeEntity> item = cooperationTypeService.list();
		if(item==null){
			String message = "没有查询到 合作类型";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message,true);
			jsonToPage(protocol, response);
		}
		else{
			String json = JSON.toJSONString(item);
			String jsonResult = Global.getProtocol(json);
			logger.info("---------合作类型--------" + jsonResult);
			jsonToPage(jsonResult, response);
		}
	}

	/**
	 * 添加合作类型
	 * 
	 * @param cooperationTypeEntity
	 *            合作类型对象
	 * 
	 */
	@RequestMapping("/addCooperationType")
	@ResponseBody
	public void addCooperationType(HttpServletResponse response, CooperationTypeEntity cooperationTypeEntity) {
		if(Global.isNull(cooperationTypeEntity)){
			String message = "前端传值为空!请核对";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message,true);
			jsonToPage(protocol, response);
			return;
		}
		
		int result = cooperationTypeService.add(cooperationTypeEntity);
		if (result==0) {
			String message = "添加合作类型失败";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message,true);
			jsonToPage(protocol, response);
		} else {
			String json = JSON.toJSONString(result);
			String jsonResult = Global.getProtocol(json);
			logger.info("添加" + json + "条合作类型");
			jsonToPage(jsonResult, response);
		}
	}

	/**
	 * 修改合作类型
	 * 
	 * @param cooperationTypeEntity
	 *            合作类型对象
	 * 
	 */
	@RequestMapping("/updateCooperationType")
	@ResponseBody
	public void updateCooperationType(HttpServletResponse response,CooperationTypeEntity cooperationTypeEntity) {
		if(Global.isNull(cooperationTypeEntity.getId())){
			String message = "前端传值为空!请核对";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message,true);
			jsonToPage(protocol, response);
			return;
		}
		int result=cooperationTypeService.edit(cooperationTypeEntity);
		if (result==0) {
			String message = "修改合作类型失败";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message,true);
			jsonToPage(protocol, response);
		} else {
			String json = JSON.toJSONString(result);
			String jsonResult = Global.getProtocol(json);
			logger.info("修改" + json + "条合作类型,编号为:"+cooperationTypeEntity.getId());
			jsonToPage(jsonResult, response);
		}
	}

	/**
	 * 删除合作类型
	 * 
	 * @param id
	 *            合作类型编号
	 * 
	 */
	@RequestMapping("/delCooperationType")
	@ResponseBody
	public void delCooperationType(HttpServletResponse response,String id) {
		if(Global.isNull(id)){
			String message = "前端传值为空!请核对";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message,true);
			jsonToPage(protocol, response);
			return;
		}
		int result=cooperationTypeService.del(id);
		if (result==0) {
			String message = "删除合作类型失败";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message,true);
			jsonToPage(protocol, response);
		} else {
			String json = JSON.toJSONString(result);
			String jsonResult = Global.getProtocol(json);
			logger.info("删除" + json + "条合作类型,编号为:"+id);
			jsonToPage(jsonResult, response);
		}
	}
}
