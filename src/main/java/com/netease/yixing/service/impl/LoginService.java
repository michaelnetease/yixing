package com.netease.yixing.service.impl;

import org.springframework.stereotype.Service;

import com.netease.yixing.dao.ILoginDao;
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

	public void test(){
		loginDao.getUserById(null);
		System.out.println("hello");
	}
	
}
