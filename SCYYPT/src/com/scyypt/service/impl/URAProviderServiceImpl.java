package com.scyypt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scyypt.dao.URAProviderDao;
import com.scyypt.entity.URAProvider;
import com.scyypt.service.URAProviderService;

@Service("URAProviderService")
public class URAProviderServiceImpl implements URAProviderService {

	@Autowired
	private URAProviderDao uRAProviderDao;

	@Override
	public int addProvider(URAProvider uRAProvider) {

		return uRAProviderDao.addProvider(uRAProvider);
	}

	@Override
	public int reviseAuthority(URAProvider uRAProvider) {
		
		return uRAProviderDao.reviseAuthority(uRAProvider);
	}

	@Override
	public Integer updateAuthority(URAProvider uRAProvider) {
		return uRAProviderDao.updateAuthority(uRAProvider);
	}

}
