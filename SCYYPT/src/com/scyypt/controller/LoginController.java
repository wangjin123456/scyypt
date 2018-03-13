package com.scyypt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.scyypt.bean.UserAccountBean;
import com.scyypt.entity.Role;
import com.scyypt.entity.UserEntity;
import com.scyypt.service.InterfaceService;
import com.scyypt.service.ItemFollowUpService;
import com.scyypt.service.LoginService;
import com.scyypt.service.UserService;
import com.scyypt.util.Global;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LoginController extends BaseController {

	private static Logger logger = Logger.getLogger(LoginController.class.getName());

	@Autowired
	private InterfaceService interfaceService;
	
	@Autowired
	private UserService userInfoService;
	
	@Autowired
	private LoginService loginService;
	

	@RequestMapping("/Login")
	public void doLogin(HttpServletRequest request, UserAccountBean user, HttpServletResponse response)
			throws Exception {

		String name = user.getUsername();
		String pwd = user.getPassword();
		String corpUuid = user.getCorpId();
		UserEntity userInfo = null;
		Object userResult = null;

		Map<String, String> resultMap = new HashMap<String, String>();

		String loginResult = this.interfaceService.doLogin(name, pwd, corpUuid);

		Global.myLogger.setUserName(name);

		String message = "";
		String content = "";

		boolean isLogin = false;

		JSONObject loginJsonObject = JSONObject.parseObject(loginResult);

		if (Global.isICESuccessed(loginJsonObject) == false) {

			message = Global.getICEMessage(loginResult);

			logger.info(message);

		} else {

			content = Global.getICEContent(loginResult);

			logger.info(content);

			isLogin = true;
		}

		// resultMap.put("resultLogin", loginResult);

		boolean isAuth = false;

		Map<String, Object> loginInfoMap = null;

		String accountUuid = "";
		String accessToken = "";
		
		if (isLogin == true) {

			loginInfoMap = Global.getContentMap(content);

			if (loginInfoMap != null) {

				accountUuid = loginInfoMap.get(Global.USER_INFO_ACCOUNT_UUID).toString();
				String names = loginInfoMap.get(Global.USER_INFO_NAME).toString();

				userInfo = new UserEntity();
				userInfo.setAccountUuid(accountUuid);
				userInfo.setName(names);
				
				if (userResult == null) {// 判断lce用户是否在存在 ,不存在就新增一条
					//userInfoService.addUserinfo(userInfo);
				}

				if (accountUuid != null) {

					String corpId = loginInfoMap.get(Global.USER_INFO_CORP_ID).toString();

					if (Global.isNull(corpId) == false) {

						String authResult = this.interfaceService.doAuth(corpId);

						if (Global.isNull(authResult) == false) {

							JSONObject authJsonObject = JSONObject.parseObject(authResult);

							if (Global.isICESuccessed(authJsonObject) == false) {

								message = Global.getICEMessage(authResult);

								logger.info(message);

								// 鉴权失败返回
								this.jsonToPage(authResult, response);

								return;

							} else {

								content = Global.getICEContent(authResult);

								logger.info(content);

								

								JSONObject object = Global.getContentObject(content);

								if (!Global.isNull(object)) {

									accessToken = object.getString(Global.AUTH_INFO_ACCESS_TOKEN);

									loginInfoMap.put(Global.AUTH_INFO_ACCESS_TOKEN, accessToken);
								}

								isAuth = true;
							}
						}

						// resultMap.put("resultAuth", authResult);
					}
				}
			}
		}

		String authV1SJson = this.interfaceService.doAuthV1();

		if (Global.isICESuccessed(authV1SJson)) {

			JSONObject object = JSONObject.parseObject(authV1SJson);  
			if (object != null) {

				JSONObject contentObject = object.getJSONObject("content");
				
				String v1AccessToken = contentObject.getString("access_token");

				logger.info("v1AccessTokenkey:" + Global.AUTH_INFO_ACCESS_TOKEN_V1 + " Value:" + v1AccessToken);

				if (v1AccessToken != null && !v1AccessToken.trim().equals("")) {
					
					loginInfoMap.put(Global.AUTH_INFO_ACCESS_TOKEN_V1, v1AccessToken);
				}
			}
		}

		Global.setLoginInfo(accountUuid, loginInfoMap);
		
		String jsonString = JSONObject.toJSONString(loginInfoMap);
		
		logger.info(jsonString);
		
		boolean isSupperUser = loginService.checkZZXTSupperUser(accessToken, name);
		
		if(isSupperUser == true) { 
			
		 
		
		}else {
			

			userResult = userInfoService.queryUserinfo(userInfo);

		}

		// String jsonString = JSON.toJSONString(resultMap);
		// logger.info("doLogin->Info:" + jsonString);

		// String resultInfo = Global.getProtocol();
		String rs = JSON.toJSONString(userResult);
		System.err.println("结果：" + rs);
		int str = loginResult.lastIndexOf("}") - 1;
		System.out.println("索引位置：" + str);
		StringBuilder sb = new StringBuilder(loginResult);// 构造一个StringBuilder对象
		sb.insert(str, ",userResult:" + rs);// 在指定的位置1，插入指定的字符串
		loginResult = sb.toString();

		String protocol = Global.getIceProtocolInfo(loginResult);

		logger.info("doLogin->Protocol-Info:" + protocol);

		// Global.myLogger.add("Login", "true", "登录成功");
		// 登录 鉴权返回
		this.jsonToPage(protocol, response);
	}

	@RequestMapping("/Register")
	public void doRegister(HttpServletRequest request, UserAccountBean user, HttpServletResponse response)
			throws Exception {

		String name = user.getUsername();
		String pwd = user.getPassword();

		String corpUuid = user.getCorpId();

	}
	
	
	

	@RequestMapping("/Logout")
	public void doLogout(HttpServletRequest request, HttpServletResponse response, String accountUuid)
			throws Exception {

		String name = Global.getName(accountUuid);
		String user = Global.getUserName(accountUuid);
		
		String message = user+"(" + name + ") ID:" + accountUuid + "注销成功，用户登录信息已经被清理!";
		
		Global.removeLoginInfo(accountUuid);
		
		String protocol =  Global.getProtocol(Global.ICE_OK, message, true);
		
		 Global.myLogger.add("Logout", "true", message);
		 
		this.jsonToPage(protocol, response);
		
	}
	

	@RequestMapping("/getHeadImage")
	public void getHeadImage(HttpServletRequest request, HttpServletResponse response, String accountUuid, String oaName)
			throws Exception {

		String content = Global.getUserIcon(oaName);
		
		String protocol =  Global.getProtocol(Global.ICE_OK, "success", false, content);
 		 
		this.jsonToPage(protocol, response);
	}
	
}
