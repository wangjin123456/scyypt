package com.scyypt.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.scyypt.service.ICEInterfaceService;
import com.scyypt.service.LoginService;
import com.scyypt.util.Global;

@Service("LoginService")
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	ICEInterfaceService iceInterfaceService;
	
	private static Logger logger = Logger.getLogger(ICEInterfaceServiceImpl.class.getName());

	
	// 递归查询
	public boolean bySelfCheckZZXTSupperUser(String accessToken, String user, Integer index) {
		
		String jsonResult =  iceInterfaceService.querySuperUser(accessToken, user, index);
		
		if(jsonResult == null || jsonResult.trim().equals("") == true) {
			
			return false;
		}
		 
		logger.info("ZZXT SupperUser INFO :" + jsonResult);
		
		JSONObject jsonObject = JSONObject.parseObject(jsonResult);
		if(jsonObject == null) {
			
			return false;
		}
				
		int success = Integer.valueOf(jsonObject.getString("code").toString());
		
		if(success != Global.ICE_OK) {
			
			return false;
		}
		
		JSONArray contentList = jsonObject.getJSONArray("content");

		int len = contentList.size();
		
		for(int c = 0; c < len; c ++) {
			
			JSONObject contentObject = contentList.getJSONObject(c);
			if(contentObject != null) {
				
				int id = contentObject.getIntValue("id");
				
				String number = Global.getConfig(Global.ICE_SCYYPT_NUMBER_KEY);
				 
				if(id == Integer.valueOf(number).intValue()) {
					
					return true;
				}
			}
		}
		
		
		String  psize = jsonObject.getString("pageSize").toString();
		
		logger.info("checkZZXTSupperUser pageInfo-> pageSize:" + psize);

		String  tpage = jsonObject.getString("totalPage").toString();

		logger.info("checkZZXTSupperUser pageInfo-> totalPage:" + tpage);

		logger.info("checkZZXTSupperUser pageInfo-> currentPage(pageIndex):" + index);

		
		Integer totalPage = Integer.valueOf(tpage);
		
		for(int i = index + 1; i < totalPage; i ++) {
		
			return this.bySelfCheckZZXTSupperUser(accessToken, user, i);
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param uer
	 *            账户信息
	 * @return 返回处理结果信息
	 */
	@Override
	public boolean checkZZXTSupperUser(String accessToken, String user) {
	
		return this.bySelfCheckZZXTSupperUser(accessToken, user, 1);
	}

	
	
//
//	/*
//	 * @Auth 休闲
//	 * @Date 2017-11-26 12:57
//	 * @Param accountUuid
//	 * @Desc  获取用户权限信息，分子极权限列表，证照信息：1 电子签章：2  证照信息和电子签章：3 
//	 */
//	
//	@Override
//	public Map<String, Object> userPowerInfo(String accessToken, String accountUuid) {
//		
//		Map<String, Object> resultMap = new HashMap<>();
//		
//		Map<String, Object> powerInfoMap = new HashMap<>();
//		
//		// 法人架构列表
//		List<LegalPersonUserEntity> list = legalPersonUserDao.legalPersonUserList(accountUuid);
// 		
//		String orgUuids = "";
//		for(LegalPersonUserEntity legalPersonUser : list) {
//			
//			String orgUuid = legalPersonUser.getOrgUuid();
//			orgUuids += orgUuid;
//			orgUuids += ",";
//			
//			Map<String, Object> powerValueMap = new HashMap<>();
//			int sub = legalPersonUser.getSub();
//			int power = legalPersonUser.getPower();
//			
//			powerValueMap.put("sub", sub);
//			powerValueMap.put("power", power);
//			
//			powerInfoMap.put(orgUuid, powerValueMap);
//						
//		}
//		
//		if(orgUuids.trim().equals("") == false) {
//			
//			orgUuids = orgUuids.substring(0, orgUuids.length() - 1);
//		}
//		
//		String legalPersons = organizationServiceHttp.getUserOrgList(accessToken, orgUuids);
//		
//		logger.info("\n---------一般用户证照树形列表--------\n" + legalPersons);
//		
//		logger.info("\n---------结束--------\n");
//		
//		resultMap.put("legalPersons", legalPersons);
//		resultMap.put("powerInfo", powerInfoMap);
//		resultMap.put("super", "false");
//		
//		logger.info("一般用户权限信息返回:" + resultMap.toString());
//		
//		return resultMap;
//	}
//	
//	@Override
//	public Map<String, Object> superUserPowerInfo(String accessToken, String orgUuid) {
//		
//		Map<String, Object> resultMap = new HashMap<>();
// 
//		String jsonResult = organizationServiceHttp.getOrgTresJson(accessToken, orgUuid, Global.STRUCTURE_TYPE_LEGALPERSON);
//		
//		logger.info("\n---------超级管理员证照树形列表--------\n" + jsonResult);
//		
//		logger.info("\n---------结束--------\n");
//		
//		
//		Map<String, Object> powerInfoMap = new HashMap<>();
//		
//		Map<String, Object> powerValueMap = new HashMap<>();
//		
//		powerValueMap.put("sub", "1");
//		powerValueMap.put("power", "3");
//		powerInfoMap.put("superUser", powerValueMap);
//		
//		resultMap.put("legalPersons", jsonResult);
//		resultMap.put("powerInfo", powerInfoMap);
//		resultMap.put("super", "true");
//		return resultMap;
//	}
//	

}
