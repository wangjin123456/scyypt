package com.scyypt.entity;

import java.io.Serializable;

/**
 * 用户，角色，操作关系实体类
 * 
 * @Description:
 *
 * @Author 程传平
 *
 * @Time 2018-01-23 11:06
 *
 */
public class URAProvider implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int provider_id;//关系表编号
	
	private int user_id;//用户表编号
	
	private int	 role_id;//角色表编号
	
	private int authority_id;//操作表编号
	
	private String status;// 状态

	private String operationId;
	
	public URAProvider() {
		super();
	}

	public URAProvider(int user_id, int role_id, int authority_id, String status) {
		super();
		this.user_id = user_id;
		this.role_id = role_id;
		this.authority_id = authority_id;
		this.status = status;
	}

	public int getProvider_id() {
		return provider_id;
	}

	public void setProvider_id(int provider_id) {
		this.provider_id = provider_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public int getAuthority_id() {
		return authority_id;
	}

	public void setAuthority_id(int authority_id) {
		this.authority_id = authority_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOperationId() {
		return operationId;
	}

	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}
	
	

}
