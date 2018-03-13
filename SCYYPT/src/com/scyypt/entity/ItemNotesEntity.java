package com.scyypt.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;


/*
	DROP TABLE IF EXISTS item_notes;
	CREATE TABLE item_notes(
	id INT NOT NULL AUTO_INCREMENT,
	fileId VARCHAR(128) COMMENT 'ICE文件微服务id',
	typeId INT NOT NULL COMMENT '记录类型 文本、图片、语音、视频',
	message TEXT COMMENT '发言内容',
	path  VARCHAR(256)  COMMENT '资源路径',
	title VARCHAR(256) NULL COMMENT '资源标题',
	createtime DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期时间',
	comment TEXT NULL COMMENT '说明',
	PRIMARY KEY (id)
	)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目跟进记录表';

*/


public class ItemNotesEntity implements Serializable {


	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	
	private Integer fileId;
	
	private Integer type;
	
	private String message;
	
	private String path;
	
	private String title;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JSONField (format="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	
	private String comment;

	
	
	public ItemNotesEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	public ItemNotesEntity(Integer fileId, Integer type, String message, String path, String title, Date createTime,
			String comment) {
		super();
		this.fileId = fileId;
		this.type = type;
		this.message = message;
		this.path = path;
		this.title = title;
		this.createTime = createTime;
		this.comment = comment;
	}




	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
