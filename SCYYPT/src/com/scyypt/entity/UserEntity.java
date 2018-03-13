package com.scyypt.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 用户信息实体类
 * 
 * @Description:
 *
 * @Author 程传平
 *
 * @Time 2018-01-22 10:31
 *
 */
public class UserEntity implements Serializable {

	private Integer u_id;// 自动编号

	private String accountUuid;// 用户编号

	private String corpId;// 租户id

	private String userName;// 员工账号

	private String name;// 用户姓名

	private String mobile;// 手机

	private String email;// 邮箱

	private Integer sex;// 性别,0未知,1男,2女

	private String jobType;// 岗位类型

	private String jobUuid;// 岗位编号

	private Integer role_id;// 角色编号

	private Role role; // 用于接收角色信息

	private List<Authority> authority; // 用户接收操作信息

	private List<URAProvider> uRAProvider; // 用于接收关系信息

	private static final long serialVersionUID = 1L;

	public UserEntity() {
		super();
	}

	/**
	 * 
	 * @param accountUuid
	 *            用户编号
	 * @param corpId
	 *            租户id
	 * @param userName
	 *            员工账号
	 * @param name
	 *            用户姓名
	 * @param mobile
	 *            手机
	 * @param email
	 *            邮箱
	 * @param sex
	 *            性别,0未知,1男,2女
	 * @param jobType
	 *            岗位类型
	 * @param jobUuid
	 *            岗位编号
	 * @param role_id
	 *            角色编号
	 */

	public UserEntity(String accountUuid, String corpId, String userName, String name, String mobile, String email,
			Integer sex, String jobType, String jobUuid, Integer role_id) {
		super();
		this.accountUuid = accountUuid;
		this.corpId = corpId;
		this.userName = userName;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.sex = sex;
		this.jobType = jobType;
		this.jobUuid = jobUuid;
		this.role_id = role_id;
	}

	public Integer getU_id() {
		return u_id;
	}

	public void setU_id(Integer u_id) {
		this.u_id = u_id;
	}

	public String getAccountUuid() {
		return accountUuid;
	}

	public void setAccountUuid(String accountUuid) {
		this.accountUuid = accountUuid;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getJobUuid() {
		return jobUuid;
	}

	public void setJobUuid(String jobUuid) {
		this.jobUuid = jobUuid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Authority> getAuthority() {
		return authority;
	}

	public void setAuthority(List<Authority> authority) {
		this.authority = authority;
	}

	public List<URAProvider> getuRAProvider() {
		return uRAProvider;
	}

	public void setuRAProvider(List<URAProvider> uRAProvider) {
		this.uRAProvider = uRAProvider;
	}

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

}
