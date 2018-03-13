package com.scyypt.util;


/**
 * 全局常量
 * @author think
 *
 */

/*
 * 协议包说明 ICE
 * 
 * type:0
 * 
 * code:0,-1  // 0 成功  -1失败 
 *  
 * message:{}  // 返回提示信
 * 
 * content:{}  // 数据包体
 * 
 */

/*
 * 协议包说明 自定义
 * 
 * type:1
 * 
 * code:0,-1  // 0 成功  -1失败 
 *  
 * message:{
 * 		show:true|false    // true显示  false 不显示
 *      info:{}            // 提示信息
 * }  // 返回提示信
 * 
 * content:{}  // 数据包体
 * 
 */



import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.scyypt.util.ThreadPool;

/**
 * 全局常量
 * 
 * @author think
 *
 */



public class Global {

	/******** 自定义状态码 *********/

	public final static int ICE_UNKNOW = -1;
	public final static int ICE_OK = 0;

	public final static int SUCCESS = 200;// 成功
	public final static int FAILD = 500;// 失败

	/******** 时间(单位：秒) *******/
	public final static int TWO_HOUR_SECOND = 7200;// 2小时

	//  跟进socket server 端口
	public final static int  PORT	= 	8899;
	
	
	public final static String RELOGIN_HINT = "登录已失效，请重新登录！";

	
	/******** 缓存KEY ************/
	public final static String ACCOUNT = "sessionIdaccount";// ICE返回的account信息(字符串中的sessionId请替换成动态sessionId)
	public final static String TOKEN = "sessionIdtoken";// ICE返回的accountToken信息(字符串中的sessionId请替换成动态sessionId)
	public final static String ORGTYPES = "sessionIdorgTypes";// ICE返回的orgTypes信息(字符串中的sessionId请替换成动态sessionId)

	////////////////////////////////////// 协议键///////////////////////////////////////
	public final static String PROTOCOL_TYPE_KEY = "type";
	public final static String PROTOCOL_CODE_KEY = "code";
	public final static String PROTOCOL_MESSAGE_KEY = "message";
	public final static String PROTOCOL_CONTENT_KEY = "content";

	public final static String PROTOCOL_MESSAGE_SHOW_KEY = "show";
	public final static String PROTOCOL_MESSAGE_INFO_KEY = "info";

	public final static String PROTOCOL_TYPE_ICE_VALUE = "0";
	public final static String PROTOCOL_TYPE_CUSTOMER_VALUE = "1";

	/////////////////////////// 0组织架构、1客户架构、2法人架构、3供方架构、4国家架构///////////////////////////////
	public final static String STRUCTURE_TYPE_ORG = "0";
	public final static String STRUCTURE_TYPE_CUSTOM = "1";
	public final static String STRUCTURE_TYPE_LEGALPERSON = "2";
	public final static String STRUCTURE_TYPE_PROVIDER = "3";
	public final static String STRUCTURE_TYPE_STATE = "4";

	///////////////////////////////////////// 权限常量//////////////////////////////////////////////////////

	private static Logger logger = Logger.getLogger(Global.class.getName());

	public static Map<String, String> configMap = new HashMap<String, String>();

	private static Map<String, Object> loginInfoMap = new HashMap<String, Object>();

	private static Map<String, String> logInfoMap = new HashMap<String, String>();

	///////////////////////////////////////// 市场运营系统配置//////////////////////////////////////////////////////
	public final static String ICE_SCYYPT_NUMBER_KEY					= 				"number";
	public final static String ICE_SCYYPT_CODE_KEY = "code";
	public final static String ICE_SCYYPT_INFO_KEY = "info";
	public final static String ICE_SCYYPT_TOKEN_KEY = "token";
	public final static String ICE_SCYYPT_APPID_KEY = "appID";
	public final static String ICE_SCYYPT_CLIENT_SECRET_KEY = "clientSecret";

	public final static String ICE_SCYYPT_APP_UUID_KEY = "appUuid";
	public final static String ICE_SCYYPT_APP_SECRET_KEY = "appSecret";

	public final static String ICE_SCYYPT_API_URL_KEY = "iceApiURL";
	public final static String ICE_SCYYPT_TIMESTAMP_URL_KEY = "timestampURL";

	
	public final static String ICE_SCYYPT_V1_APPKEY_KEY					=						"AppKey";
	public final static String ICE_SCYYPT_V1_APPSECRET_KEY				=					"AppSecret";
	
	
	public final static String SERVER_PATH_KEY = "serverPath";
	
	
	
