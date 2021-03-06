package com.netease.yixing.service;

import java.util.List;
import java.util.Map;

import com.netease.yixing.model.User;

public interface IMemberManageService {
	
	public String getMembersByTravelId(int travelId) throws Exception;
	public void updateMembers(Map insertData) throws Exception;
	public String getPicByUserId(int userId) throws Exception;
	public User getUserById(int userId) throws Exception;
	public void addMember(int userId,int schedule_id) throws Exception;

	public List<User> queryLatestScheduleMembersByUserId(int userId) throws Exception;
}
