package com.scyypt.entity;

import java.io.Serializable;
import java.util.Date;

import com.scyypt.util.Global;

/**
 * 日志记录
 * 
 * @author Administrator
 *
 */
public class LogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer logId; // 日志主键
	private String userName; // 用户名称
	private Date date; // 记录时间
	private String handle; // 进行操作
	private String state; // 执行状态
	private String comment; // 备注

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getHandle() {
		 String op = Global.getLogInfo(handle);
		 if (op == null || op.trim().equals("")) {
		
		 op = handle;
		 }
		
		 return op;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public String getState() {
		String op = Global.getLogInfo(state);
		if (op == null || op.trim().equals("")) {

			op = handle;
		}

		return op;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LogEntity() {
		super();
	}

	public LogEntity(Integer logId, String userName, Date date, String handle, String state, String comment) {
		super();
		this.logId = logId;
		this.userName = userName;
		this.date = date;
		this.handle = handle;
		this.state = state;
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "LogEntity [logId=" + logId + ", userName=" + userName + ", date=" + date + ", handle=" + handle
				+ ", state=" + state + ", comment=" + comment + "]";
	}

}
