package com.scyypt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.scyypt.entity.Authority;

/**
 * 权限接口
 * 
 * @Description:
 *
 * @Author 程传平
 *
 * @Time 2018-01-22 20:53
 *
 */
public interface AuthorityDao {
	/**
	 * 获取全部权限操作
	 * 
	 * @Author 程传平
	 * @Time 2018-01-22 20:54
	 *
	 * @return 权限集合
	 */
	public List<Authority> getAllAuthority();

	/**
	 * 根据菜单模块编号查询菜单模块
	 * 
	 * @Author 程传平
	 * @Time 2018-01-22 20:54
	 *
	 * @param authority_id
	 *            菜单模块编号
	 * @return 权限集合
	 */
	public List<Authority> findAuthority(@Param("authority_id") String authority_id);

	/**
	 * 新增权限模块
	 * 
	 * @Author 程传平
	 * @Time 2018-01-24 01:59
	 *
	 * @return 大于0成功，等于0失败
	 */
	public int addAuthority(Authority authority);

	/**
	 * 修改权限模块
	 * 
	 * @Author 程传平
	 * @Time 2018-01-24 16:29
	 *
	 * @param authority
	 *            权限模块对象
	 * @return 大于0成功，等于0失败
	 */
	public int updateAuthority(Authority authority);

	/**
	 * 根据权限模块编号删除权限模块
	 * 
	 * @Author 程传平
	 * @Time 2018-01-24 16:40
	 *
	 * @param authority_id
	 *            权限模块编号
	 * @return 大于0成功，等于0失败
	 */
	public int delAuthority(@Param("authority_id") String authority_id);

}
