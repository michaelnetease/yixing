package com.netease.yixing.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.netease.yixing.model.Auth2;
import com.netease.yixing.model.User;
import com.netease.yixing.service.IAuthService;
import com.netease.yixing.service.ILoginService;
import com.netease.yixing.work.redis.RedisClientTemplate;

@Service
public class UserCheck {
	@Autowired
	private RedisClientTemplate redisClient;
	
	@Autowired
	private ILoginService loginServ;
	
	@Autowired
	private IAuthService authService;
	
	
	//token
	private String getRandom(String key){
		Date dt = new Date();
		String s = dt.getTime()+"";
		String t = s+Security.MD5(key);
		return Security.MD5(t);
	}
	public String queryUserToken(int id){
		String uid = "USER.ID."+id;
		if(redisClient.exists(uid)){
			String token = redisClient.get(uid);
			return token;
		}else{
			Auth2 au = authService.queryByUid(id);
			
			if(au==null){
				try {
					User u = loginServ.selectUserById(id+"");
					Auth2 tmp = new Auth2();
					tmp.setUid(u.getId()+"");
					String rnd =getRandom(u.getPassword());
					tmp.setRnd(rnd);
					String token = getRandom(u.getPassword()+u.getId());
					tmp.setAccessToken(token);
					tmp.setValidTime("");
					authService.insert(tmp);
					redisClient.set(uid, token);
					return token;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "";
				}
			}else{
				String token = au.getAccessToken();
				redisClient.set(uid, token);
				return token;
			}
		}
		
	}
	public boolean  checkUserToken(int id,String accessToken){
		String token = queryUserToken(id);
		if(token.equals(accessToken))return true;
		return false;
	}
	public  void  changeAccessToken(int id){
		Auth2 au = authService.queryByUid(id);
		String token = null;
		try {
			User u = loginServ.selectUserById(id+"");
			if(u!=null){
				token = getRandom(u.getPassword());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(token==null){
			token = getRandom(id+"");
		}
		au.setAccessToken(token);
		authService.update(au);
		String uid = "USER.ID."+id;
		redisClient.set(uid,token);
	}
	
	//user login
	public boolean checkUserPasswordByMD5(String username , String password){
		
		try {
			User u = loginServ.selectUserByUsername(username);
			if(u==null){
				return false;
			}else{
				if(u.getPassword().equals(password)){
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public User checkUserPasswordByRandom(String username,String password){
		try {
			User u = loginServ.selectUserByUsername(username);
			if(u==null){
				return null;
			}else{
				Auth2 au = authService.queryByUid(u.getId());
				if(au==null){
					queryLoginRandom(u.getUsername());
					au = authService.queryByUid(u.getId());
				}
				String rnd = au.getRnd();
				String tmp = Security.MD5(u.getPassword()+rnd);
				if(tmp.equals(password)){
					au.setRnd(getRandom(u.getPassword()));
					authService.update(au);
					return u;
				}
				return null;
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public String queryLoginRandom(String username){
		try {
			User u = loginServ.selectUserByUsername(username);
			if(u==null){
				return null;
			}
			Auth2 au = authService.queryByUid(u.getId());
			if(au==null){
				Auth2 tmp = new Auth2();
				tmp.setUid(u.getId()+"");
				String rnd =getRandom(u.getPassword());
				tmp.setRnd(rnd);
				String token = getRandom(u.getPassword()+u.getId());
				tmp.setAccessToken(token);
				tmp.setValidTime("");
				authService.insert(tmp);
				return  rnd;
			}else{
				return au.getRnd();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public boolean checkUserPasswordByConstant(String username , String password){
		String pwdConstant = Constant.LOGINCONSTANT;
		try {
			User u = loginServ.selectUserByUsername(username);
			String tmp = Security.MD5(u.getPassword()+pwdConstant);
			if(tmp.equals(password)){
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return false;
	}
	public String queryLoginConstant(){
		return Constant.LOGINCONSTANT;
	} 
}
