package com.scyypt.entity;

import java.io.Serializable;


/*
  	DROP TABLE IF EXISTS item_user_notes;
	CREATE TABLE item_user_notes(
	 id INT NOT NULL AUTO_INCREMENT,
	 noteId INT NOT NULL COMMENT '信息id',
	 accountUuid VARCHAR(128) NOT NULL COMMENT '跟进人身份标识唯一',
	 comment TEXT NULL COMMENT '说明',
	 PRIMARY KEY (id)
	)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目跟进记录表';
	
 */



public class ItemUserNotesEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	
	private Integer noteId;
	
	private Integer itemId;
	
	private String accountUuid;
	
	private String comment;

	
	
	
	
	public ItemUserNotesEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	 
	public ItemUserNotesEntity(Integer noteId, Integer itemId, String accountUuid, String comment) {
		super();
		this.noteId = noteId;
		this.itemId = itemId;
		this.accountUuid = accountUuid;
		this.comment = comment;
	}





	public Integer getId() {
		return id;
	}





	public void setId(Integer id) {
		this.id = id;
	}





	public Integer getNoteId() {
		return noteId;
	}





	public void setNoteId(Integer noteId) {
		this.noteId = noteId;
	}





	public Integer getItemId() {
		return itemId;
	}





	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}





	public String getAccountUuid() {
		return accountUuid;
	}





	public void setAccountUuid(String accountUuid) {
		this.accountUuid = accountUuid;
	}





	public String getComment() {
		return comment;
	}





	public void setComment(String comment) {
		this.comment = comment;
	}





	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
