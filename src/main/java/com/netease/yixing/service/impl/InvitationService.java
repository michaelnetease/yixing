package com.netease.yixing.service.impl;

import java.util.Date;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.yixing.dao.IInvitationDao;
import com.netease.yixing.model.Invitation;
import com.netease.yixing.service.IInvitationService;
import com.netease.yixing.utils.Security;


@Service
public class InvitationService implements IInvitationService{
	@Autowired
	private IInvitationDao invitationDao;
	@Override
	public void insertInvitation(Invitation invitation) {
		// TODO Auto-generated method stub
		invitationDao.insertInvitation(invitation);
	}

	@Override
	public Invitation queryByRnd(String rnd) {
		// TODO Auto-generated method stub
		return invitationDao.queryByRnd(rnd);
	}

	@Override
	public Invitation queryByTravelId(String travelId) {
		// TODO Auto-generated method stub
		return invitationDao.queryByTravelId(travelId);
	}
	
	public void insertInvitation(int travelId){
		Invitation  it = new Invitation();
		it.setTravelId(travelId+"");
		Random rd = new Random();
		long lnum = rd.nextLong();
		String rnd = getRandom(travelId+lnum+"");
		it.setRnd(rnd);
		insertInvitation(it);	
	}
	private String getRandom(String key){
		Date dt = new Date();
		String s = dt.getTime()+"";
		String t = s+Security.MD5(key);
		return Security.MD5(t);
	}
}
