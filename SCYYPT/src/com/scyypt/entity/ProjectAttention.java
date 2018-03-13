package com.scyypt.entity;

import java.io.Serializable;
/**
 * 项目关注人实体类
 * @Description: 
 * @author ChengChuanPing
 * @Time   2018年1月5日下午10:34:46
 */
public class ProjectAttention implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer attentionId;	//项目关注编号
	
	private Integer itemId;
	
	private String accountUuid;	//关注人编号
	
	private String name;	//关注人姓名

	public ProjectAttention() {
		super();
	}

	


	public ProjectAttention(Integer itemId, String accountUuid, String name) {
		super();
		this.itemId = itemId;
		this.accountUuid = accountUuid;
		this.name = name;
	}





	public Integer getAttentionId() {
		return attentionId;
	}

	public void setAttentionId(Integer attentionId) {
		this.attentionId = attentionId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getAccountUuid() {
		return accountUuid;
	}

	public void setAccountUuid(String accountUuid) {
		this.accountUuid = accountUuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
 
	

}
