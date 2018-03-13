package com.scyypt.bean;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 用于接收前端发送项目新增项目请求
 * 
 * @Description:
 * @author ChengChuanPing
 * @Time 2017年12月24日下午5:08:57
 */
public class ItemInfoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer flag;//保存是否是彩住宅

	private String userId; //用户编号
	
	private String userName;  //用户姓名
	
	private String itemCooperation;// 合作方式

	private String itemName;// 项目名称

	private String province;// 项目地址_省
	
	private String city;// 项目地址_市
	
	private String district;// 项目地址_区
	
	private String itemAddress;// 详细地址
	
	private String itemaArea;// 项目面积

	private String itemJoinArea;// 入伙面积
	
	private String itemAttention; // 项目关注人编号

	private String projectPeoName;// 项目关注人名称

	private String itemRelevancy;// 关联项目编号
	
	private String relevanceItemName;// 关联项目名称

	private String itemExplain;// 项目说明

	private String itemCreateDatetime;// 项目创建时间
	
	private String itemProgress="1";//项目进度

	private String itemFollowDatetime;// 更进时间

	private String itemUnmarked;// 标识是草稿还是保存

	private String secretLairs;  //信息提供者
	
	


	public ItemInfoBean() {
		super();
	}

	public ItemInfoBean(Integer flag, String userId, String userName, String itemCooperation, String itemName,
			String province, String city, String district, String itemAddress, String itemaArea, String itemJoinArea,
			String itemAttention, String projectPeoName, String itemRelevancy, String relevanceItemName,
			String itemExplain, String itemCreateDatetime, String itemProgress, String itemFollowDatetime,
			String itemUnmarked, String secretLairs) {
		super();
		this.flag = flag;
		this.userId = userId;
		this.userName = userName;
		this.itemCooperation = itemCooperation;
		this.itemName = itemName;
		this.province = province;
		this.city = city;
		this.district = district;
		this.itemAddress = itemAddress;
		this.itemaArea = itemaArea;
		this.itemJoinArea = itemJoinArea;
		this.itemAttention = itemAttention;
		this.projectPeoName = projectPeoName;
		this.itemRelevancy = itemRelevancy;
		this.relevanceItemName = relevanceItemName;
		this.itemExplain = itemExplain;
		this.itemCreateDatetime = itemCreateDatetime;
		this.itemProgress = itemProgress;
		this.itemFollowDatetime = itemFollowDatetime;
		this.itemUnmarked = itemUnmarked;
		this.secretLairs = secretLairs;
	}


















	public Integer getFlag() {
		return flag;
	}




	public void setFlag(Integer flag) {
		this.flag = flag;
	}




	public String getItemAddress() {
		return itemAddress;
	}




	public void setItemAddress(String itemAddress) {
		this.itemAddress = itemAddress;
	}




	public String getUserId() {
		return userId;
	}




	public void setUserId(String userId) {
		this.userId = userId;
	}




	public String getUserName() {
		return userName;
	}




	public void setUserName(String userName) {
		this.userName = userName;
	}




	public String getItemCooperation() {
		return itemCooperation;
	}




	public void setItemCooperation(String itemCooperation) {
		this.itemCooperation = itemCooperation;
	}




	public String getItemName() {
		return itemName;
	}




	public void setItemName(String itemName) {
		this.itemName = itemName;
	}




	public String getProvince() {
		return province;
	}




	public void setProvince(String province) {
		this.province = province;
	}




	public String getCity() {
		return city;
	}




	public void setCity(String city) {
		this.city = city;
	}




	public String getDistrict() {
		return district;
	}




	public void setDistrict(String district) {
		this.district = district;
	}




	public String getItemaArea() {
		return itemaArea;
	}




	public void setItemaArea(String itemaArea) {
		this.itemaArea = itemaArea;
	}




	public String getItemJoinArea() {
		return itemJoinArea;
	}




	public void setItemJoinArea(String itemJoinArea) {
		this.itemJoinArea = itemJoinArea;
	}




	public String getItemAttention() {
		return itemAttention;
	}




	public void setItemAttention(String itemAttention) {
		this.itemAttention = itemAttention;
	}




	public String getProjectPeoName() {
		return projectPeoName;
	}




	public void setProjectPeoName(String projectPeoName) {
		this.projectPeoName = projectPeoName;
	}




	public String getItemRelevancy() {
		return itemRelevancy;
	}




	public void setItemRelevancy(String itemRelevancy) {
		this.itemRelevancy = itemRelevancy;
	}




	public String getRelevanceItemName() {
		return relevanceItemName;
	}




	public void setRelevanceItemName(String relevanceItemName) {
		this.relevanceItemName = relevanceItemName;
	}




	public String getItemExplain() {
		return itemExplain;
	}




	public void setItemExplain(String itemExplain) {
		this.itemExplain = itemExplain;
	}




	public String getItemCreateDatetime() {
		return itemCreateDatetime;
	}




	public void setItemCreateDatetime(String itemCreateDatetime) {
		this.itemCreateDatetime = itemCreateDatetime;
	}




	public String getItemProgress() {
		return itemProgress;
	}




	public void setItemProgress(String itemProgress) {
		this.itemProgress = itemProgress;
	}




	public String getItemFollowDatetime() {
		return itemFollowDatetime;
	}




	public void setItemFollowDatetime(String itemFollowDatetime) {
		this.itemFollowDatetime = itemFollowDatetime;
	}




	public String getItemUnmarked() {
		return itemUnmarked;
	}




	public void setItemUnmarked(String itemUnmarked) {
		this.itemUnmarked = itemUnmarked;
	}




	public String getSecretLairs() {
		return secretLairs;
	}




	public void setSecretLairs(String secretLairs) {
		this.secretLairs = secretLairs;
	}



	
}
