package com.netease.yixing.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.netease.yixing.dao.ILoginDao;
import com.netease.yixing.model.User;

@Repository
public class LoginDao extends SqlSessionDaoSupport implements ILoginDao {

	@Override
	public User getUserById(User user) {		
		System.out.println("mybatis");
		return null;
		//return (User) getSqlSession().selectOne("com.xxt.ibatis.dbcp.domain.User.getUser", user); 
	}

}
