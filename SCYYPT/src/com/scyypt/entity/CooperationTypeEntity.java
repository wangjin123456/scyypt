package com.scyypt.entity;

import java.io.Serializable;
/**
 * 合作方式实体类
 * @Description: 
 * @author ChengChuanPing
 * @Time   2017年12月22日下午12:30:15
 */
public class CooperationTypeEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;//合作方式编号
	
	private String name;//合作方式标题
	
	private String comment;//合作方式说明

	
	
	
	public CooperationTypeEntity() {
		super();
		// TODO Auto-generated constructor stub
	}



	public CooperationTypeEntity(String name, String comment) {
		super();
		this.name = name;
		this.comment = comment;
	}



	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
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
