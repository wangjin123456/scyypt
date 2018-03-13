package com.scyypt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.scyypt.entity.ItemUserNotesEntity;

public interface ItemUserNotesDao {

	public List<ItemUserNotesEntity> list();

	public ItemUserNotesEntity info(@Param("id") String id);

	public Integer add(ItemUserNotesEntity itemUserNotesEntity);

	public Integer edit(ItemUserNotesEntity itemUserNotesEntity);
	
}