	//////////////////////////////////////登录 鉴权键///////////////////////////////////////
	public final static String  USER_INFO_NAME						= 			"name";		// 真名姓名
	public final static String  USER_INFO_USER						= 			"user"; 		// 帐号
	public final static String  USER_INFO_PWD						= 			"pwd";
	public final static String  USER_INFO_PHONE						= 			"phone";
	public final static String  USER_INFO_EMAIL						= 			"email";
	public final static String  USER_INFO_ACCOUNT_UUID				= 			"accountUuid";
	public final static String  USER_INFO_CORP_ID					= 			"corpId";
	public final static String  AUTH_INFO_ACCESS_TOKEN				= 			"accessToken";
	
	public final static String  AUTH_INFO_ACCESS_TOKEN_V1				= 			"accessToken_v1";
	

	/////////////////////////////////////////// 配置文件//////////////////////////////////////////////////////
	public final static String CONFIG_PATH = "/resource-provider/src/main/resources/";

	public final static String CONFIG_FILE = "application.properties";

	// ICE OA登录
	public final static String ICE_SERVICE_NAME_LOGIN = "orgms/loginAccount";

	// ICE 鉴权
	public final static String ICE_SERVICE_NAME_AUTHAPP = "authms/auth/app";
	
	// ICE 鉴权1.0
	public final static String ICE_SERVICE_NAME_AUTHAPP_V1 = "jqfw/app/auth";
		

	// ICE 查询行政区
	public final static String ICE_SERVICE_NAME_REGIOMS = "regionms/administrativeDivision";

	// ICE 模糊查询用户
	public final static String ICE_SERVICE_NAME_ACCOUNT_SEARCHKEY = "orgms/account/searchKey";

	// public final static String ICE_SERVICE_NAME_UPLOAD_FILE = "/v1/wjwfw/pcUploadFile";
	
	public final static String ICE_SERVICE_NAME_NEW_UPLOAD_FILE = "newfileup/pcUploadFile";
	
	public final static String ICE_SERVICE_NAME_NEW_DOWNLOAD_FILE = "newfileup/downloadFile";
	
	// ICE 设置时间提醒
	public final static String ICE_SERVICE_NAME_ADD_TIME_REMINDER	 = "tr/timeReminder/addTimeReminder";
	
	// 发送邮件
	public final static String ICE_SERVICE_NAME_SEND_EMAIL	= 	"newmail/mail";
	
	public final static String ICE_SERVICE_NAME_ORG_SEARCH 		=  		"orgms/org/search";
	
	public final static String ICE_SERVICE_NAME_ACCOUNT_SEARCH 		=  	"orgms/account/searchKey";
	
	public final static String ICE_SERVICE_NAME_USER_AVATAR_URL		=	"http://avatar.ice.colourlife.com/avatar?uid=";
	
	public final static String ICE_SERVICE_NAME_MEMBERTAGS 		=  		"groupTag/tag/memberTags";
	
	static public final Integer MESSAGE 						= 1;
	static public final Integer FILE 						= 2;
	static public final Integer PICTURE 						= 3;
	static public final Integer AUDIO 						= 4;
	static public final Integer VUDIO 						= 5;
	
	static public final String TYPE							=			"type";
	static public final String ITEM_ID							=		"itemId";
	static public final String USER_ID							=		"userId";
	static public final String ACCOUNT_UUID							=		"accountUuid";
	static public final String DATE 									= "date";
	static public final String STAGE									= "stage";
	static public final String FILE_NAME 									= "fileName";
	static public final String MESSAGE_KEY						=		"message";
 
	static public final String INTERFACE_NAME_VISIT		= 		"visit";
	static public final String INTERFACE_NAME_EXIT		= 		"exit";
	static public final String INTERFACE_NAME_CLOSE		= 		"close";

	static public final String INTERFACE_NAME_SAVE_DATA		= 		"saveData";
	static public final String INTERFACE_NAME_GET_DATA_LIST 	=  		"getDataList";
	
	static public final String INTERFACE_NAME_SEARCH_DATA		= 		"searchData";

	static public final String BASE64_JPEG_DATA =  "data:image/jpeg;base64,";
	
	static public final String BASE64_PDF_DATA =  "data:application/pdf;base64,";
	
	static public final Integer FILE_TYPE_PICTURE = 2;
	
	static public final Integer FILE_TYPE_PDF = 3;
	
	private static String basePath = "";
	
	public static MyLoggerThread myLogger = null;
	
	
	
