package com.scyypt.entity;

import java.io.Serializable;

/*
	DROP TABLE IF EXISTS item_user;
	CREATE TABLE item_user(
	 id INT NOT NULL AUTO_INCREMENT,
	 userType INT NOT NULL DEFAULT 0 COMMENT '项目用户类型 0: 创建者， 1：跟进人 2，关注人',
	 accountUuid VARCHAR(128) NOT NULL COMMENT '跟进人身份标识唯一',
	 comment TEXT NULL COMMENT '说明',
	 PRIMARY KEY (id)
	)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目跟进表';
*/


public class ItemUserEntity implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer userType;
	
	private String accountUuid;
	
	private String comment;
	
	
	

	public ItemUserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ItemUserEntity(Integer userType, String accountUuid, String comment) {
		super();
		this.userType = userType;
		this.accountUuid = accountUuid;
		this.comment = comment;
	}
	
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
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
