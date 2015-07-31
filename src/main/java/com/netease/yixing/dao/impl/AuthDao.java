package com.netease.yixing.dao.impl;

import org.springframework.stereotype.Repository;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.netease.yixing.dao.IAuthDao;
import com.netease.yixing.model.Auth2;

@Repository
public class AuthDao  extends SqlSessionDaoSupport implements IAuthDao{

	@Override
	public Auth2 queryByUid(int uid) {
		// TODO Auto-generated method stub
		Auth2 auth=getSqlSession().selectOne("com.netease.yixing.model.Auth2.queryAuth", uid);
		return auth;
	}
	@Override
	public void insert(Auth2 auth) {
		// TODO Auto-generated method stub
		getSqlSession().insert("com.netease.yixing.model.Auth2.insertAuth", auth);
	}

	@Override
	public void update(Auth2 auth) {
		// TODO Auto-generated method stub
		getSqlSession().update("com.netease.yixing.model.Auth2.updateAuth",auth);	
	}
}
