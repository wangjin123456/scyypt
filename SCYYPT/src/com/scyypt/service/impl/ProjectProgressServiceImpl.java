package com.scyypt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scyypt.dao.ProjectProgressDao;
import com.scyypt.entity.ProjectProgress;
import com.scyypt.service.ProjectProgressService;

@Service("ProjectProgressService")
public class ProjectProgressServiceImpl implements ProjectProgressService {
	@Autowired
	private  ProjectProgressDao projectProgressDao;
	
	
	@Override
	public int addProjectProgress(ProjectProgress projectProgress) {
		return 0;
	}

	@Override
	public List<ProjectProgress> findAllProjectProgress() {
		return projectProgressDao.findAllProjectProgress();
	}

	@Override
	public int updateProjectProgress(ProjectProgress projectProgress) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delProjectProgress(String progressId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
