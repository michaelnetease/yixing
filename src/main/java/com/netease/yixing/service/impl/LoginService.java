package com.netease.yixing.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.netease.yixing.dao.ILoginDao;
import com.netease.yixing.model.User;
import com.netease.yixing.service.ILoginService;

@Service
public class LoginService implements ILoginService {

	
	private ILoginDao loginDao;
	
	public ILoginDao getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(ILoginDao loginDao) {
		this.loginDao = loginDao;
	}

	@Override
	public List<User> queryUser(User user) {
		List<User> userList = loginDao.getAllUser();
		
		return userList;
	}

	@Override
	public void register(User user) throws Exception{
		loginDao.insertUser(user);		
	}

	@Override
	public User login(User user) {
		return loginDao.queryUser(user);

	}
	
	

	@Override
	public void deleteUser(User user) throws Exception {
		loginDao.deleteUser(user);
		
	}

	@Override
	public void loginout(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public User selectUserByPhone(String phoneNum) throws Exception {
		User u=loginDao.selectUserByPhone(phoneNum);
		return u;
	}
	
	@Override
	public void updatesignature(User user) throws Exception {
		loginDao.updatesignature(user);
		
	}

	@Override
	public void updatelocation(User user) throws Exception {
		// TODO Auto-generated method stub
		loginDao.updatelocation(user);
	}

	@Override
	public void updategender(User user) throws Exception {
		// TODO Auto-generated method stub
		loginDao.updategender(user);
	}

	@Override
	public void updatenickname(User user) throws Exception {
		// TODO Auto-generated method stub
		loginDao.updatenickname(user);
	}

	@Override
	public void updatepicId(User user) throws Exception {
		// TODO Auto-generated method stub
		loginDao.updatepicId(user);
	}

	@Override
	public User selectUserById(String id) throws Exception {
		// TODO Auto-generated method stub
		User u=loginDao.selectUserById(id);
		return u;
	}

	@Override
	public User selectUserByUsername(String username) throws Exception {
		// TODO Auto-generated method stub
		User u=loginDao.selectUserByUsername(username);
		return u;
	}
	
	@Override
	public void updatePass(User user) {
		loginDao.updatePass(user); 
	}
}
