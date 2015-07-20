package com.netease.yixing.dao;

import java.util.List;

import com.netease.yixing.model.User;

public interface ILoginDao {

	public List<User> getAllUser();
	
	public void insertUser(User user);
	
	public User queryUser(User user);
	
}
