package com.scyypt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.scyypt.bean.ItemNotesBean;
import com.scyypt.entity.ItemFollowUpEntity;


public interface ItemFollowUpDao {
	
	public List<ItemNotesBean> getDataListByStage(@Param("itemId") String itemId, @Param("stage") String stage, @Param("start") int start, @Param("pageSize") int pageSize);
	
	public Integer add(ItemFollowUpEntity itemFollowUpEntity);

	public Integer edit(ItemFollowUpEntity itemFollowUpEntity);


}
