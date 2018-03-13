package com.scyypt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.scyypt.entity.LogEntity;

/**
 * 日志接口
 * 
 * @Description: 负责对日志进行一系列操作
 * @author ChengChuanPing
 * @Time 2017年12月27日上午10:22:18
 */
public interface LogDao {
	/**
	 * 
	 * @param log
	 *            日志对象
	 */
	public int addLog(LogEntity log);

	/**
	 * 根据用户名查询所有日志总记录数
	 * 
	 * @param userName
	 *            用户名称
	 * @return
	 */
	public int findAllCount(@Param("userName") String userName);

	/**
	 * 分页查询我的日志信息
	 * 
	 * @param userName
	 *            用户名称
	 * @param start
	 *            起始页
	 * @param pageSize
	 *            页面大小
	 * @return 我的日志集合
	 */
	public List<LogEntity> findLogByPage(@Param("userName") String userName, @Param("start") int start,
			@Param("pageSize") int pageSize);

	/**
	 * 根据日志编号删除日志
	 * 
	 * @param logId
	 *            日志编号
	 * @return 0失败,>0成功
	 */
	public Integer deleteLog(@Param("logId") String logId);

}
