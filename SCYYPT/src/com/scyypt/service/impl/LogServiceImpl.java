package com.scyypt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scyypt.dao.LogDao;
import com.scyypt.entity.LogEntity;
import com.scyypt.service.LogService;
/**
 * 实现日志服务接口
 * @Description: 
 * @author ChengChuanPing
 * @Time   2017年12月27日上午10:36:43
 */
@Service("logService")
public class LogServiceImpl implements LogService {
	@Autowired
	private LogDao logDao;

	@Override
	public int addLog(LogEntity log) {
		return logDao.addLog(log);
	}

	@Override
	public int findAllCount(String userName) {
		return logDao.findAllCount(userName);
	}

	@Override
	public List<LogEntity> findLogByPage(String userName, int start, int pageSize) {
		return logDao.findLogByPage(userName, start, pageSize);
	}

	@Override
	public Integer deleteLog(String logId) {
		return logDao.deleteLog(logId);
	}
	

}
