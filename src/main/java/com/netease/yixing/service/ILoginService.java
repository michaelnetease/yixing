package com.netease.yixing.service;

import java.util.List;

import com.netease.yixing.model.User;

public interface ILoginService {
	
	public List<User> queryUser(User user);
	
	public void register(User user) throws Exception;
	
	public User login(User user) throws Exception;
	
	public void loginout(User user) throws Exception;

}
