package com.scyypt.service.impl;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.scyypt.service.ICEInterfaceService;
import com.scyypt.service.ICERequestService;
import com.scyypt.util.Global;
import com.scyypt.util.Md5Util;
import com.scyypt.util.TimestampUtil;

@Service("ICEInterfaceService")
public class ICEInterfaceServiceImpl implements ICEInterfaceService {

	private static Logger logger = Logger.getLogger(ICEInterfaceServiceImpl.class.getName());

	@Autowired
	ICERequestService iceRequestService;

	@Override
	public String doLogin(String user, String pwd, String accountUuid) {

		HashMap<String, String> paramMap = new HashMap<String, String>();

		paramMap.put("username", user);
		paramMap.put("password", pwd);

		if (accountUuid != null) {

			String corpUuid = Global.getUserCorpId(accountUuid);

			if (corpUuid != null && corpUuid.trim().equals("") == false) {

				paramMap.put("corpUuid", corpUuid);
			}
		}

		String result = this.iceRequestService.doPostRequest(paramMap, Global.ICE_SERVICE_NAME_LOGIN);

		return result;
	}

	@Override
	public String doAuth(String corpUuid) {

		String appUuid = Global.getConfig(Global.ICE_SCYYPT_APP_UUID_KEY);

		String timestamp = TimestampUtil.getTs();

		// form表单参数
		HashMap<String, String> paramMap = new HashMap<>();
		paramMap.put("timestamp", timestamp);
		paramMap.put("app_uuid", appUuid);
		paramMap.put("signature", Md5Util.signatureMd5(timestamp));
		paramMap.put("corp_uuid", corpUuid);

		String jsonResult = this.iceRequestService.doPostRequest(paramMap, Global.ICE_SERVICE_NAME_AUTHAPP);

		return jsonResult;
	}

	@Override
	public String doAuthV1() {

		String appKey = Global.getConfig(Global.ICE_SCYYPT_V1_APPKEY_KEY);
		String appSecretKey = Global.getConfig(Global.ICE_SCYYPT_V1_APPSECRET_KEY);

		String timestamp = TimestampUtil.getTs();

		HashMap<String, String> paramMap = new HashMap<>();

		paramMap.put("appkey", appKey);
		paramMap.put("timestamp", timestamp);

		String signatrue = Md5Util.signatureMd5(appKey, timestamp, appSecretKey);
		paramMap.put("signature", signatrue);

		String jsonResult = this.iceRequestService.doPostRequestByAuthV1(paramMap, Global.ICE_SERVICE_NAME_AUTHAPP_V1);

		return jsonResult;
	}

	@Override
	public String doUserSearchKey(String accountUuid, String skey) {

		Map<String, String> param = new HashMap<String, String>();
		String token = Global.getAccessToken(accountUuid);
		param.put("keyword", skey);
		param.put("token", token);

		String result = this.iceRequestService.doGetRequest(param, Global.ICE_SERVICE_NAME_ACCOUNT_SEARCHKEY);

		String protocol = Global.getIceProtocolInfo(result);

		return protocol;
	}

	@Override
	public String querySuperUser(String accessToken, String user, Integer pageIndex) {

		// 封装 请求参数
		Map<String, String> param = new HashMap<String, String>();
		
		param.put("member", user);
		param.put("token", accessToken);
		param.put("pageSize", "100");
		param.put("currentPage", String.valueOf(pageIndex));
		
		String result = this.iceRequestService.doGetRequest(param, Global.ICE_SERVICE_NAME_MEMBERTAGS);

		String protocol = Global.getIceProtocolInfo(result);

		return protocol;
	}
	
	
	@Override
	public String getRegioms(String accountUuid, String pid) {

		String result = "";

		if (Global.isNull(pid)) {

			String message = "行政区ID为空,可能前端传参错误!";

			result = Global.getProtocol(Global.ICE_UNKNOW, message);

			return result;
		}

		if (Global.isNull(accountUuid)) {

			String message = "用户标识ID为空, 后台程序不能继续运行!";

			result = Global.getProtocol(Global.ICE_UNKNOW, message);

			return result;
		}

		String token = Global.getAccessToken(accountUuid);

		if (Global.isNull(token)) {

			String message = "token为空,后台程序不能继续运行!";

			result = Global.getProtocol(Global.ICE_UNKNOW, message);

			return result;
		}

		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("token", token);
		paramMap.put("pid", pid);

		String iceJson = this.iceRequestService.doGetRequest(paramMap, Global.ICE_SERVICE_NAME_REGIOMS);

		String iceProtocol = Global.getIceProtocolInfo(iceJson);

		return iceProtocol;
	}

