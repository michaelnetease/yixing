package com.netease.yixing.dao.impl;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.netease.yixing.dao.IMemberManageDao;
import com.netease.yixing.model.User;

@Repository
public class MemberManageDao extends SqlSessionDaoSupport implements IMemberManageDao {

	@Override
	public String getMembersByTravelId(int travelId) throws Exception {
		
		return getSqlSession().selectOne("com.netease.yixing.model.Members.selectMembers", travelId);
	}

	@Override
	public void updateMembers(Map insertData) throws Exception {
		getSqlSession().update("com.netease.yixing.model.Members.updateMembers", insertData);

	}

	@Override
	public String getPicByUserId(int userId) throws Exception {
		return getSqlSession().selectOne("com.netease.yixing.model.Members.getPicByUserId", userId);
	}
	
	@Override
	public User getUserById(int userId) throws Exception
	{
		return getSqlSession().selectOne("com.netease.yixing.model.Members.getUserById",userId);
	}

}
