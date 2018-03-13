package com.scyypt.dao;

import org.apache.ibatis.annotations.Param;

import com.scyypt.entity.ItemProvider;
/**
 * 项目关系接口
 * @author ChengChuanPing
 * @Time   2017年12月15日下午3:16:13
 */
public interface ItemProviderDao {
	/**
	 * 新增关系
	 * @param itemProvider
	 * @return
	 */
	public int addItemProvider(ItemProvider itemProvider);
	/**
	 * 删除项目关系
	 * @param itemId 项目编号
	 * @param providerId 信息提供者编号
	 * @param userId    用户编号
	 * @return 0失败,>0 成功
	 */
	public Integer delItemProvider(@Param("itemId")String itemId,@Param("providerId") String providerId,@Param("userId")String userId);
}