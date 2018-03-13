package com.scyypt.entity;

import java.io.Serializable;
/**
 * 项目进度实体类
 * @Description: 
 * @author ChengChuanPing
 * @Time   2018年1月5日上午11:19:06
 */
public class ProjectProgress implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int progressId;//进度编号
	
	private String progressName;//进度名称
	
	private String progressComment;//进度说明

	public ProjectProgress() {
		super();
	}

	public ProjectProgress(int progressId, String progressName, String progressComment) {
		super();
		this.progressId = progressId;
		this.progressName = progressName;
		this.progressComment = progressComment;
	}
	
	

	public int getProgressId() {
		return progressId;
	}

	public void setProgressId(int progressId) {
		this.progressId = progressId;
	}

	public String getProgressName() {
		return progressName;
	}

	public void setProgressName(String progressName) {
		this.progressName = progressName;
	}

	public String getProgressComment() {
		return progressComment;
	}

	public void setProgressComment(String progressComment) {
		this.progressComment = progressComment;
	}

	
	

}
