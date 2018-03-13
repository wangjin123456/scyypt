package com.scyypt.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.scyypt.entity.UserEntity;
import com.scyypt.service.ICEInterfaceService;
import com.scyypt.service.URAProviderService;
import com.scyypt.service.UserService;
import com.scyypt.util.Global;

/**
 * reviseAuthority
 * @Description: 用户权限控制器
 *
 * @Author 程传平
 *
 * @Time 2018-01-21 20:40
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserPowerController extends BaseController {

	private static Logger logger = Logger.getLogger(UserPowerController.class.getName());

	@Autowired
	private ICEInterfaceService iceInterfaceService;

	@Autowired
	private UserService userService;

	@Autowired
	private URAProviderService uRAProviderService;

	/**
	 * 权限模块使用，获取管理组织架构
	 * 
	 * @param response
	 * @param request
	 * @param accountUuid
	 *            用户编号
	 * @param orgUuid
	 *            组织编号
	 */
	@RequestMapping("/getOrgStructure")
	@ResponseBody
	public void getOrgStructure(HttpServletResponse response, HttpServletRequest request, String accountUuid,
			String orgUuid) {

		if (Global.isNull(accountUuid)) {
			String message = "前端传值错误请请核对";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
			jsonToPage(protocol, response);
		}

		if(orgUuid == null || orgUuid.trim().equals("")) {
			orgUuid = "0";
		}
		
		String result = iceInterfaceService.getOrgList(accountUuid, orgUuid);

		if (result == null) {

			String message = "获取管理组织架构失败";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
			jsonToPage(protocol, response);

		} else {
			// result = result.replace("[", "");
			// result = result.replace("]", "");

			// String jsonResult = Global.getProtocol(result);
			logger.info("---------组织架构树形列表--------" + result);
			jsonToPage(result, response);
		}

	}

	/**
	 * 获取管理员组织架构员工列表
	 * 
	 * @param response
	 * @param request
	 * @param accountUuid
	 *            用户编号
	 * @param orgUuid
	 *            父级id
	 */
	@RequestMapping("/getOrgStructureUsers")
	@ResponseBody
	public void getOrgStructureUsers(HttpServletResponse response, HttpServletRequest request, String accountUuid,
			String orgUuid) {

		if (Global.isNull(accountUuid) || Global.isNull(orgUuid)) {
			String message = "前端传值错误请请核对";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
			jsonToPage(protocol, response);
		}

		String content = iceInterfaceService.getOrgAgents(accountUuid, orgUuid);

		if (content == null) {

			String message = "获取管组织架构员工列表失败！";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
			jsonToPage(protocol, response);

		} else {

			String jsonResult = Global.getProtocol(content);
			logger.info("---------组织架构员工列表--------" + jsonResult);
			jsonToPage(jsonResult, response);
		}
	}

	/**
	 * 获取用户权限列表
	 * 
	 * @Author 程传平
	 * @Time 2018-01-21 20:37
	 *
	 * @param response
	 * @param request
	 * @param corpId
	 *            父级Id
	 */
	@RequestMapping("/showUsers")
	@ResponseBody
	public void showUsers(HttpServletResponse response, HttpServletRequest request, String corpId) {

		List<UserEntity> result = userService.showUsers(corpId);

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
	 * 添加用户并受默认权限
	 * 
	 * @Author 程传平
	 * @Time 2018-01-21 20:48
	 *
	 * @param response
	 * @param request
	 * 
	 * @param users
	 *            用户信息对象
	 */
	@RequestMapping("/addUsers")
	@ResponseBody
	public void addUsers(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "users") String users) {

		logger.info(users);
		
		String deUsers = "";
		
		try {
			deUsers =  URLDecoder.decode(users, "utf-8");
			
		}catch(Exception e ) {
			
			e.printStackTrace();
		}
		
		logger.info(deUsers);
		
		JSONObject json = JSONObject.parseObject(deUsers);

		// Integer role_id =
		// Integer.parseInt(json.get("parentId").toString());// 角色编号

		// JSONArray jArr = json.getJSONArray("childId");

		// ArrayList<Integer> authorityId = new ArrayList<>();// 用于保存操作模块的Id

		// for (int i = 0; i < jArr.size(); i++) {

		// JSONObject obj2 = jArr.getJSONObject(i);
		// authorityId.add(Integer.parseInt(obj2.get("id").toString()));
		// }

		// ArrayList<Integer> userId = new ArrayList<>();// 用于保存用户的Id

		Integer result = 0;
		JSONArray people = json.getJSONArray("people");

		for (int i = 0; i < people.size(); i++) {

			JSONObject people1 = people.getJSONObject(i);

			String accountUuid = people1.getString("accountUuid");

			String corpId = people1.getString("corpId");

			String userName = people1.getString("username");

			String name = people1.getString("name");

			String mobile = people1.getString("mobile");

			String email = people1.getString("email");

			Integer sex = Integer.parseInt(people1.getString("sex"));

			String jobType = people1.getString("jobType");

			String jobUuid = people1.getString("jobUuid");

			Integer role_id = 3;

			UserEntity userEntity = new UserEntity(accountUuid, corpId, userName, name, mobile, email, sex, jobType,
					jobUuid, role_id);
			List<UserEntity> userList = userService.searchUser(userEntity);
			if (userList.size() == 0) {
				result = userService.addUsers(userEntity);
			} else {
				// String message = "账号：" + userEntity.getUserName() + "姓名：" +
				// userEntity.getName() + "用户已存在";
				// String protocol = Global.getProtocol(Global.ICE_UNKNOW,
				// message, true);
				// jsonToPage(protocol, response);
			}

		}

		/*
		 * int result = 0; for (int i = 0; i < userId.size(); i++) {
		 * 
		 * URAProvider uRAProvider = new URAProvider(userId.get(i), role_id,
		 * authorityId.get(i), "true");
		 * 
		 * result = uRAProviderService.addProvider(uRAProvider);
		 * 
		 * }
		 */
		if (result == 0) {

			String message = "操作失败";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
			jsonToPage(protocol, response);

		} else {

			String jsonAuthority = JSON.toJSONString(result);
			String jsonResult = Global.getProtocol(jsonAuthority);
			logger.info("---------新增用户信息--------" + jsonResult);
			jsonToPage(jsonResult, response);
		}

	}

	/**
	 * 设置用户角色
	 * 
	 * @Author 程传平
	 * @Time 2018-01-24 21:51
	 *
	 * @param response
	 * @param request
	 * @param users
	 *            用户全部信息
	 */
	@RequestMapping(value = "/reviseAuthority", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public void reviseAuthority(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "users") String users) {

		logger.info(users);
		
		String deUsers = "";
		
		try {
			deUsers =  URLDecoder.decode(users, "utf-8");
			
		}catch(Exception e ) {
			
			e.printStackTrace();
		}
		
		logger.info(deUsers);
		
		JSONObject json = JSONObject.parseObject(deUsers);
		

		JSONArray user = json.getJSONArray("user");

		ArrayList<Integer> userId = new ArrayList<>();// 用于保存用户的Id

		UserEntity userEntity = new UserEntity();

		for (int i = 0; i < user.size(); i++) {

			JSONObject peopleId = user.getJSONObject(i);

			String user_id = peopleId.getString("user_id");

			userId.add(Integer.parseInt(user_id));
		}

		Integer role_id = Integer.parseInt(json.get("role_id").toString());// 角色编号

		// JSONArray authority_id = json.getJSONArray("authority");

		Integer result = 0;

		for (int i = 0; i < userId.size(); i++) {

			// for (int j = 0; j < authority_id.size(); j++) {
			// JSONObject people = people_id.getJSONObject(i);
			// JSONObject authority = userId.getJSONObject(j);
			// String authorityId = authority.getString("authority_id");
			// String status = authority.getString("status");
			// uRAProvider.setUser_id(userId.get(i));
			// uRAProvider.setRole_id(role_id);
			// uRAProvider.setAuthority_id(Integer.parseInt(authorityId));
			// uRAProvider.setStatus(status);

			userEntity.setU_id(userId.get(i));
			userEntity.setRole_id(role_id);
			result = userService.updateUser(userEntity);

			// }
		}

		if (result == 0) {

			String message = "设置用户权限失败";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
			jsonToPage(protocol, response);

		} else {

			String jsonResult = Global.getProtocol(result);
			logger.info("---------角色信息--------" + jsonResult);
			jsonToPage(jsonResult, response);

		}
	}

}
