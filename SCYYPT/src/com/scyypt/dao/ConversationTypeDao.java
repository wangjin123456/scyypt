package com.scyypt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.scyypt.entity.ConversationType;

public interface ConversationTypeDao {
	/**
	 * 查询全部洽谈类型
	 * @return  洽谈类型对象集合
	 */
	public List<ConversationType> queryAll();
	/**
	 * 新增洽谈类型
	 * @param conversationType 洽谈类型对象
	 * @return
	 */
	public int addConversationType(ConversationType conversationType);
	/**
	 * 修改洽谈类型
	 * @param conversationType 洽谈类型对象
	 * @return
	 */
	public int updateConversationType(ConversationType conversationType);
	/**
	 * 删除洽谈类型
	 * @param conversationId 洽谈类型编号
	 * @return
	 */
	public int delConversationType(@Param("conversationId") String conversationId);
}
