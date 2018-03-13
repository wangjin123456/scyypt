package com.scyypt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scyypt.dao.ConversationTypeDao;
import com.scyypt.entity.ConversationType;
import com.scyypt.service.ConversationTypeService;
@Service("ConversationTypeService")
public class ConversationTypeServiceImpl implements ConversationTypeService {
    @Autowired
	private ConversationTypeDao conversationTypeDao;
    
	@Override
	public List<ConversationType> queryAll() {
		return conversationTypeDao.queryAll();
	}

	@Override
	public int addConversationType(ConversationType conversationType) {
		return conversationTypeDao.addConversationType(conversationType);
	}

	@Override
	public int updateConversationType(ConversationType conversationType) {
		return conversationTypeDao.updateConversationType(conversationType);
	}

	@Override
	public int delConversationType(String conversationId) {
		return conversationTypeDao.delConversationType(conversationId);
	}

}
