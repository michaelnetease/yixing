package com.netease.yixing.service;

import java.util.List;

import com.netease.yixing.model.User;

public interface ILoginService {
	
	public List<User> queryUser(User user);
	
	public void register(User user) throws Exception;
	
	public User login(User user) throws Exception;
	
	public void loginout(User user) throws Exception;
	
	public void deleteUser(User user) throws Exception;
	
	public User selectUserByPhone(String phoneNum) throws Exception;
	
	public void updatesignature(User user) throws Exception;
    
	public void updatelocation(User user) throws Exception;
	
	public void updategender(User user) throws Exception;
	
	public void updatenickname(User user) throws Exception;
	
	public void updatepicId(User user) throws Exception;
	
	public User selectUserById(String id) throws Exception;
	
	public User selectUserByUsername(String username) throws Exception;
	
	public void updatePass(User user) throws Exception;
}