	//
	//
	// static public String getSuccessedProtocol(Object content) {
	//
	// String code = String.valueOf(Global.ICE_OK);
	// String message = "success";
	//
	// String jsonString = Global.getMyProtocol(code, message, false, content);
	//
	// return jsonString;
	// }
	//
	//
	// static public String getMessageProtocol(String message, boolean show) {
	//
	// String code = String.valueOf(Global.ICE_OK);
	//
	// String content = "";
	//
	// String jsonString = Global.getMyProtocol(code, message, show, content);
	//
	// return jsonString;
	// }
	//
	//
	// static public String getFailedProtocol(String message) {
	//
	// String code = String.valueOf(Global.ICE_UNKNOW);
	//
	// boolean show = true;
	//
	// String content = "";
	//
	// String jsonString = Global.getMyProtocol(code, message, show, content);
	//
	// return jsonString;
	// }
	//
	public static void setMyLogger(MyLoggerThread mylogger) {

		Global.myLogger = mylogger;
	}

	static public void initLogInfo() {
		Global.logInfoMap.put("false", "失败");
		Global.logInfoMap.put("true", "成功");
		Global.logInfoMap.put("Login", "登录");
		Global.logInfoMap.put("loginOout", "注销");
	}

	/**
	 * 说明 成功时返回协议主体包
	 * 
	 * @param content
	 * @return
	 */
	static public String getProtocol(Object content) {

		String code = String.valueOf(Global.ICE_OK);

		String message = "success";

		boolean show = false;

		return Global.getMyProtocol(code, message, show, content);
	}

	/**
	 * 说明 失败时弹窗协议返回
	 * 
	 * @author zhao
	 *
	 * @param code
	 * @param message
	 * @return
	 */

	static public String getProtocol(Integer code, String message) {

		boolean show = false;

		if (code != Global.ICE_OK) {

			show = true;
		}

		String content = "";

		return Global.getMyProtocol(String.valueOf(code), message, show, content);
	}
	
	/**
	 * 说明
	 * 
	 * 不管成功功失败，自定义是否弹窗
	 * 
	 * @param code
	 * @param message
	 * @param content
	 * @return
	 */
	static public String getWebSocketProtocol(Integer code, String message, Object content) {

		return Global.getMyProtocol(String.valueOf(code), message, content);
	}

	/**
	 * 说明
	 * 
	 * 不管成功功失败，自定义是否弹窗
	 * 
	 * @param code
	 * @param message
	 * @param show
	 * @return
	 */
	static public String getProtocol(Integer code, String message, boolean show) {

		String content = "";

		return Global.getMyProtocol(String.valueOf(code), message, show, content);
	}

	/**
	 * 
	 * 说明 完全定制
	 * 
	 * @param code
	 * @param message
	 * @param show
	 * @param content
	 * @return
	 */

	static public String getProtocol(Integer code, String message, boolean show, Object content) {

		return Global.getMyProtocol(String.valueOf(code), message, show, content);
	}

	static public String getMyProtocol(String code, String message, boolean show, Object content) {

		JSONObject jsonObject = new JSONObject();

		String jsonString = "";

		jsonObject.put(Global.PROTOCOL_TYPE_KEY, Global.PROTOCOL_TYPE_CUSTOMER_VALUE);
		jsonObject.put(Global.PROTOCOL_CODE_KEY, code);

		JSONObject messagaJsonObject = new JSONObject();
		messagaJsonObject.put(Global.PROTOCOL_MESSAGE_INFO_KEY, message);
		messagaJsonObject.put(Global.PROTOCOL_MESSAGE_SHOW_KEY, show);

		jsonObject.put(Global.PROTOCOL_MESSAGE_KEY, messagaJsonObject);

		jsonObject.put(Global.PROTOCOL_CONTENT_KEY, content);

		jsonString = jsonObject.toJSONString();
		
		return jsonString;
	}

	
	static public String getMyProtocol(String code, String message,  Object content) {

		JSONObject jsonObject = new JSONObject();

		String jsonString = "";

		jsonObject.put(Global.PROTOCOL_TYPE_KEY, Global.PROTOCOL_TYPE_CUSTOMER_VALUE);
		jsonObject.put(Global.PROTOCOL_CODE_KEY, code);

		jsonObject.put(Global.PROTOCOL_MESSAGE_KEY, message);

		jsonObject.put(Global.PROTOCOL_CONTENT_KEY, content);

		jsonString = jsonObject.toJSONString();
		
		return jsonString;
	}

	
	static public String addCutmostContent(String protocol, String key, Object value) {

		String jsonString = "";

		if (protocol != null && !protocol.trim().equals("") && key != null && !key.trim().equals("") && value != null) {

			JSONObject jsonObject = JSONObject.parseObject(protocol);
			if (jsonObject != null) {

				JSONObject contentObject = jsonObject.getJSONObject(Global.PROTOCOL_CONTENT_KEY);
				if (contentObject != null) {

					contentObject.put(key, value);
				}

			}

			jsonString = jsonObject.toJSONString();
		}

		return jsonString;
	}

