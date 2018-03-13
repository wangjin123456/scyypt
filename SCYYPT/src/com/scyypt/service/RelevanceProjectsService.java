package com.scyypt.service;

import com.scyypt.entity.RelevanceProjects;

public interface RelevanceProjectsService {

	/**
	 * 添加关联项目数据库
	 * @param relevanceProjects 关联项目对象
	 * @return 大于0成功，等于0失败
	 */
	public Integer addRelevanceProjects(RelevanceProjects relevanceProjects);
}
