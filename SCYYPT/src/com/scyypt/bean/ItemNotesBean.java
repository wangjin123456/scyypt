package com.scyypt.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

public class ItemNotesBean implements Serializable {

	

	private static final long serialVersionUID = 1L;
	

	private String accountUuid;
	
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
	
	
	
	
	
	

	public ItemNotesBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public ItemNotesBean(String accountUuid, Integer fileId, Integer type, String message, String path, String title,
			Date createTime, String comment) {
		super();
		this.accountUuid = accountUuid;
		this.fileId = fileId;
		this.type = type;
		this.message = message;
		this.path = path;
		this.title = title;
		this.createTime = createTime;
		this.comment = comment;
	}

	public String getAccountUuid() {
		return accountUuid;
	}

	public void setAccountUuid(String accountUuid) {
		this.accountUuid = accountUuid;
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
