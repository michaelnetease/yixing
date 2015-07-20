package com.netease.yixing.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.netease.yixing.dao.ILoginDao;
import com.netease.yixing.model.User;

@Repository
public class LoginDao extends SqlSessionDaoSupport implements ILoginDao {

	@Override
	public List<User> getAllUser() {		
		System.out.println("mybatis");
		return getSqlSession().selectList("com.netease.yixing.model.User.getAllUser"); 
	}

	@Override
	public void insertUser(User user) {
		getSqlSession().insert("com.netease.yixing.model.User.insertUser",user);		
	}

	@Override
	public User queryUser(User user) {
		return getSqlSession().selectOne("com.netease.yixing.model.User.getUser", user);
	}

}
