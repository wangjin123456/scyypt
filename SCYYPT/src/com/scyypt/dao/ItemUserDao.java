package com.scyypt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.scyypt.entity.ItemNotesTypeEntity;
import com.scyypt.entity.ItemUserEntity;

public interface ItemUserDao {

	public List<ItemUserEntity> list();

	public ItemNotesTypeEntity info(@Param("id") String id);

	public Integer add(ItemUserEntity itemUserEntity);

	public Integer edit(ItemUserEntity itemUserEntity);
	
}
