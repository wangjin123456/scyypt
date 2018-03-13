package com.scyypt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scyypt.dao.ItemProviderDao;
import com.scyypt.entity.ItemProvider;
import com.scyypt.service.ItemProviderService;

@Service("ItemProviderService")
public class ItemProviderServiceImpl implements ItemProviderService {
	@Autowired
	private ItemProviderDao itemProviderDao;

	@Override
	public int addItemProvider(ItemProvider itemProvider) {

		return itemProviderDao.addItemProvider(itemProvider);
	}

	@Override
	public Integer delItemProvider(String itemId, String providerId, String userId) {
		return itemProviderDao.delItemProvider(itemId, providerId, userId);
	}

}
