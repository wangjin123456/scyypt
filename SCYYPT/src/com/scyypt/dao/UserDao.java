package com.scyypt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.scyypt.entity.UserEntity;

public interface UserDao {
	/**
	 * 根据用户名和用户编号查询
	 * 
	 * @param userInfoEntity
	 *            用户实体对象
	 * @return 用用集合
	 */
	public List<UserEntity> queryUserinfo(UserEntity userEntity);

	public List<UserEntity> searchUser(UserEntity userEntity);

	/**
	 * 新增用户信息
	 * 
	 * @param userInfoEntity
	 *            用户对象
	 * @return 大于0成功,等于0失败
	 */
	public Integer addUsers(UserEntity userEntity);

	/**
	 * 根据父级id查询用户信息
	 * 
	 * @Author 程传平
	 * @Time 2018-01-23 09:52
	 *
	 * @param orgUuid
	 * @return
	 */
	public List<UserEntity> showUsers(@Param("orgUuid") String orgUuid);

	/**
	 * 根据用户编号修改用户角色
	 * 
	 * @Author 程传平
	 * @Time 2018-01-27 13:46
	 *
	 * @param userEntity
	 * @return
	 */
	public Integer updateUser(UserEntity userEntity);

}
