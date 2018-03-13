package com.scyypt.service;

import com.scyypt.entity.ProjectAttention;

public interface ProjectAttentionService {

	/**
	 * 新增项目关注人
	 * 
	 * @param projectAttention
	 *            项目关注人对象
	 * @return 大于0成功，小于0失败
	 */
	public Integer addProjectAttention(ProjectAttention projectAttention);
}
