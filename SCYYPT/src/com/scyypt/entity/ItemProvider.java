package com.scyypt.entity;

import java.io.Serializable;

/**
 * 项目关系实体类
 * @author ChengChuanPing
 * @Time   2017年12月15日下午3:02:27
 */
public class ItemProvider implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer Id;//关联关系编号
	
	private Integer itemId;//关联项目编号
	
	private Integer providerId;//关联信息提供者编号
	
	private Integer attentionId;	//项目关注人编号
	private Integer relevanceId;	//关联项目编号
	private Integer followId;	//项目跟进编号


	public ItemProvider() {
		super();
	}



	public ItemProvider(Integer id, Integer itemId, Integer providerId, Integer attentionId, Integer relevanceId,
			Integer followId) {
		super();
		Id = id;
		this.itemId = itemId;
		this.providerId = providerId;
		this.attentionId = attentionId;
		this.relevanceId = relevanceId;
		this.followId = followId;
	}



	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getProviderid() {
		return providerId;
	}

	public void setProviderid(Integer providerid) {
		this.providerId = providerid;
	}

	public Integer getProviderId() {
		return providerId;
	}

	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}

	public Integer getAttentionId() {
		return attentionId;
	}

	public void setAttentionId(Integer attentionId) {
		this.attentionId = attentionId;
	}

	public Integer getRelevanceId() {
		return relevanceId;
	}

	public void setRelevanceId(Integer relevanceId) {
		this.relevanceId = relevanceId;
	}

	public Integer getFollowId() {
		return followId;
	}

	public void setFollowId(Integer followId) {
		this.followId = followId;
	}

	
	
	
}
