package com.scyypt.entity;

import java.io.Serializable;

/**
 * 菜单权限实体类
 * 
 * @Description:
 *
 * @Author 程传平
 *
 * @Time 2018-01-22 20:49
 *
 */
public class Authority implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer authority_id;// 权限编号

	private String operate;// 操作

	public Authority() {
		super();
	}

	/**
	 * 
	 * @param operate
	 *            操作
	 */
	public Authority(String operate) {
		super();
		this.operate = operate;
	}

	public Integer getAuthority_id() {
		return authority_id;
	}

	public void setAuthority_id(Integer authority_id) {
		this.authority_id = authority_id;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}
	

}
