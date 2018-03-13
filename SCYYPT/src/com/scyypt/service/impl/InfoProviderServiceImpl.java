/**
 * 
 */
package com.scyypt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scyypt.dao.InfoProviderDao;
import com.scyypt.entity.InfoProvider;
import com.scyypt.service.InfoProviderService;

/**
 * 实现信息提供者接口
 * 
 * @author ChengChuanPing
 * @Time 2017年12月15日上午9:32:49
 */
@Service("InfoProviderService")
public class InfoProviderServiceImpl implements InfoProviderService {
	@Autowired
	private InfoProviderDao infoProviderDao;

	@Override
	public int addInfoProvider(InfoProvider infoProvider) {
		return infoProviderDao.addInfoProvider(infoProvider);
	}

	@Override
	public Integer del(String providerId) {
		return infoProviderDao.del(providerId);
	}

}