	static public String cutmostMessage(String protocol, String info, boolean show) {

		String jsonString = Global.updateCustomerMessage(protocol, "show", show);

		jsonString = Global.updateCustomerMessage(jsonString, "info", info);

		return jsonString;
	}

	static public String updateIceMessage(String protocol, String value) {

		String jsonString = "";

		if (protocol != null && !protocol.trim().equals("") && value != null && !value.trim().equals("")) {

			JSONObject jsonObject = JSONObject.parseObject(protocol);
			if (jsonObject != null) {

				jsonObject.put(Global.PROTOCOL_MESSAGE_KEY, value);

			}

			jsonString = jsonObject.toJSONString();
		}

		return jsonString;
	}

	static public String updateCustomerMessage(String protocol, String key, Object value) {

		String jsonString = "";

		if (protocol != null && !protocol.trim().equals("") && value != null) {

			JSONObject jsonObject = JSONObject.parseObject(protocol);
			if (jsonObject != null) {

				JSONObject object = new JSONObject();
				object.put(key, value);

				jsonObject.put(Global.PROTOCOL_MESSAGE_KEY, object);

			}

			jsonString = jsonObject.toJSONString();
		}

		return jsonString;
	}

	static public JSONObject getIceProtocolObject(String protocol) {

		return Global.getProtocolTypeObject(protocol, Global.PROTOCOL_TYPE_ICE_VALUE);
	}

	static public JSONObject getCustomerProtocolObject(String protocol) {

		return Global.getProtocolTypeObject(protocol, Global.PROTOCOL_TYPE_CUSTOMER_VALUE);
	}

	static public String getIceProtocolInfo(String protocol) {

		return Global.getProtocolTypeInfo(protocol, Global.PROTOCOL_TYPE_ICE_VALUE);
	}

	static public String getCustomerProtocolInfo(String protocol) {

		return Global.getProtocolTypeInfo(protocol, Global.PROTOCOL_TYPE_CUSTOMER_VALUE);
	}

	static public String getProtocolTypeInfo(String protocol, String type) {

		String resultJson = "";

		if (protocol != null && !protocol.trim().equals("") && type != null && !type.trim().equals("")) {

			JSONObject jsonObject = JSONObject.parseObject(protocol);
			if (jsonObject != null) {

				jsonObject.put(Global.PROTOCOL_TYPE_KEY, type);

				resultJson = jsonObject.toJSONString();

			}
		}

		return resultJson;
	}

	static public JSONObject getProtocolTypeObject(String protocol, String type) {

		JSONObject jsonObject = null;

		if (protocol != null && !protocol.trim().equals("") && type != null && !type.trim().equals("")) {

			jsonObject = JSONObject.parseObject(protocol);
			if (jsonObject != null) {

				jsonObject.put(Global.PROTOCOL_TYPE_KEY, type);

			}
		}

		return jsonObject;
	}
	
	
	static ExecutorService getPool(String name) {
		
		return  ThreadPool.getPool(name);
		
	}

	/*
	 * 在IniData里执行配置文件读取并初始化
	 * 
	 * @authore 赵玺翔
	 * 
	 * @param basePath 项目根路径
	 *
	 * @Time 2017-11-25 15:30
	 * 
	 * @return boolean true:初始化成功 false:初始化失败，整个项目不能继续执行，需要排查配置问题
	 */
	static public boolean initConfig(String basePath) {

		// String configFilePath = basePath + CONFIG_PATH + CONFIG_FILE;

		boolean initSuccess = false;

		String configFilePath = basePath + CONFIG_PATH + CONFIG_FILE;

		logger.info("ConfigFilePath:" + configFilePath);

		initSuccess = Global.readConfig(configFilePath);

		return initSuccess;
	}

	static public String getLogInfo(String key) {

		Object o = Global.logInfoMap.get(key);

		String value = "";

		if (o != null) {

			value = o.toString();
		}

		return value;
	}

