package com.scyypt.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 角色实体类
 * 
 * @Description:
 * @author ChengChuanPing
 * @Time 2018年1月17日下午3:35:58
 */
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int role_id;// 角色编号

	private String role_name;// 角色名称

	private String authority_id;// 菜单模块编号

	private List<Authority> authority; // 用于接收权限信息

	public Role() {
		super();
	}

	/**
	 * 
	 * @param role_name
	 *            角色名称
	 */
	public Role(String role_name) {
		super();
		this.role_name = role_name;
	}

	public Role(int role_id, String role_name, String authority_id) {
		super();
		this.role_id = role_id;
		this.role_name = role_name;
		this.authority_id = authority_id;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public List<Authority> getAuthority() {
		return authority;
	}

	public void setAuthority(List<Authority> authority) {
		this.authority = authority;
	}

	public String getAuthority_id() {
		return authority_id;
	}

	public void setAuthority_id(String authority_id) {
		this.authority_id = authority_id;
	}

}
