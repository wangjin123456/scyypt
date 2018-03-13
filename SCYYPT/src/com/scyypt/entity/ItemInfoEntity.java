package com.scyypt.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 项目实体类
 * 
 * @author ChengChuanPing
 * @Time 2017年12月14日下午6:55:20
 */
public class ItemInfoEntity implements Serializable {

	/**
	 * 作序列化版本比较用
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer itemId;	//项目编号
	
	private String accountUuid;	//用户标识
	
	private String userName;	//用户姓名
	
	private String itemCooperation;	//合作方式
	
	private Integer flag;//保存是否是彩住宅
	
	private String itemName;	//项目名称
	
	private String province;	//项目地址_省
	
	private String city;	//项目地址_市
	
	private String district ;	//项目地址_区
	
	private String itemAddress;// 详细地址
	
	private String itemaArea;	//项目面积
	
	private String itemJoinArea;	//入伙面积
	
	private String itemExplain;	//项目说明
	
	private String itemCreateDatetime;	//创建日期时间
	
	private Integer itemProgress=1;	//项目进度,默认值1为初次访谈
	
	private String itemFollowDatetime;	//更进时间
	
	private Integer itemUnmarked;	//标识是草稿还是保存
	
	private List<InfoProvider> infoProvider; //用于接收信息提供者
	
	private List<ProjectAttention> projectAttention; //用于接收项目关注人
	
	private List<RelevanceProjects> relevanceProjects; //用于接关联收项目
	
	private String reminder_oa;       // 接收用户oa账号列表
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JSONField (format="yyyy-MM-dd")
	private Date  reminder_expire;    // 到期时间 
	
	private Integer reminder_days;     // 到期前多少天提醒
	
	private String reminder_content;   // 提醒内容
	
	

	public ItemInfoEntity() {
		super();
	}

	public ItemInfoEntity(Integer itemId, String accountUuid, String userName, String itemCooperation, Integer flag,
			String itemName, String province, String city, String district, String itemAddress, String itemaArea,
			String itemJoinArea, String itemExplain, String itemCreateDatetime, Integer itemProgress,
			String itemFollowDatetime, Integer itemUnmarked, List<InfoProvider> infoProvider,
			List<ProjectAttention> projectAttention, List<RelevanceProjects> relevanceProjects) {
		super();
		this.itemId = itemId;
		this.accountUuid = accountUuid;
		this.userName = userName;
		this.itemCooperation = itemCooperation;
		this.flag = flag;
		this.itemName = itemName;
		this.province = province;
		this.city = city;
		this.district = district;
		this.itemAddress = itemAddress;
		this.itemaArea = itemaArea;
		this.itemJoinArea = itemJoinArea;
		this.itemExplain = itemExplain;
		this.itemCreateDatetime = itemCreateDatetime;
		this.itemProgress = itemProgress;
		this.itemFollowDatetime = itemFollowDatetime;
		this.itemUnmarked = itemUnmarked;
		this.infoProvider = infoProvider;
		this.projectAttention = projectAttention;
		this.relevanceProjects = relevanceProjects;
	}



	public ItemInfoEntity(String accountUuid, String userName, String itemCooperation, Integer flag, String itemName,
			String province, String city, String district, String itemAddress, String itemaArea, String itemJoinArea,
			String itemExplain, String itemCreateDatetime, Integer itemProgress, String itemFollowDatetime,
			Integer itemUnmarked) {
		super();
		this.accountUuid = accountUuid;
		this.userName = userName;
		this.itemCooperation = itemCooperation;
		this.flag = flag;
		this.itemName = itemName;
		this.province = province;
		this.city = city;
		this.district = district;
		this.itemAddress = itemAddress;
		this.itemaArea = itemaArea;
		this.itemJoinArea = itemJoinArea;
		this.itemExplain = itemExplain;
		this.itemCreateDatetime = itemCreateDatetime;
		this.itemProgress = itemProgress;
		this.itemFollowDatetime = itemFollowDatetime;
		this.itemUnmarked = itemUnmarked;
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

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
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

	public String getItemAddress() {
		return itemAddress;
	}

	public void setItemAddress(String itemAddress) {
		this.itemAddress = itemAddress;
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

	public Integer getItemProgress() {
		return itemProgress;
	}

	public void setItemProgress(Integer itemProgress) {
		this.itemProgress = itemProgress;
	}

	public String getItemFollowDatetime() {
		return itemFollowDatetime;
	}

	public void setItemFollowDatetime(String itemFollowDatetime) {
		this.itemFollowDatetime = itemFollowDatetime;
	}

	public Integer getItemUnmarked() {
		return itemUnmarked;
	}

	public void setItemUnmarked(Integer itemUnmarked) {
		this.itemUnmarked = itemUnmarked;
	}

	public List<InfoProvider> getInfoProvider() {
		return infoProvider;
	}

	public void setInfoProvider(List<InfoProvider> infoProvider) {
		this.infoProvider = infoProvider;
	}

	public List<ProjectAttention> getProjectAttention() {
		return projectAttention;
	}

	public void setProjectAttention(List<ProjectAttention> projectAttention) {
		this.projectAttention = projectAttention;
	}

	public List<RelevanceProjects> getRelevanceProjects() {
		return relevanceProjects;
	}

	public void setRelevanceProjects(List<RelevanceProjects> relevanceProjects) {
		this.relevanceProjects = relevanceProjects;
	}

	
	
	
	public String getReminder_oa() {
		return reminder_oa;
	}

	public void setReminder_oa(String reminder_oa) {
		this.reminder_oa = reminder_oa;
	}

	public Date getReminder_expire() {
		return reminder_expire;
	}

	public void setReminder_expire(Date reminder_expire) {
		this.reminder_expire = reminder_expire;
	}

	public Integer getReminder_days() {
		return reminder_days;
	}

	public void setReminder_days(Integer reminder_days) {
		this.reminder_days = reminder_days;
	}

	public String getReminder_content() {
		return reminder_content;
	}

	public void setReminder_content(String reminder_content) {
		this.reminder_content = reminder_content;
	}

	


}
