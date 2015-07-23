package com.netease.yixing.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.netease.yixing.model.User;
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

	@RequestMapping(value="/login/test")
	public Map<String,Object> test(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		System.out.println("test ok!!!!!!!!!!!");
		return modelMap;
	}
	
	@RequestMapping(value="/login/register",method=RequestMethod.POST)
	public Map<String,Object> register(HttpServletRequest request, @RequestBody User user){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = true;
		String message = "ok";
		try{
			loginServ.register(user);
		}catch(Exception e){
			success = false;
			message = e.getMessage();
			e.printStackTrace();
		}
		
		//云信注册
		
		
		
		modelMap.put("success", success);
		modelMap.put("message", message);
		return modelMap;
	}
	
	@RequestMapping(value="/login/login",method=RequestMethod.POST)
	public Map<String,Object> login(HttpServletRequest request, @RequestBody User user){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = true;
		String message = "ok";
		User result = null;
		try{
			result = loginServ.login(user);
		}catch(Exception e){
			success = false;
			message = e.getMessage();
			e.printStackTrace();
		}
		modelMap.put("success", success);
		modelMap.put("message", message);
		modelMap.put("user", result);
		return modelMap;
	}
	

}
