package com.netease.yixing.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.netease.yixing.dao.ILoginDao;
import com.netease.yixing.model.User;

@Repository
public class LoginDao extends SqlSessionDaoSupport implements ILoginDao {

	@Override
	public List<User> getAllUser() {		
		System.out.println("mybatis");
		return getSqlSession().selectList("com.netease.yixing.model.User.getAllUser"); 
	}

	@Override
	public int insertUser(User user) {
		return getSqlSession().insert("com.netease.yixing.model.User.insertUser",user);		
	}

	@Override
	public User queryUser(User user) {
		return getSqlSession().selectOne("com.netease.yixing.model.User.getUser", user);
	}
	
	@Override
	public void deleteUser(User user){
		getSqlSession().delete("com.netease.yixing.model.User.deleteUser", user);
	}
	
	@Override
	public User selectUserById(String id)
	{
		User u=getSqlSession().selectOne("com.netease.yixing.model.User.selectUserById", id);
		return u;
	}
	
	@Override
	public User selectUserByPhone(String phoneNum)
	{
		User u=getSqlSession().selectOne("com.netease.yixing.model.User.selectUserByPhone", phoneNum);
		return u;
	}

	@Override
	public User queryUserById(int userId) {
		return getSqlSession().selectOne("com.netease.yixing.model.User.getUserById", userId);
	}
	
	@Override
	public void updatesignature(User user) {
		getSqlSession().update("com.netease.yixing.model.User.updatesignature",user);	
	}

	@Override
	public void updatenickname(User user) {
		// TODO Auto-generated method stub
		getSqlSession().update("com.netease.yixing.model.User.updatenickname",user);
	}

	@Override
	public void updatelocation(User user) {
		// TODO Auto-generated method stub
		getSqlSession().update("com.netease.yixing.model.User.updatelocation",user);
	}

	@Override
	public void updategender(User user) {
		// TODO Auto-generated method stub
		getSqlSession().update("com.netease.yixing.model.User.updategender",user);
	}

	@Override
	public void updatepicId(User user) {
		// TODO Auto-generated method stub
		getSqlSession().update("com.netease.yixing.model.User.updatepicId",user);
	}

	@Override
	public User selectUserByUsername(String username) {
		// TODO Auto-generated method stub
		User u=getSqlSession().selectOne("com.netease.yixing.model.User.selectUserByUsername", username);
		return u;
	}
	@Override
	public void updatePass(User user){
		getSqlSession().update("com.netease.yixing.model.User.updatePass", user);
	}
	

	@Override
	public void updateJoinSchedule(User user) {
		getSqlSession().update("com.netease.yixing.model.User.updateJoinSchedule", user);
		
	}

	@Override
	public List<User> queryMembersByIds(int[] userIds) {
		return getSqlSession().selectList("com.netease.yixing.model.User.selectMembersByIds", userIds);
	}
	
	
}
