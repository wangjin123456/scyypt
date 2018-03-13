package com.scyypt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.scyypt.entity.ProjectProgress;

public interface ProjectProgressDao {
	/**
	 *新增项目进度
	 * @param projectProgress 项目进度类
	 * @return  大于0成功,等与0失败
	 */
	public int addProjectProgress(ProjectProgress projectProgress);
	/**
	 * 查询所有项目进度
	 * @return 项目进度集合
	 */
	public List<ProjectProgress> findAllProjectProgress();
	/**
	 * 
	 * @param projectProgress 项目进度类
	 * @return 大于0成功,等与0失败
	 */
	public int updateProjectProgress(ProjectProgress projectProgress);
	/**
	 * 删除项目进度
	 * @param progressId 项目进度编号
	 * @return 大于0成功,等与0失败
	 */
	public int delProjectProgress(@Param("progressId")String progressId);
}
