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
		System.out.println(userList.size());
		return null;
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
	
	

	
}
