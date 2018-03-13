package com.scyypt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scyypt.bean.UserInfoBean;
import com.scyypt.service.ICEInterfaceService;
import com.scyypt.service.InterfaceService;
import com.scyypt.util.Global;

@Service("InterfaceService")
public class InterfaceServiceImpl implements InterfaceService {

	
	
	@Autowired
	ICEInterfaceService iceInterfaceService;
	
	@Override
	public String doLogin(String user, String pwd, String corpUuid) {

		String iceJson = this.iceInterfaceService.doLogin(user, pwd, corpUuid);
		
		String protocol = Global.getIceProtocolInfo(iceJson);
		
		return protocol;
 	}


	@Override
	public String doAuth(String corpUuid) {

		String iceJson =  this.iceInterfaceService.doAuth(corpUuid);
		
		String protocol = Global.getIceProtocolInfo(iceJson);
		
		return protocol;
 	}


	@Override
	public String doAuthV1() {
		 
		String iceJson =  this.iceInterfaceService.doAuthV1();
		
		String protocol = Global.getIceProtocolInfo(iceJson);
		
		return protocol;
	}
	
	
	
	
	@Override
	public String doRegister(UserInfoBean userInfoBean) {
		
		return null;
	}


}
