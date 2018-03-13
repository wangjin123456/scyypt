/**
 * 
 */
package com.scyypt.entity;

import java.io.Serializable;

/**
 * 信息提供类
 * 
 * @author ChengChuanPing
 * @Time 2017年12月15日上午9:05:43
 */
public class InfoProvider implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int providerId;//提供者编号
	
	private String providerName;//提供者姓名
	
	private String providerTel;//提供者电话

	public InfoProvider() {
		super();
	}



	public InfoProvider(int providerId, String providerName, String providerTel) {
		super();
		this.providerId = providerId;
		this.providerName = providerName;
		this.providerTel = providerTel;
	}



	public int getProviderId() {
		return providerId;
	}



	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}



	public String getProviderName() {
		return providerName;
	}



	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}



	public String getProviderTel() {
		return providerTel;
	}



	public void setProviderTel(String providerTel) {
		this.providerTel = providerTel;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	
}
