package com.scyypt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.scyypt.entity.ItemNotesTypeEntity;

public interface ItemNotesTypeDao {

	public List<ItemNotesTypeEntity> list();

	public ItemNotesTypeEntity info(@Param("id") String id);

	public Integer add(ItemNotesTypeEntity itemNoteTypeEntity);

	public Integer edit(ItemNotesTypeEntity itemNoteTypeEntity);

	
}
