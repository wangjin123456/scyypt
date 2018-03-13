package com.scyypt.service;

import com.scyypt.entity.URAProvider;

/**
 * 
 * @Description: 
 *
 * @Author 程传平
 *
 * @Time   2018-01-23 23:20
 *
 */
public interface URAProviderService {

	/**
	 * 新增用户，角色，权限关系
	 * 
	 * @Author 程传平
	 * @Time 2018-01-23 23:18
	 *
	 * @param userRoleAuthorityProvider
	 *            用户，角色，权限对象
	 * @return 大于0成功，等于0失败
	 */
	public int addProvider(URAProvider uRAProvider);
	
	/**
	 * 修改用户权限
	 * 
	 * @Author 程传平
	 * @Time 2018-01-24 21:55
	 *
	 * @param uRAProvider
	 *            用户，角色，权限对象
	 * @return 大于0成功，等于0失败
	 */
	public int reviseAuthority(URAProvider uRAProvider);
	
	/**
	 * 根据角色编号，功能模块编号，修改操作权限
	 * 
	 * @Author 程传平
	 * @Time 2018-01-27 22:14
	 *
	 * @param uRAProvider
	 * @return
	 */
	public Integer updateAuthority(URAProvider uRAProvider);
}
