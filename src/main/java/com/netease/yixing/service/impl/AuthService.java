package com.netease.yixing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.yixing.dao.IAuthDao;
import com.netease.yixing.model.Auth2;
import com.netease.yixing.service.IAuthService;
@Service
public class AuthService implements IAuthService{

	@Autowired
	private IAuthDao authDao;
	

	@Override
	public Auth2 queryByUid(int uid) {
		// TODO Auto-generated method stub
		return authDao.queryByUid(uid);
	}

	@Override
	public void insert(Auth2 auth) {
		// TODO Auto-generated method stub
		authDao.insert(auth);
	}

	@Override
	public void update(Auth2 auth) {
		// TODO Auto-generated method stub
		authDao.update(auth);
	}

}