	@Override
	public String downloadFile(String accountUuid, String fileId) {

		String resultString = "";

		String token = Global.getAccessTokenV1(accountUuid);

		if (Global.isNull(token)) {

			String message = "token为空,后台程序不能继续运行!";

			resultString = Global.getProtocol(Global.ICE_UNKNOW, message);

			return resultString;
		}

		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("access_token", token);
		paramMap.put("fileId", fileId);

		String iceJson = this.iceRequestService.doGetRequest(paramMap, Global.ICE_SERVICE_NAME_NEW_DOWNLOAD_FILE);

		String iceProtocol = Global.getIceProtocolInfo(iceJson);

		return iceProtocol;

	}

	@Override
	public String uploadFile(String accountUuid, CommonsMultipartFile cfile) {

		String fileName = cfile.getOriginalFilename();

		DiskFileItem fileItem = (DiskFileItem) cfile.getFileItem();

		File oldFile = fileItem.getStoreLocation();

		String filePath = oldFile.getAbsolutePath();

		String path = FilenameUtils.getFullPath(filePath);

		String newPath = path + fileName;

		File newFile = new File(newPath);

		boolean ok = oldFile.renameTo(newFile);
		if (ok == true) {

		}

		return this.uploadFile(accountUuid, newPath);
	}

	@Override
	public String uploadFile(String accountUuid, String filePath) {

		String resultString = "";

		String token = Global.getAccessTokenV1(accountUuid);

		String account = Global.getUserName(accountUuid);

		if (Global.isNull(token)) {

			String message = "token为空,后台程序不能继续运行!";

			resultString = Global.getProtocol(Global.ICE_UNKNOW, message);

			return resultString;
		}

		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("token", token);
		paramMap.put("account", account);
		paramMap.put("filePath", filePath);

		String iceJson = this.iceRequestService.doUploadFileRequest(paramMap, Global.ICE_SERVICE_NAME_NEW_UPLOAD_FILE);

		String iceProtocol = Global.getIceProtocolInfo(iceJson);

		return iceProtocol;
	}

