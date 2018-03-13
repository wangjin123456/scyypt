package com.scyypt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scyypt.dao.CooperationTypeDao;
import com.scyypt.entity.CooperationTypeEntity;
import com.scyypt.service.CooperationTypeService;
/**
 * 合作类型接口实现
 * @author ChengChuanPing
 * @Time   2017年12月18日上午9:44:21
 */
@Service("CooperationTypeService")
public class CooperationTypeServiceImpl implements CooperationTypeService {
	 
	@Autowired
	private CooperationTypeDao cooperationTypeDao;
	@Override
	public List<CooperationTypeEntity> list() {
		return cooperationTypeDao.list();
	}

	@Override
	public CooperationTypeEntity info(String id) {
		return cooperationTypeDao.info(id);
	}

	@Override
	public Integer add(CooperationTypeEntity cooperationTypeEntity) {
		return cooperationTypeDao.add(cooperationTypeEntity);
	}

	@Override
	public Integer edit(CooperationTypeEntity cooperationTypeEntity) {
		return cooperationTypeDao.edit(cooperationTypeEntity);
	}

	@Override
	public Integer del(String id) {
		
		return cooperationTypeDao.del(id);
	}

}
