package com.netease.yixing.dao;

import java.util.List;

import com.netease.yixing.model.User;

public interface ILoginDao {

	public List<User> getAllUser();
	
	public int insertUser(User user);
	
	public User queryUser(User user);
	
	public void deleteUser(User user);
	
	public void updatePass(User user);
	
	public User selectUserByPhone(String phoneNum);
	
	public void updatenickname(User user);
	
	public void updatelocation(User user);
	
	public void updatesignature(User user);
	
	public void updategender(User user);
	
	public void updatepicId(User user);

	public User selectUserById(String id);
	
	public User selectUserByUsername(String username);
	
	public User queryUserById(int userId);
	
	public void updateJoinSchedule(User user);
	
	public List<User> queryMembersByIds(int[] userIds);
	
}
