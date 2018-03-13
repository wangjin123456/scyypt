/**
 * 
 */
package com.scyypt.service;

import org.apache.ibatis.annotations.Param;

import com.scyypt.entity.InfoProvider;

/**
 * @author ChengChuanPing
 * @Time 2017年12月15日上午9:25:14
 */
public interface InfoProviderService {

	/**
	 * 添加信息提供者
	 * 
	 * @param infoProvider
	 * @return 0失败,>0成功
	 */
	public int addInfoProvider(InfoProvider infoProvider);

	/**
	 * 删除信息提供者
	 * 
	 * @param providerId
	 *            信息提供者编号
	 * @return 0失败,>0成功
	 */
	public Integer del(@Param("providerId") String providerId);
}
