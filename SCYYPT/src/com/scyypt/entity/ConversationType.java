package com.scyypt.entity;

import java.io.Serializable;
/**
 * 
 * @Description: 洽谈类型实体
 * @author ChengChuanPing
 * @Time   2017年12月19日上午8:54:54
 */
public class ConversationType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int conversationId;//洽谈类型编号
	
	private String conversationTitle;//洽谈类型标题
	
	private String conversationComment;//洽谈类型说明

	public ConversationType() {
		super();
	}

	public ConversationType(int conversationId, String conversationTitle, String conversationComment) {
		super();
		this.conversationId = conversationId;
		this.conversationTitle = conversationTitle;
		this.conversationComment = conversationComment;
	}

	public int getConversationId() {
		return conversationId;
	}

	public void setConversationId(int conversationId) {
		this.conversationId = conversationId;
	}

	public String getConversationTitle() {
		return conversationTitle;
	}

	public void setConversationTitle(String conversationTitle) {
		this.conversationTitle = conversationTitle;
	}

	public String getConversationComment() {
		return conversationComment;
	}

	public void setConversationComment(String conversationComment) {
		this.conversationComment = conversationComment;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
