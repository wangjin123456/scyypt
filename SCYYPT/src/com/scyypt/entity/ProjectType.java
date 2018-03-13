package com.scyypt.entity;

import java.io.Serializable;

/**
 * 项目类型
 * 
 * @author ChengChuanPing
 * @date 
 */
public class ProjectType implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int projectType_id;//项目类型编号
	private String projectType_name;//项目类型名称
	private String projectType_Comment;//项目类型说明
	
	public ProjectType() {
		super();
	}

	public ProjectType(int projectType_id, String projectType_name, String projectType_Comment) {
		super();
		this.projectType_id = projectType_id;
		this.projectType_name = projectType_name;
		this.projectType_Comment = projectType_Comment;
	}

	public int getProjectType_id() {
		return projectType_id;
	}

	public void setProjectType_id(int projectType_id) {
		this.projectType_id = projectType_id;
	}

	public String getProjectType_name() {
		return projectType_name;
	}

	public void setProjectType_name(String projectType_name) {
		this.projectType_name = projectType_name;
	}

	public String getProjectType_Comment() {
		return projectType_Comment;
	}

	public void setProjectType_Comment(String projectType_Comment) {
		this.projectType_Comment = projectType_Comment;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

	
}