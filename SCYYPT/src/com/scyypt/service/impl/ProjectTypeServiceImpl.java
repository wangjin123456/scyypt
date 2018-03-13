package com.scyypt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scyypt.dao.ProjectTypeDao;
import com.scyypt.entity.ProjectType;
import com.scyypt.service.ProjectTypeService;
@Service("ProjectTypeService")
public class ProjectTypeServiceImpl implements ProjectTypeService {
	@Autowired
	private ProjectTypeDao project_TypeDao;
	@Override
	public Integer addProject_Type(ProjectType project_Type) {
		return project_TypeDao.addProject_Type(project_Type);
	}

	@Override
	public List<ProjectType> findAll() {
		return project_TypeDao.findAll();
	}

	@Override
	public Integer del(String projectType_id) {
		return project_TypeDao.del(projectType_id);
	}

	@Override
	public Integer updateProject_Type(ProjectType project_Type) {
		return project_TypeDao.updateProject_Type(project_Type);
	}

}
