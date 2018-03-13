package com.scyypt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scyypt.dao.AuthorityDao;
import com.scyypt.entity.Authority;
import com.scyypt.service.AuthorityService;

@Service("AuthorityService")
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	private AuthorityDao authorityDao;
	
	
	@Override
	public List<Authority> getAllAuthority() {
		return authorityDao.getAllAuthority();
	}


	
	@Override
	public int addAuthority(Authority authority) {
		return authorityDao.addAuthority(authority);
	}


	@Override
	public int updateAuthority(Authority authority) {
		return authorityDao.updateAuthority(authority);
	}


	@Override
	public int delAuthority(String authority_id) {
		return authorityDao.delAuthority(authority_id);
	}



	@Override
	public List<Authority> findAuthority(String authority_id) {
		return authorityDao.findAuthority(authority_id);
	}



}