	/*
	 * 在外部提供key值, 返回相应ICE配置内容 Key配置在Global.ICE_SCYYPT....
	 * 
	 * 与Global.readConfig() 方法相对应
	 * 
	 * @authore 赵玺翔
	 * 
	 * @param String key Global.ICE_SCYYPT....
	 *
	 * @Time 2017-11-25 15:30
	 * 
	 * @return 成功则返回 Global.ICE_SCYYPT... key对应的值
	 */
	static public String getConfig(String key) {

		String value = Global.configMap.get(key);

		return value;
	}

	static public void setBasePath(String basePath) {
		Global.basePath = basePath;
	}

	static public String getBasePath() {

		return Global.basePath;
	}

	/*
	 * 在IniData里执行配置文件读取并初始化
	 * 
	 * @authore 赵玺翔
	 * 
	 * @param file 项目文件路径
	 *
	 * @Time 2017-11-25 15:30
	 * 
	 * @return boolean true:初始化成功 false:初始化失败，整个项目不能继续执行，需要排查配置问题
	 */
	static private boolean readConfig(String file) {

		Properties properties = new Properties();

		boolean ok = true;

		try {

			InputStream in = new BufferedInputStream(new FileInputStream(file));

			properties.load(in); /// 加载属性列表

			Iterator<String> it = properties.stringPropertyNames().iterator();

			while (it.hasNext()) {

				String key = it.next();
				String value = properties.getProperty(key);

				if (key.equals(Global.ICE_SCYYPT_NUMBER_KEY) == true
					|| key.equals(Global.ICE_SCYYPT_CODE_KEY) == true  
					|| key.equals(Global.ICE_SCYYPT_INFO_KEY) == true
					|| key.equals(Global.ICE_SCYYPT_TOKEN_KEY) == true
					|| key.equals(Global.ICE_SCYYPT_APPID_KEY) == true
					|| key.equals(Global.ICE_SCYYPT_CLIENT_SECRET_KEY) == true
					|| key.equals(Global.ICE_SCYYPT_APP_UUID_KEY) == true
					|| key.equals(Global.ICE_SCYYPT_APP_SECRET_KEY) == true
					|| key.equals(Global.ICE_SCYYPT_API_URL_KEY) == true
					|| key.equals(Global.ICE_SCYYPT_TIMESTAMP_URL_KEY) == true
					|| key.equals(Global.SERVER_PATH_KEY) == true
					|| key.equals(Global.ICE_SCYYPT_V1_APPKEY_KEY) == true
					|| key.equals(Global.ICE_SCYYPT_V1_APPSECRET_KEY) == true) {

					logger.info(key + ":" + value);

					Global.configMap.put(key, value);
				}
			}

			in.close();
		}

		catch (Exception e) {

			ok = false;

			logger.info(e.getLocalizedMessage());
		}

		return ok;
	}

	/*
	 * 以accountUuid为key为入参，返回对应的用户登录信息 map
	 * 
	 * 
	 * @authore 赵玺翔
	 * 
	 * @param String accountUuid 用户登录信息key
	 * 
	 *
	 * @Time 2017-11-25 15:30
	 * 
	 * @return String 返回 name （ice oa用户实际姓名)
	 */
	static public String getName(String key) {

		Map<String, Object> map = Global.getUserInfoMap(key);

		String name = "";

		if (map != null) {

			name = map.get(Global.USER_INFO_NAME).toString();
		}

		return name;
	}

	/*
	 * 以accountUuid为key为入参，返回对应的用户登录信息 map
	 * 
	 * 
	 * @authore 赵玺翔
	 * 
	 * @param String accountUuid 用户登录信息key
	 * 
	 *
	 * @Time 2017-11-25 15:30
	 * 
	 * @return String 返回 username （ice oa帐号)
	 */
	static public String getUserName(String key) {

		Map<String, Object> map = Global.getUserInfoMap(key);

		String name = "";

		if (map != null) {

			name = map.get("username").toString();
		}

		return name;
	}
	
	
	
	/*
	 * 以user为入参，返回对应的用户头像ice在线地址
	 * 
	 * 
	 * @authore 赵玺翔
	 * 
	 * @param String user 用户oa
	 * 
	 *
	 * @Time 2018-02-09 10:51
	 * 
	 * @return String 返回 oa帐号地址
	 */
	static public String getUserIcon(String user) {

		if(user == null || user.trim().equals("")) {
			
			return "";
		}
		
		String iconPath = Global.ICE_SERVICE_NAME_USER_AVATAR_URL + user;
		
		return iconPath;
	}
	
	
	

