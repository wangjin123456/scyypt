package com.scyypt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.scyypt.entity.ItemNotesEntity;

public interface ItemNotesDao {
	
	public List<ItemNotesEntity> list();

	public ItemNotesEntity info(@Param("id") String id);

	public Integer add(ItemNotesEntity itemNotesEntity);

	public Integer edit(ItemNotesEntity itemNotesEntity);

}
