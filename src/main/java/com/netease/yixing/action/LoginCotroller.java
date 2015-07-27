package com.netease.yixing.action;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
import com.netease.yixing.utils.YunxinService;

@Controller
public class LoginCotroller {

	@Autowired
	private ILoginService loginServ;
	
	public ILoginService getLoginServ() {
		return loginServ;
	}
	
	@Autowired
	private YunxinService yxs;

	public YunxinService getYxs() {
		return yxs;
	}

	public void setYxs(YunxinService yxs) {
		this.yxs = yxs;
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
	public Map<String,Object> register(HttpServletRequest request, @RequestBody User user) throws NoSuchAlgorithmException, IOException{
		System.out.println("********************测试注册功能**********************");
		user.setToken(user.getPassword());
		user.setUsername(user.getPhoneNum());
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = true;
		String message = "ok";
		try{
		
			loginServ.register(user);
			System.out.println("刚在数据库添加了数据");
		}catch(Exception e){
			success = false;
			message = e.getMessage();
			e.printStackTrace();
			return modelMap;
		}
		
		//云信注册
		String accid=user.getPhoneNum();
		String name=user.getNickname();
		String token=user.getPassword();
		String resultYunxin=yxs.registerYunXing(accid, name, token);
		if(resultYunxin.contains("\"code\":200"))
		{
			modelMap.put("success", success);
			modelMap.put("message", "注册成功");
		}
		else
		{
			try {
				loginServ.deleteUser(user);
				System.out.println("从数据库删除刚添加的数据");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			modelMap.put("success", false);
			modelMap.put("message","云信注册失败,用户信息从业务服务器中删除了");
			
		}
		
		return modelMap;
	}
	
	@RequestMapping(value="/login/login",method=RequestMethod.POST)
	public Map<String,Object> login(HttpServletRequest request, @RequestBody User user){
		System.out.println("*************************************测试登录功能***********************************");
		System.out.println(user.getNickname()+"   "+user.getId());
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
		//modelMap.put("user", result);
		return modelMap;
	}
	
	
	
	@RequestMapping(value="/login/isUserExist",method=RequestMethod.POST)
	public Map<String,Object> isUserExist(HttpServletRequest request, @RequestBody Map phoneNum){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		String pn=(String)phoneNum.get("phoneNum");
		boolean success = true;
		String message = "Not exist";
		User result = null;
		try{
			result = loginServ.selectUserByPhone(pn);
		}catch(Exception e){
			success = false;
			message = e.getMessage();
			e.printStackTrace();
			modelMap.put("success", false);
		}
		if(result!=null)
		{
			modelMap.put("success", success);
			modelMap.put("message", message);
		}
		else
		{
			modelMap.put("success", false);
			modelMap.put("message", "Been existed");
		}
		return modelMap;
	}

}
