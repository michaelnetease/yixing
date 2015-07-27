package com.netease.yixing.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.yixing.dao.IMemberManageDao;
import com.netease.yixing.model.User;
import com.netease.yixing.service.IMemberManageService;

@Service
public class MemberManageService implements IMemberManageService {

	
	@Autowired
	private IMemberManageDao memberManageDao;
	
	


	public IMemberManageDao getMemberManageDao() {
		return memberManageDao;
	}

	public void setMemberManageDao(IMemberManageDao memberManageDao) {
		this.memberManageDao = memberManageDao;
	}

	@Override
	public String getMembersByTravelId(int travelId) throws Exception {
		
		return this.getMemberManageDao().getMembersByTravelId(travelId);
	}

	@Override
	public void updateMembers(Map insertData) throws Exception {
		this.getMemberManageDao().updateMembers(insertData);
		
	}

	@Override
	public String getPicByUserId(int userId) throws Exception {
		// TODO Auto-generated method stub
		return this.getMemberManageDao().getPicByUserId(userId);
	}
	@Override
	public User getUserById(int userId) throws Exception
	{
		return this.getMemberManageDao().getUserById(userId);
	}
	
	


	
	

	
}