	/*
	 * 以accountUuid为key为入参，返回自身，做为统一方法提供
	 * 
	 * 
	 * @authore 赵玺翔
	 * 
	 * @param String key, accountUuid用户登录信息key
	 * 
	 *
	 * @Time 2017-11-25 15:30
	 * 
	 * @return String 返回 corpId （多租户id)
	 */
	static public String accountUuid(String key) {

		return key;
	}

	/*
	 * 以accountUuid为key为入参，返回对应的用户登录信息 map
	 * 
	 * 
	 * @authore 赵玺翔
	 * 
	 * @param String accountUuid 用户登录信息key
	 * 
	 *
	 * @Time 2017-11-25 15:30
	 * 
	 * @return String 返回 corpId （多租户id)
	 */

	static public String getUserCorpId(String key) {

		Map<String, Object> map = Global.getUserInfoMap(key);

		String name = "";

		if (map != null) {

			name = map.get("corpId").toString();
		}

		return name;
	}

	/*
	 * 以accountUuid为key为入参，返回对应的用户登录信息 map
	 * 
	 * 
	 * @authore 赵玺翔
	 * 
	 * @param String accountUuid 用户登录信息key
	 * 
	 *
	 * @Time 2017-11-25 15:30
	 * 
	 * @return String 返回 orgUuid
	 */

	static public String getUserOrgUuid(String key) {

		Map<String, Object> map = Global.getUserInfoMap(key);

		String name = "";

		if (map != null) {

			name = map.get("orgUuid").toString();
		}

		return name;
	}

	/*
	 * 以accountUuid为key为入参，返回对应的用户登录信息 map
	 * 
	 * 
	 * @authore 赵玺翔
	 * 
	 * @param String accountUuid 用户登录信息key
	 * 
	 *
	 * @Time 2017-11-25 15:30
	 * 
	 * @return Map<String, Object> 用户登录成功实际信息存储
	 */
	static private Map<String, Object> getUserInfoMap(String key) {

		if (key == null || key.trim().equals("") == true) {

			return null;
		}

		return (Map<String, Object>) Global.loginInfoMap.get(key);
	}

	/*
	 * 在/Login 接口中如果登录成功，则需要取出accountUuid为key, 员工信息 map 为value 进行用户信息保存
	 * 
	 * @authore 赵玺翔
	 * 
	 * @param String accountUuid 用户登录信息key， Map<String, Object> userInfoMap
	 * 用户登录成功实际信息存储
	 * 
	 *
	 * @Time 2017-11-25 15:30
	 * 
	 * @return
	 */
	static public void setLoginInfo(String accountUuid, Map<String, Object> userInfoMap) {

		Global.loginInfoMap.put(accountUuid, userInfoMap);
	}

	
	/*
	 * 传入accountUuid为key, 清理登录map
	 * 
	 * @authore 赵玺翔
	 * 
	 * @param String accountUuid 用户登录信息key
	 * 
	 *
	 * @Time 2017-11-25 15:30
	 * 
	 * @return
	 */
	static public void removeLoginInfo(String accountUuid) {

		Global.loginInfoMap.remove(accountUuid);
	}

	
	
	/*
	 * 以accountUuid为key, 返回accessToken
	 * 
	 * @authore 赵玺翔
	 * 
	 * @param String accountUuid 用户登录信息key
	 * 
	 * @Time 2017-12-12 00:44
	 * 
	 * @return accessToken
	 */
	static public String getAccessToken(String key) {

		String token = "";

		Map<String, Object> map = Global.getUserInfoMap(key);
		if (map != null) {

			token = map.get(Global.AUTH_INFO_ACCESS_TOKEN).toString();
		}

		return token;
	}
	
	
	/*
	 * 以accountUuid为key, 返回accessTokenV1
	 * 
	 * @authore 赵玺翔
	 * 
	 * @param String accountUuid 用户登录信息key
	 * 
	 * @Time 2017-12-12 00:44
	 * 
	 * @return accessToken
	 */
	static public String getAccessTokenV1(String key) {

		String token = "";

		Map<String, Object> map = Global.getUserInfoMap(key);
		if (map != null) {

			token = map.get(Global.AUTH_INFO_ACCESS_TOKEN_V1).toString();
		}

		return token;
	}

//	/*
//	 * 在/Auth 接口中如果鉴权成功，则需要以accountUuid为key, accessToken 为value
//	 * 进行accessToken信息保存
//	 * 
//	 * @authore 赵玺翔
//	 * 
//	 * @param String accountUuid 用户登录信息key， String accessToken
//	 * 
//	 * @Time 2017-12-12 00:44
//	 * 
//	 * @return
//	 */
//	static public void setAccessToken(String accountUuid, String accessToken) {
//
//		if (!Global.isNull(accountUuid) && !Global.isNull(accessToken)) {
//
//			Global.authInfoMap.put(accountUuid, accessToken);
//		}
//	}
	

//	/*
//	 * 在/Auth 接口中如果鉴权成功，则需要以accountUuid为key, accessToken 为value
//	 * 进行accessToken信息保存
//	 * 
//	 * @authore 赵玺翔
//	 * 
//	 * @param String accountUuid 用户登录信息key， String accessToken
//	 * 
//	 * @Time 2017-12-12 00:44
//	 * 
//	 * @return
//	 */
//	static public void setAccessTokenV1(String accountUuid, String accessToken) {
//
//		if (!Global.isNull(accountUuid) && !Global.isNull(accessToken)) {
//
//			Global.authInfoMap.put(accountUuid, accessToken);
//		}
//	}
//	
	

