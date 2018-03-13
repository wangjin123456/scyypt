package com.scyypt.entity;

import java.io.Serializable;


/*
	 DROP TABLE IF EXISTS item_followup;
	CREATE TABLE item_followup(
	  id INT NOT NULL AUTO_INCREMENT,
	  itemId  INT DEFAULT NULL COMMENT '项目ID',
	  userId  INT COMMENT '项目创建人ID',
	  upUserId INT DEFAULT NULL COMMENT '项目跟进人ID',
	  withUserId INT DEFAULT NULL COMMENT '项目关注人ID',
	  stage INT DEFAULT NULL COMMENT '项目阶段',
	  state INT DEFAULT NULL COMMENT '项目状态',
	  comment TEXT NULL COMMENT'说明',
	  PRIMARY KEY (id)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目跟进表'; 
 */


public class ItemFollowUpEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	
	private Integer itemId;
	
	private Integer userId;
	
	private String accountUuid;
		
	private Integer stage;
	
	private Integer state;
	
	private String comment;

	

	
	
	public ItemFollowUpEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	


	public ItemFollowUpEntity(Integer id, Integer itemId, Integer userId, String accountUuid, Integer stage, Integer state, String comment) {
		super();
		this.id = id;
		this.itemId = itemId;
		this.userId = userId;
		this.accountUuid = accountUuid;
		this.stage = stage;
		this.state = state;
		this.comment = comment;
	}






	public Integer getItemId() {
		return itemId;
	}






	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}






	public Integer getUserId() {
		return userId;
	}






	public void setUserId(Integer userId) {
		this.userId = userId;
	}






	public String getAccountUuid() {
		return accountUuid;
	}






	public void setAccountUuid(String accountUuid) {
		this.accountUuid = accountUuid;
	}




	public Integer getStage() {
		return stage;
	}






	public void setStage(Integer stage) {
		this.stage = stage;
	}






	public Integer getState() {
		return state;
	}






	public void setState(Integer state) {
		this.state = state;
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
