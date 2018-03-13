package com.scyypt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scyypt.dao.OperationDao;
import com.scyypt.entity.Operation;
import com.scyypt.service.OperationService;
@Service("operationService")
public class OperationServiceImpl implements OperationService {

	@Autowired
	private OperationDao operationDao;
	@Override
	public List<Operation> findOperation() {
		return operationDao.findOperation();
	}
	@Override
	public List<Operation> queryOperation(String roleId, String authorityId) {
		return operationDao.queryOperation(roleId, authorityId);
	}

}
