package com.scyypt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scyypt.dao.RelevanceProjectsDao;
import com.scyypt.entity.RelevanceProjects;
import com.scyypt.service.RelevanceProjectsService;

@Service("relevanceProjects")
public class RelevanceProjectsServicImpl implements RelevanceProjectsService {

	@Autowired
	private RelevanceProjectsDao relevanceProjectsDao;
	@Override
	public Integer addRelevanceProjects(RelevanceProjects relevanceProjects) {
		return relevanceProjectsDao.addRelevanceProjects(relevanceProjects);
	}

}