	////////////////////////////////////////////// 时间提醒微服务///////////////////////////////////////////
	@Override
	public String setTimeReminder(String oaList, String time, String days, String body, String corpId, String user, String name)  {

		String result = "";

		if (Global.isNull(oaList)) {

			String message = "接收邮件的oa帐号列表为空,可能前端传参错误!";

			result = Global.getProtocol(Global.ICE_UNKNOW, message);

			return result;
		}

		if (Global.isNull(time)) {

			String message = "时间提醒触发点为空, 后台程序不能继续运行!";

			result = Global.getProtocol(Global.ICE_UNKNOW, message);

			return result;
		}

		if (Global.isNull(days)) {

			String message = "时间提醒天数为空,后台程序不能继续运行!";

			result = Global.getProtocol(Global.ICE_UNKNOW, message);

			return result;
		}

		if (Global.isNull(body)) {

			String message = "时间提醒正文为空,后台程序不能继续运行!";

			result = Global.getProtocol(Global.ICE_UNKNOW, message);

			return result;
		}

		try {
			
			oaList = URLEncoder.encode(oaList, "utf-8");
			body = URLEncoder.encode(body, "utf-8");
			corpId = URLEncoder.encode(corpId, "utf-8");
			user = URLEncoder.encode(user, "utf-8");
			name = URLEncoder.encode(name, "utf-8");
			
		} catch(Exception e) {
			
			e.printStackTrace();
		
			String message = "回调地址参数编码产生异常，详细信息：" + e.getLocalizedMessage();
			
			String jsonResult = Global.getProtocol(Global.ICE_UNKNOW, message);
			
			return jsonResult;
		}
		
		String params = "oaList="+ oaList + "&body=" + body + "&corpId=" + corpId + "&user=" + user + "&name=" + name;
		
		
		try {
			
			params = URLEncoder.encode(params, "utf-8");
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
				
		String serverURL = Global.getConfig(Global.SERVER_PATH_KEY);

		String callbackURL = serverURL+ "/doTimeReminderCallback?" + params;
		
		logger.info("setTimeReminder 回调地址:" + callbackURL);
		
		HashMap<String, String> paramMap = new HashMap<String, String>();

		// 时间点(支持到"分") 格式 : yyyy-MM-dd HH:mm:ss
		paramMap.put("timepoint", time);

		// 提醒方式[+之后，-之前]
		paramMap.put("remindway", "-");

		paramMap.put("quantity", days);

		paramMap.put("unit", "m");
		paramMap.put("callbackurl", callbackURL);
		paramMap.put("circletimes", "0");
		paramMap.put("circleinterval", "0");
		paramMap.put("circleunit", "m");

		String iceJson = this.iceRequestService.doGetRequest(paramMap, Global.ICE_SERVICE_NAME_ADD_TIME_REMINDER);

		String iceProtocol = Global.getIceProtocolInfo(iceJson);

		return iceProtocol;
	}

	////////////////////////////////////////////// 发邮件微服务///////////////////////////////////////////
	@Override
	public String sendEmail(String recipients, String body, String corpId, String username, String name) {

		String result = "";

		if (Global.isNull(recipients)) {

			String message = "接收邮件的帐号列表为空,可能是传参错误!";

			result = Global.getProtocol(Global.ICE_UNKNOW, message);

			return result;
		}

		if (Global.isNull(body)) {

			String message = "邮件正文为空, 后台程序不能继续运行!";

			result = Global.getProtocol(Global.ICE_UNKNOW, message);

			return result;
		}

		if (Global.isNull(corpId)) {

			String message = "租户ID为空,后台程序不能继续运行!";

			result = Global.getProtocol(Global.ICE_UNKNOW, message);

			return result;
		}

		if (Global.isNull(username)) {

			String message = "OA帐号为空,后台程序不能继续运行!";

			result = Global.getProtocol(Global.ICE_UNKNOW, message);

			return result;
		}

		if (Global.isNull(name)) {

			String message = "真实 名称为空,后台程序不能继续运行!";

			result = Global.getProtocol(Global.ICE_UNKNOW, message);

			return result;
		}

		// 封装请求参数
		Map<String, String> paramMap = new HashMap<>();

		paramMap.put("title", "提醒邮件");

		paramMap.put("body", body);

		// 是否存为草稿0否1是.
		paramMap.put("isdraft", "0");

		// 附件数量
		paramMap.put("accessorySum", "0");

		// 附件信息json格式
		paramMap.put("accessory", "");

		paramMap.put("recipients", recipients);

		paramMap.put("corpid", corpId);

		paramMap.put("founder", username);

		paramMap.put("foundername", name);

		String iceJson = this.iceRequestService.doPostRequest(paramMap, Global.ICE_SERVICE_NAME_SEND_EMAIL);

		String iceProtocol = Global.getIceProtocolInfo(iceJson);

		return iceProtocol;
	}

	// @Override
	// public String selectEmployee(String accessToken, String keyword) {
	//
	// return this.doUserSearchKey(accountUuid, skey);
	//
	// }

	//
	// @Override
	// public String selectEmployee(String accessToken, String keyword) {
	// String appId = Global.getConfig(Global.ICE_SCYYPT_APPID_KEY);
	//
	// String api = Global.getConfig(Global.ICE_SCYYPT_API_URL_KEY);
	// String url = api +"orgms/account/searchKey";
	//
	// String token = accessToken;
	// String ts = TimestampUtil.getTs();
	// String sign = Md5Util.signMd5(ts);
	// // 封装请求参数
	// Map<String,String> param = new HashMap<>();
	// param.put("keyword", keyword);
	// param.put("ts", ts);
	// param.put("sign", sign);
	// param.put("appID", appId);
	// param.put("token", token);
	// param.put("pageSize", "30");
	// param.put("pageIndex", "1");
	//
	// // 发起get请求
	// String jsonResult = IOkHttpUtil.sendGet(url, param);
	// return jsonResult;
	//
	// }

	@Override
	public String getOrgList(String accountUuid, String orgUuid) {

		String token = Global.getAccessToken(accountUuid);

		return this.getOrgList(token, orgUuid, Global.STRUCTURE_TYPE_ORG);
	}

	@Override
	public String getOrgAgents(String accountUuid, String orgUuid) {

		HashMap<String, String> paramMap = new HashMap<>();

		String token = Global.getAccessToken(accountUuid);

		paramMap.put("token", token);
		paramMap.put("orgUuid", orgUuid);

		paramMap.put("pageSize", "300");

		String iceJson = this.iceRequestService.doGetRequest(paramMap, Global.ICE_SERVICE_NAME_ACCOUNT_SEARCHKEY);

		JSONObject jsonObject = JSONObject.parseObject(iceJson);

		// 需要进行树封装的数据 集合
		JSONArray content = jsonObject.getJSONArray("content");

		return content.toJSONString();
	}

	/**
	 * 获取所有类型组织架构
	 * 
	 * @param accessToken
	 * @param parentId
	 * @param familyTypeId
	 * @return
	 */
	public String getOrgList(String accessToken, String parentId, String familyTypeId) {

		if (parentId == null || parentId.trim().equals("")) {

			parentId = "0";
		}

		// 获取所有的数据 totalRecord
		HashMap<String, String> paramMap = new HashMap<>();

		paramMap.put("token", accessToken);

		paramMap.put("familyTypeId", familyTypeId);// 0组织架构、1客户架构、2法人架构、3供方架构、4国家架构

		paramMap.put("parentId", parentId);

		String iceJson = this.iceRequestService.doGetRequest(paramMap, Global.ICE_SERVICE_NAME_ORG_SEARCH);

		JSONObject jsonObject = JSONObject.parseObject(iceJson);

		// pageSize 页大小
		// totalPage 总页
		// currentPage 当前页面
		// totalRecord 总记录

		Integer totalPage = jsonObject.getInteger("totalPage");
		Integer totalRecord = jsonObject.getInteger("totalRecord");

		// 大于一个页面的数据
		if (totalPage > 1) {

			paramMap.put("pageSize", String.valueOf(totalRecord));

			iceJson = this.iceRequestService.doGetRequest(paramMap, Global.ICE_SERVICE_NAME_ORG_SEARCH);
		}
		
		System.out.println(iceJson);
		
		jsonObject = JSONObject.parseObject(iceJson);

		String resultString = "";

		JSONArray content = jsonObject.getJSONArray("content");

		if (content == null) {

			return resultString;
		}

		int len = content.size();

		JSONArray resultList = new JSONArray();

		for (int index = 0; index < len; index++) {

			JSONObject contentObject = content.getJSONObject(index);

			if (contentObject != null) {

				JSONObject newObject = new JSONObject();

				String uuid = contentObject.getString("orgUuid");
				String name = contentObject.getString("name");
				String pid = contentObject.getString("parentId");
				String status = contentObject.getString("status");

				// logger.info("Orguuid:" + uuid + " name:" + name + "
				// parentId:" + pid + "status:" + status);

				newObject.put("orgUuid", uuid);
				newObject.put("status", status);
				newObject.put("name", name);
				newObject.put("parentId", pid);
				newObject.put("isParent", true);

				resultList.add(newObject);
			}

		}

		resultString = resultList.toJSONString();

		return resultString;
	}

}
