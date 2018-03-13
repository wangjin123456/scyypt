package com.scyypt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.scyypt.entity.Authority;
import com.scyypt.service.AuthorityService;
import com.scyypt.util.Global;

/**
 * 
 * @Description:
 *
 * @Author 程传平
 *
 * @Time 2018-01-24 02:10
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthorityController extends BaseController {

	private static Logger logger = Logger.getLogger(LoginController.class.getName());

	@Autowired
	private AuthorityService authorityService;

	/**
	 * 
	 * @Author 程传平
	 * @Time 2018-01-24 17:06
	 *
	 * @param response
	 * @param request
	 * @param operate
	 */
	@RequestMapping("/addAuthority")
	@ResponseBody
	public void addAuthority(HttpServletResponse response, HttpServletRequest request, String operate) {

		if(Global.isNull(operate)){
			String message = "前端传值为空!请核对";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message,true);
			jsonToPage(protocol, response);
			return;
		}
		
		Authority authority = new Authority(operate);

		int result = authorityService.addAuthority(authority);

		if (result == 0) {

			String message = "新增权限模块失败";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
			jsonToPage(protocol, response);

		} else {

			String jsonAuthority = JSON.toJSONString(result);
			String jsonResult = Global.getProtocol(jsonAuthority);

			logger.info("---------权限模块信息--------" + jsonResult);
			jsonToPage(jsonResult, response);
		}
	}

	/**
	 * 获取全部与权限相关的操作
	 * 
	 * @Author 程传平
	 * @Time 2018-01-23 09:12
	 *
	 * @param response
	 * @param request
	 */
	@RequestMapping("/getAllAuthority")
	@ResponseBody
	private void getAllAuthority(HttpServletResponse response, HttpServletRequest request) {

		List<Authority> result = authorityService.getAllAuthority();

		if (result == null) {

			String message = "获取操作失败";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
			jsonToPage(protocol, response);

		} else {

			String jsonAuthority = JSON.toJSONString(result);
			String jsonResult = Global.getProtocol(jsonAuthority);

			logger.info("---------角色信息--------" + jsonResult);
			jsonToPage(jsonResult, response);
		}

	}

	/**
	 * 修改权限模块
	 * 
	 * @Author 程传平
	 * @Time 2018-01-24 17:09
	 *
	 * @param response
	 * @param request
	 * @param authority
	 *            权限对象
	 */
	@RequestMapping("/updateAuthority")
	@ResponseBody
	private void updateAuthority(HttpServletResponse response, HttpServletRequest request, Authority authority) {

		if(Global.isNull(authority)){
			String message = "前端传值为空!请核对";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message,true);
			jsonToPage(protocol, response);
			return;
		}
		
		int result = authorityService.updateAuthority(authority);

		if (result == 0) {

			String message = "修改权限名称失败";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
			jsonToPage(protocol, response);

		} else {

			String jsonAuthority = JSON.toJSONString(result);
			String jsonResult = Global.getProtocol(jsonAuthority);

			logger.info("---------修改权限名称===》编号：" + authority.getAuthority_id());
			jsonToPage(jsonResult, response);
		}

	}

	/**
	 * 根据权限编号删除权限名称
	 * 
	 * @Author 程传平
	 * @Time 2018-01-24 17:23
	 *
	 * @param response
	 * @param request
	 * @param authority_id
	 *            权限名称编号
	 */
	@RequestMapping("/delAuthority")
	@ResponseBody
	private void delAuthority(HttpServletResponse response, HttpServletRequest request, String authority_id) {

		if(Global.isNull(authority_id)){
			String message = "前端传值为空!请核对";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message,true);
			jsonToPage(protocol, response);
			return;
		}
		
		int result = authorityService.delAuthority(authority_id);

		if (result == 0) {

			String message = "修改权限名称失败";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
			jsonToPage(protocol, response);

		} else {

			String jsonAuthority = JSON.toJSONString(result);
			String jsonResult = Global.getProtocol(jsonAuthority);

			logger.info("---------删除权限名称===》编号：" + authority_id);
			jsonToPage(jsonResult, response);
		}
	}

}
