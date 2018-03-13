package com.scyypt.service;

import com.scyypt.bean.UserInfoBean;

public interface InterfaceService {

	
	public String doLogin(String user, String pwd, String corpUuid);
	
	public String doAuth(String corpUuid);

	public String doAuthV1();
	
	public String doRegister(UserInfoBean userInfoBean);
	
}
