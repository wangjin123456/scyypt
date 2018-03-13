package com.scyypt.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.scyypt.entity.CooperationTypeEntity;
/**
 * 合作类型服务接口
 * @author ChengChuanPing
 * @Time   2017年12月18日上午9:42:09
 */
public interface CooperationTypeService {
	
	public List<CooperationTypeEntity> list();

	public CooperationTypeEntity info(@Param("id") String id);

	public Integer add(CooperationTypeEntity cooperationTypeEntity);

	public Integer edit(CooperationTypeEntity cooperationTypeEntity);

	public Integer del(@Param("id") String id);
}
