package com.scyypt.entity;

import java.io.Serializable;
/**
 * 关联项目实体类
 * @Description: 
 * @author ChengChuanPing
 * @Time   2018年1月5日下午10:37:20
 */
public class RelevanceProjects implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer relevanceId;	//关联编号
	
	private Integer relevanceProjectsId;	//关联项目编号
	
	private String relevanceItemName;	//关联项目名

	public RelevanceProjects() {
		super();
	}

	public RelevanceProjects(Integer relevanceId, Integer relevanceProjectsId, String relevanceItemName) {
		super();
		this.relevanceId = relevanceId;
		this.relevanceProjectsId = relevanceProjectsId;
		this.relevanceItemName = relevanceItemName;
	}

	public Integer getRelevanceId() {
		return relevanceId;
	}

	public void setRelevanceId(Integer relevanceId) {
		this.relevanceId = relevanceId;
	}

	public Integer getRelevanceProjectsId() {
		return relevanceProjectsId;
	}

	public void setRelevanceProjectsId(Integer relevanceProjectsId) {
		this.relevanceProjectsId = relevanceProjectsId;
	}

	public String getrelevanceItemName() {
		return relevanceItemName;
	}

	public void setItemName(String relevanceItemName) {
		this.relevanceItemName = relevanceItemName;
	}
	
	


}
