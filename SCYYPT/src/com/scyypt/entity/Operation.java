package com.scyypt.entity;

import java.io.Serializable;

/**
 * 菜单功能操作权限实体
 * 
 * @Description:
 *
 * @Author 程传平
 *
 * @Time 2018-01-26 19:41
 *
 */
public class Operation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int operationId; // 功能权限编号

	private String operationName;// 功能权限名称

	
	public Operation() {
		super();
	}


	/**
	 * 
	 * @param operationName 功能权限名称
	 */
	public Operation(String operationName) {
		super();
		this.operationName = operationName;
	}


	public int getOperationId() {
		return operationId;
	}


	public void setOperationId(int operationId) {
		this.operationId = operationId;
	}


	public String getOperationName() {
		return operationName;
	}


	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	
	
	
	

}
