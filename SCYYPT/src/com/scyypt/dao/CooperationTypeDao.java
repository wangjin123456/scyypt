package com.scyypt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.scyypt.entity.CooperationTypeEntity;

public interface CooperationTypeDao {

public List<CooperationTypeEntity> list();
	
	public CooperationTypeEntity info(@Param("id") String id);
		
	public Integer add(CooperationTypeEntity cooperationTypeEntity);
	
	public Integer edit(CooperationTypeEntity cooperationTypeEntity);

	public Integer del(@Param("id") String id);


}
