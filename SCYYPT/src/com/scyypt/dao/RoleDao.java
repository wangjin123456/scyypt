package com.scyypt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.scyypt.entity.Role;

/**
 * 角色信息数据接口
 * 
 * @Description:
 *
 * @Author 程传平
 *
 * @Time 2018-01-22 11:42
 *
 */
public interface RoleDao {

	/**
	 * 获取所有角色信息
	 * 
	 * @Author 程传平
	 * @Time 2018-01-22 11:43
	 *
	 * @return
	 */
	public List<Role> getRole();

	/**
	 * 根据角色编号获取角色信息
	 * 
	 * @Author 程传平
	 * @Time 2018-01-27 10:48
	 *
	 * @param role_id
	 *            角色编号
	 * @return
	 */
	public List<Role> queryRole(@Param("role_id") String role_id);

	/**
	 * 新增角色
	 * 
	 * @Author 程传平
	 * @Time 2018-01-24 01:21
	 *
	 * @param role
	 * @return 大于0成功，等于0失败
	 */
	public int addRole(Role role);

	/**
	 * 修改角色名称
	 * 
	 * @Author 程传平
	 * @Time 2018-01-24 13:01
	 *
	 * @param role
	 *            角色对象
	 * @return 大于0成功，等于0失败
	 */
	public int updateRole(Role role);

	/**
	 * 根据角色编号删除角色
	 * 
	 * @Author 程传平
	 * @Time 2018-01-24 13:03
	 *
	 * @param role_id
	 *            角色编号
	 * @return 大于0成功，等于0失败
	 */
	public int delRole(@Param("role_id") String role_id);

	/**
	 * 
	 * 设置角色菜单模块权限
	 * 
	 * @Author 程传平
	 * @Time 2018-01-27 14:37
	 *
	 * @param role
	 *            角色对象
	 * @return
	 */
	public int setRoleAuthority(Role role);
}
