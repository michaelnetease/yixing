package com.netease.yixing.dao;

import java.util.Map;

import com.netease.yixing.model.User;

public interface IMemberManageDao {

	public String getMembersByTravelId(int travelId) throws Exception;
	public void updateMembers(Map insertData) throws Exception;
	public String getPicByUserId(int userId) throws Exception;
	public User getUserById(int userId) throws Exception;
	
}
