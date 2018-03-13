package com.scyypt.entity;

import java.io.Serializable;



/*
   	DROP TABLE IF EXISTS item_note_type;
	CREATE TABLE item_note_type(
	id INT NOT NULL AUTO_INCREMENT,
	type VARCHAR(32) COMMENT '记录类型 文本、图片、语音、视频',
	comment TEXT NULL COMMENT '说明',
	PRIMARY KEY (id)
	)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目资源类型表';
	
 */


public class ItemNotesTypeEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;

	
	private Integer id;
	
	private String type;
	
	private String comment;
	

	public ItemNotesTypeEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ItemNotesTypeEntity(String type, String comment) {
		super();
		this.type = type;
		this.comment = comment;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
