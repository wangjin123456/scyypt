package com.scyypt.dao;

import com.scyypt.entity.RelevanceProjects;

public interface RelevanceProjectsDao {
	/**
	 * 添加关联项目数据库
	 * @param relevanceProjects 关联项目对象
	 * @return 大于0成功，等于0失败
	 */
	public Integer addRelevanceProjects(RelevanceProjects relevanceProjects);

}