	static public JSONObject getJSONObject(String jsonString) {

		JSONObject jsonObject = null;

		if (jsonString != null && !jsonString.trim().equals("")) {

			jsonObject = JSONObject.parseObject(jsonString);
		}

		return jsonObject;
	}

	/*
	 * 以jsonObject为入参，返回ICE请求的信息码 code = 0 为成功
	 * 
	 * 
	 * @authore 赵玺翔
	 * 
	 * @param JSONObject jsonObject ICE请求返回信息json解析对象
	 * 
	 *
	 * @Time 2017-12-9 21:00
	 * 
	 * @return Integer 返回 code
	 */
	static public Integer getICECode(JSONObject jsonObject) {

		int code = Global.ICE_UNKNOW;

		if (jsonObject != null) {

			String c = jsonObject.getString("code");
			if (c != null && c.trim().equals("") == false) {

				code = Integer.valueOf(c);
			}

		}

		return code;
	}

	/*
	 * 以jsonObject为入参，返回ICE请求的信息
	 * 
	 * 
	 * @authore 赵玺翔
	 * 
	 * @param JSONObject jsonObject ICE请求返回信息json解析对象
	 * 
	 *
	 * @Time 2017-12-9 21:00
	 * 
	 * @return String 返回 message 主是错误提示信
	 */
	static public String getICEMessage(JSONObject jsonObject) {

		String message = "";

		if (jsonObject != null) {

			message = jsonObject.getString("message");
		}

		return message;
	}

	/*
	 * 以jsonObject为入参，返回ICE请求的实际数据内容
	 * 
	 * 
	 * @authore 赵玺翔
	 * 
	 * @param JSONObject jsonObject ICE请求返回信息json解析对象
	 * 
	 *
	 * @Time 2017-12-9 21:00
	 * 
	 * @return String 返回 content 主体信息json串
	 */
	static public String getICEContent(JSONObject jsonObject) {

		String content = "";

		if (jsonObject != null) {

			content = jsonObject.getString("content");
		}

		return content;
	}

	/*
	 * 以jsonString为入参，返回ICE请求的信息码 code = 0 为成功
	 * 
	 * 
	 * @authore 赵玺翔
	 * 
	 * @param String jsonString ICE请求返回信息jsonString
	 * 
	 *
	 * @Time 2017-12-9 21:00
	 * 
	 * @return Integer 返回 code
	 */
	static public Integer getICECode(String jsonString) {

		int code = Global.ICE_UNKNOW;

		JSONObject jsonObject = Global.getJSONObject(jsonString);

		if (jsonObject != null) {

			String c = jsonObject.getString("code");
			if (c != null && c.trim().equals("") == false) {

				code = Integer.valueOf(c);
			}
		}

		return code;
	}

	/*
	 * 以jsonString为入参，返回ICE请求的错误提示信息
	 * 
	 * 
	 * @authore 赵玺翔
	 * 
	 * @param String jsonString ICE请求返回信息json串
	 * 
	 *
	 * @Time 2017-12-9 21:00
	 * 
	 * @return String 返回 message 主是错误提示信
	 */
	static public String getICEMessage(String jsonString) {

		String message = "";

		JSONObject jsonObject = Global.getJSONObject(jsonString);

		if (jsonObject != null) {

			message = jsonObject.getString("message");
		}

		return message;
	}

