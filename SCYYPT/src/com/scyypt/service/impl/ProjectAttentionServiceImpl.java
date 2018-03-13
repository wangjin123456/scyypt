package com.scyypt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scyypt.dao.ProjectAttentionDao;
import com.scyypt.entity.ProjectAttention;
import com.scyypt.service.ProjectAttentionService;

@Service("projectAttentionService")
public class ProjectAttentionServiceImpl implements ProjectAttentionService {

	@Autowired
	private ProjectAttentionDao projectAttentionDao;

	@Override
	public Integer addProjectAttention(ProjectAttention projectAttention) {
		return projectAttentionDao.addProjectAttention(projectAttention);
	}

}
