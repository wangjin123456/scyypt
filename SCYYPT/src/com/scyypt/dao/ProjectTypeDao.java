package com.scyypt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.scyypt.entity.ProjectType;

public interface ProjectTypeDao {
	/**
	 * 增加项目类型
	 * 
	 * @param project_Type
	 * @return
	 */
	public Integer addProject_Type(ProjectType project_Type);
	/**
	 * 查询所有项目类型列表
	 * @return Project_Type集合
	 */
	public List<ProjectType> findAll();
	/**
	 * 删除项目类型
	 * @param id 项目类型编号
	 * @return
	 */
	public Integer del(@Param("projectType_id") String projectType_id);
	/**
	 * 修改项目类型
	 * @param project_Type
	 * @return
	 */
	public Integer updateProject_Type(ProjectType project_Type); 
		
	

}