	/*
	 * 以jsonString为入参，返回ICE请求的实际数据json串
	 * 
	 * 
	 * @authore 赵玺翔
	 * 
	 * @param String jsonString ICE请求返回信息json串
	 * 
	 *
	 * @Time 2017-12-9 21:00
	 * 
	 * @return String 返回 content 主体数据json串
	 */
	static public String getICEContent(String jsonString) {

		String content = "";

		JSONObject jsonObject = Global.getJSONObject(jsonString);

		if (jsonObject != null) {

			content = jsonObject.getString("content");
		}

		return content;
	}

	/*
	 * 以jsonObject为入参，返回ICE请求的实际数据map
	 * 
	 * 
	 * @authore 赵玺翔
	 * 
	 * @param String jsonString ICE请求返回信息json串
	 * 
	 *
	 * @Time 2017-12-9 21:00
	 * 
	 * @return Map<String, Object> 返回 content 主体数据map
	 */
	static public Map<String, Object> getContentMap(JSONObject jsonObject) {

		String content = "";

		if (jsonObject != null) {

			content = jsonObject.getString("content");
		}

		Map<String, Object> contentMap = null;

		if (Global.isNull(content) == false) {

			contentMap = JSONObject.parseObject(content);
		}

		return contentMap;
	}

	/*
	 * 以jsonString为入参，返回ICE请求的实际数据map
	 * 
	 * 
	 * @authore 赵玺翔
	 * 
	 * @param String jsonString ICE请求返回信息json串
	 * 
	 *
	 * @Time 2017-12-9 21:00
	 * 
	 * @return Map<String, Object> 返回 content 主体数据map
	 */
	static public Map<String, Object> getContentMap(String jsonString) {

		Map<String, Object> contentMap = null;

		if (Global.isNull(jsonString) == false) {

			contentMap = JSONObject.parseObject(jsonString);
		}

		return contentMap;
	}

	/*
	 * 以jsonString为入参，返回ICE请求的实际数据json对象
	 * 
	 * 
	 * @authore 赵玺翔
	 * 
	 * @param String jsonString ICE请求返回信息json串
	 * 
	 *
	 * @Time 2017-12-9 21:00
	 * 
	 * @return JSONObject 返回 content 主体数据json对象
	 */
	static public JSONObject getContentObject(String jsonString) {

		// String content = Global.getICEContent(jsonString);

		JSONObject contentObject = null;
		if (jsonString != null && jsonString.trim().equals("") == false) {

			contentObject = Global.getJSONObject(jsonString);
		}

		return contentObject;
	}

	/*
	 * 要求代码严谨， 返回对象要判空，帮尝试写一个统一判空方法 如果存在问题，请后续同事改，或完善
	 * 
	 * @authore 赵玺翔
	 * 
	 * @param Object object
	 * 
	 *
	 * @Time 2017-12-9 21:00
	 * 
	 * @return boolean true:空 false:非空
	 */

	static public boolean isNull(Object object) {

		boolean isNull = false;
		if (object == null) {

			isNull = true;
		} else {

			if (object.getClass().equals(String.class) == true) {

				if (object.toString().equals("") == true) {

					isNull = true;
				}
			}

		}

		return isNull;
	}

	/*
	 * ICE数据请求返回验证
	 * 
	 * @authore 赵玺翔
	 * 
	 * @param String jsonString
	 * 
	 *
	 * @Time 2017-12-9 21:00
	 * 
	 * @return boolean true:成功 （code == 0) false: 错值值
	 */

	static public boolean isICESuccessed(String jsonString) {

		boolean isSuccessed = false;
		int code = Global.getICECode(jsonString);

		if (code == Global.ICE_OK) {

			isSuccessed = true;
		}

		return isSuccessed;
	}

	/*
	 * ICE数据请求返回验证
	 * 
	 * @authore 赵玺翔
	 * 
	 * @param JSONObject jsonObject
	 * 
	 *
	 * @Time 2017-12-9 21:00
	 * 
	 * @return boolean true:成功 （code == 0) false: 错值值
	 */

	static public boolean isICESuccessed(JSONObject jsonObject) {

		boolean isSuccessed = false;
		int code = Global.getICECode(jsonObject);

		if (code == Global.ICE_OK) {

			isSuccessed = true;
		}

		return isSuccessed;
	}
	
	
	public static String getServerIp(){
			
		String ip = Global.getConfig(Global.SERVER_PATH_KEY);
  
		String a [] = ip.split("://");
		
		int len = a.length;
		
		String b = "";
		if(len == 2) {
			
			b = a[1];
		}
		
		String c[] = b.split(":");
		
		len = c.length;
		
		if(len > 1) {
			
			ip = c[0];
		}
		
		return ip;
     }

}
