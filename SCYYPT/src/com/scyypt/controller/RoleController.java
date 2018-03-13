package com.scyypt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.quartz.impl.calendar.BaseCalendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.scyypt.entity.Role;
import com.scyypt.service.RoleService;
import com.scyypt.util.Global;

/**
 * 角色控制器
 * 
 * @Description: 负责用户对角色的一系列操作
 *
 * @Author 程传平
 *
 * @Time 2018-01-24 01:40
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class RoleController extends BaseController {

	private static Logger logger = Logger.getLogger(TimeReminderController.class.getName());

	@Autowired
	private RoleService roleService;

	/**
	 * 新增角色信息
	 * 
	 * @Author 程传平
	 * @Time 2018-01-24 01:56
	 *
	 * @param response
	 * @param request
	 * @param role_name
	 *            角色名称
	 */
	@RequestMapping("/addRole")
	@ResponseBody
	public void addRole(HttpServletResponse response, HttpServletRequest request, String role_name) {

		if (Global.isNull(role_name)) {
			String message = "前端传值为空!请核对";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
			jsonToPage(protocol, response);
			return;
		}

		Role role = new Role(role_name);

		int result = roleService.addRole(role);

		if (result == 0) {

			String message = "新增角色失败";
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
	 * 获取全部角色
	 * 
	 * @Author 程传平
	 * @Time 2018-01-22 14:27
	 *
	 */
	@RequestMapping("/getRole")
	@ResponseBody
	public void getRole(HttpServletResponse response, HttpServletRequest request) {

		List<Role> result = roleService.getRole();

		if (result == null) {

			String message = "获取角色失败";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
			jsonToPage(protocol, response);

		} else {

			String jsonRole = JSON.toJSONString(result);
			String jsonResult = Global.getProtocol(jsonRole);
			logger.info("---------角色信息--------" + jsonResult);
			jsonToPage(jsonResult, response);
		}
	}

	/**
	 * 修改角色信息
	 * 
	 * @Author 程传平
	 * @Time 2018-01-24 13:25
	 *
	 * @param response
	 * @param request
	 * @param role
	 *            角色对象
	 */
	@RequestMapping("/updateRole")
	@ResponseBody
	public void updateRole(HttpServletResponse response, HttpServletRequest request, Role role) {

		if (Global.isNull(role)) {
			String message = "前端传值为空!请核对";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
			jsonToPage(protocol, response);
			return;
		}

		int result = roleService.updateRole(role);

		if (result == 0) {

			String message = "修改角色失败";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
			jsonToPage(protocol, response);

		} else {

			String jsonRole = JSON.toJSONString(result);
			String jsonResult = Global.getProtocol(jsonRole);
			logger.info("---------修改角色信息===》编号：" + role.getRole_id());
			jsonToPage(jsonResult, response);
		}
	}

	/**
	 * 删除角色信息
	 * 
	 * @Author 程传平
	 * @Time 2018-01-24 13:27
	 *
	 * @param response
	 * @param request
	 * @param role_id
	 *            角色编号
	 */
	@RequestMapping("/delRole")
	@ResponseBody
	public void delRole(HttpServletResponse response, HttpServletRequest request, String role_id) {

		if (Global.isNull(role_id)) {
			String message = "前端传值为空!请核对";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
			jsonToPage(protocol, response);
			return;
		}

		int result = roleService.delRole(role_id);

		if (result == 0) {

			String message = "删除角色失败";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
			jsonToPage(protocol, response);

		} else {

			String jsonRole = JSON.toJSONString(result);
			String jsonResult = Global.getProtocol(jsonRole);
			logger.info("---------删除角色信息===》编号：" + role_id);
			jsonToPage(jsonResult, response);
		}

	}

	/**
	 * 设置角色菜单模块功能权限
	 * 
	 * @Author 程传平
	 * @Time 2018-01-27 15:05
	 *
	 * @param response
	 * @param request
	 * @param roles
	 *            角色信息集合
	 */
	@RequestMapping("/setRoleAuthority")
	@ResponseBody
	private void setRoleAuthority(HttpServletResponse response, HttpServletRequest request, String roles) {

		if (Global.isNull(roles)) {
			String message = "前端传值为空!请核对";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
			jsonToPage(protocol, response);
			return;
		}

		Integer result = roleService.setRoleAuthority(roles);

		if (result == 0) {

			String message = "设置角色菜单权限模块失败";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
			jsonToPage(protocol, response);

		} else {

			String jsonRole = JSON.toJSONString(result);
			String jsonResult = Global.getProtocol(jsonRole);
			logger.info("---------设置角色菜单权限模块成功：" + jsonResult);
			jsonToPage(jsonResult, response);
		}
	}

}
