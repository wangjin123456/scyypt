package com.scyypt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.scyypt.entity.Operation;

/**
 * 菜单功能操作数据接口
 * 
 * @Description:
 *
 * @Author 程传平
 *
 * @Time 2018-01-26 19:50
 *
 */
public interface OperationDao {
	/**
	 * 获取菜单功能操作
	 * 
	 * @Author 程传平
	 * @Time 2018-01-26 19:51
	 *
	 * @return
	 */
	public List<Operation> findOperation();

	/**
	 * 根据角色编号和模块编号查询操作
	 * 
	 * @Author 程传平
	 * @Time 2018-01-27 20:32
	 *
	 * @param roleId
	 *            角色编号
	 * @param authorityId
	 *            模块编号
	 * @return 操作对象集合
	 */
	public List<Operation> queryOperation(@Param("roleId")String roleId,@Param("authorityId") String authorityId);

}
