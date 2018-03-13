package com.scyypt.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public interface ICEInterfaceService {

	public String doLogin(String user, String pwd, String corpUuid);

	public String doAuth(String corpUuid);

	public String doAuthV1();

	public String querySuperUser(String accessToken, String user, Integer pageIndex);
	
	public String getRegioms(String accountUuid, String pid);

	public String doUserSearchKey(String accountUuid, String skey);

	public String uploadFile(String accountUuid, CommonsMultipartFile cfile);

	public String uploadFile(String accountUuid, String filePath);

	public String downloadFile(String accountUuid, String fileId);

	// public String setTimeReminder(String accountUuid, String oaList, String
	// time, String days, String body);

	// public String setTimeReminder(String oaList, String time, String days, String body);

	public String setTimeReminder(String oaList, String time, String days, String body, String corpId, String user, String name);
	
	public String sendEmail(String recipients, String body, String corpId, String username, String name);

	/**
	 * 获取人员列表
	 * 
	 * @param accountUuid
	 *            用户编号
	 * @param orgUuid
	 *            父级Id
	 * @return
	 */
	public String getOrgAgents(String accountUuid, String orgUuid);

	/**
	 * 获取管理组织架构
	 * 
	 * @Auth 程传平
	 * @Time 2018年1月21日下午2:27:16
	 *
	 * @param accountUuid
	 *            用户编号
	 * @param orgUuid
	 *            父级Id
	 * @return
	 */
	public String getOrgList(String accountUuid, String orgUuid);

}
