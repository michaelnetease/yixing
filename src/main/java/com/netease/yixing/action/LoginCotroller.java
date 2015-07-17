package com.netease.yixing.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.netease.yixing.service.ILoginService;

@Controller
public class LoginCotroller {

	@Autowired
	private ILoginService loginServ;
	
	public ILoginService getLoginServ() {
		return loginServ;
	}

	public void setLoginServ(ILoginService loginServ) {
		this.loginServ = loginServ;
	}



	@RequestMapping(value="/login")
	public void add(){
		System.out.println("value");
		loginServ.test();
	}
	
}
