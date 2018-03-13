package com.scyypt.service;

public interface LoginService {

	/**
	 * 
	 * @param uer
	 *            账户信息
	 * @return 返回处理结果信息
	 */
	public boolean checkZZXTSupperUser(String accessToken, String user);
	
}
