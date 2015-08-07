package com.netease.yixing.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.netease.yixing.dao.IInvitationDao;
import com.netease.yixing.model.Invitation;
@Repository
public class InvitationDao   extends SqlSessionDaoSupport implements IInvitationDao{

	@Override
	public void insertInvitation(Invitation invitation) {
		// TODO Auto-generated method stub
		getSqlSession().insert("com.netease.yixing.model.Invitation.insertInvitation",invitation);
	}
	@Override
	public Invitation queryByRnd(String rnd) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("com.netease.yixing.model.Invitation.queryByRnd",rnd);
	}

	@Override
	public Invitation queryByTravelId(String travelId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("com.netease.yixing.model.Invitation.queryByTravelId",travelId);
	}

}
