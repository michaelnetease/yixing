package com.netease.yixing.action;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import net.sf.*;
import net.sf.json.JSONObject;
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

	
	@RequestMapping(value="/login/register",method=RequestMethod.POST)
	public Map<String,Object> register(HttpServletRequest request, @RequestBody User user) throws NoSuchAlgorithmException, IOException{
		user.setToken(user.getPassword());
		user.setUsername(user.getPhoneNum());
		Map<String,Object> modelMap = new HashMap<String,Object>();
		try{
			loginServ.register(user);
		}catch(Exception e){
			modelMap.put("success", 0);
			modelMap.put("message","数据库中已有该用户信息");
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
			modelMap.put("success", 1);
			modelMap.put("message","注册成功");
		}
		else
		{
			try {
				loginServ.deleteUser(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
			modelMap.put("success", 0);
			modelMap.put("message","云信注册失败,用户信息从业务服务器中删除了");
			
		}
		
		return modelMap;
	}
	
	@RequestMapping(value="/login/login",method=RequestMethod.POST)
	public Map<String,Object> login(HttpServletRequest request, @RequestBody User user){
		System.out.println(user.getNickname()+"   "+user.getId());
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = true;
		String message = "ok";
		User result = null;
		try{
			
			result = loginServ.login(user);
			if(result==null){
				modelMap.put("success", false);
				modelMap.put("message", "用户名或密码错误");
				return modelMap;
			}
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
		String pn=String.valueOf(phoneNum.get("phoneNum"));
		User result = null;
		try{
			result = loginServ.selectUserByPhone(pn);
		}catch(Exception e){
			e.printStackTrace();
			modelMap.put("success", 0);
			modelMap.put("message", "查询数据出现异常");
		}
		if(result!=null)
		{
			modelMap.put("success", 1);
			modelMap.put("message", "Not exist");
		}
		else
		{
			modelMap.put("success", 1);
			modelMap.put("message", "Been existed");
		}
		return modelMap;
	}
	

	@RequestMapping(value="/login/updatenickname",method=RequestMethod.POST)
	public Map<String,Object> updatenickname(HttpServletRequest request, @RequestBody User user){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = true;
		String message = "isSuccess";
		try{
			
			loginServ.updatenickname(user);
		}catch(Exception e){
			success = false;
			message = e.getMessage();
			e.printStackTrace();
		}		
		modelMap.put("success", success);
		modelMap.put("message", message);
		return modelMap;
	}
	
	@RequestMapping(value="/login/updatelocation",method=RequestMethod.POST)
	public Map<String,Object> updatelocation(HttpServletRequest request, @RequestBody User user){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = true;
		String message = "isSuccess";
		try{
			
			loginServ.updatelocation(user);
		}catch(Exception e){
			success = false;
			message = e.getMessage();
			e.printStackTrace();
		}		
		modelMap.put("success", success);
		modelMap.put("message", message);
		return modelMap;
	}
	@RequestMapping(value="/login/updatesignature",method=RequestMethod.POST)
	public Map<String,Object> updatesignature(HttpServletRequest request, @RequestBody User user){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = true;
		String message = "isSuccess";
		try{
			
			loginServ.updatesignature(user);
		}catch(Exception e){
			success = false;
			message = e.getMessage();
			e.printStackTrace();
		}		
		modelMap.put("success", success);
		modelMap.put("message", message);
		return modelMap;
	}
	@RequestMapping(value="/login/updatepicId",method=RequestMethod.POST)
	public Map<String,Object> updatepicId(HttpServletRequest request, @RequestBody User user){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = true;
		String message = "isSuccess";
		try{
			
			loginServ.updatepicId(user);
		}catch(Exception e){
			success = false;
			message = e.getMessage();
			e.printStackTrace();
		}		
		modelMap.put("success", success);
		modelMap.put("message", message);
		return modelMap;
	}
	@RequestMapping(value="/login/updategender",method=RequestMethod.POST)
	public Map<String,Object> updategender(HttpServletRequest request, @RequestBody User user){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = true;
		String message = "isSuccess";
		try{			
			loginServ.updategender(user);
		}catch(Exception e){
			success = false;
			message = e.getMessage();
			e.printStackTrace();
		}		
		modelMap.put("success", success);
		modelMap.put("message", message);
		return modelMap;
	}
	@RequestMapping(value="/login/updatePass",method=RequestMethod.POST)
	public Map<String, Object> updatePass(HttpServletRequest request,@RequestBody User user){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = true;
		String message = "ok";
		try{
			loginServ.updatePass(user);
			System.out.println("ing");
		}catch(Exception e){
			success = false;
			message = e.getMessage();
			e.printStackTrace();
		}		
		modelMap.put("issuccess", success);
		modelMap.put("message", message);
		return modelMap;
	}
	
	@RequestMapping(value="/login/forgetPass",method=RequestMethod.POST)
	public Map<String, Object> forgetPass(HttpServletRequest request,@RequestBody JSONObject string) throws Exception{
		boolean nstep=true;
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success=false;
		String message="";
		String pass="";
		String phone="";
		JSONObject json;
		SmsVerifyKit smsV;
		try {
			json=JSONObject.fromObject(string);
			phone=json.getString("phone");
			String code=json.getString("code");	
			pass=json.getString("password");
			smsV=new SmsVerifyKit("9079ceadd30d", phone, "86", code);
			JSONObject tmp=JSONObject.fromObject(smsV.go());
			int temp=tmp.getInt("status");
			switch(temp){
			case 200:
				success=true;
				message="登陆成功";
				break;
			case 512:
				message="服务器拒绝访问，或者拒绝操作";
				break;
			case 513:
				message="求Appkey不存在或被禁用";
				break;
			case 514:
				message="权限不足";
				break;
			case 515:
				message="服务器内部错误";
				break;
			case 517:
				message="缺少必要的请求参数";
				break;
			case 518:
				message="请求中用户的手机号格式不正确（包括手机的区号）";
				break;
			case 519:
				message="请求发送验证码次数超出限制";
				break;
			case 520:
				message="无效验证码。";
				break;
			case 526:
				message="余额不足";
				break;
			default:
				message="未知错误";
				break;
			}			
		} catch (Exception e) {
			// TODO: handle exception
			success = false;
			message = e.getMessage();
			e.printStackTrace();
		}
		if (nstep) {
			User temp=new User();
			temp.setPhoneNum(phone);
			temp.setPassword(pass);
			Map<String, Object>tmp=updatePass(request, temp);
		}else {
			success=false;
		}
		new String(message.getBytes("UTF-8"));
		modelMap.put("success", success);
		modelMap.put("message", message);	
		return modelMap;
	}
	@RequestMapping(value="/login/smsVerify",method=RequestMethod.POST)
	public Map<String, Object> smsVerify(HttpServletRequest request,@RequestBody JSONObject string) throws Exception{
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success=false;
		String message="";
		String phone="";
		JSONObject json;
		SmsVerifyKit smsV;
		try {
			json=JSONObject.fromObject(string);
			phone=json.getString("phone");
			String code=json.getString("code");	
			smsV=new SmsVerifyKit("9079ceadd30d", phone, "86", code);
			JSONObject tmp=JSONObject.fromObject(smsV.go());
			int temp=tmp.getInt("status");
			switch(temp){
			case 200:
				success=true;
				message="登陆成功";
				break;
			case 512:
				message="服务器拒绝访问，或者拒绝操作";
				break;
			case 513:
				message="求Appkey不存在或被禁用";
				break;
			case 514:
				message="权限不足";
				break;
			case 515:
				message="服务器内部错误";
				break;
			case 517:
				message="缺少必要的请求参数";
				break;
			case 518:
				message="请求中用户的手机号格式不正确（包括手机的区号）";
				break;
			case 519:
				message="请求发送验证码次数超出限制";
				break;
			case 520:
				message="无效验证码。";
				break;
			case 526:
				message="余额不足";
				break;
			default:
				message="未知错误";
				break;
			}			
		} catch (Exception e) {
			// TODO: handle exception
			success = false;
			message = e.getMessage();
			e.printStackTrace();
		}
		new String(message.getBytes("UTF-8"));
		modelMap.put("success", success);
		modelMap.put("message", message);	
		return modelMap;
	}
	
	

}
