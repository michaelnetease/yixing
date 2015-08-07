package com.netease.yixing.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netease.yixing.service.ITokenHandlerService;

@Controller
public class TokenController {

	@Autowired
	private ITokenHandlerService tokenServ;
	
	public ITokenHandlerService getTokenServ() {
		return tokenServ;
	}

	public void setTokenServ(ITokenHandlerService tokenServ) {
		this.tokenServ = tokenServ;
	}

	@RequestMapping(value = "/generate", method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> generateToken(HttpServletRequest request) {     
		Map<String,Object> modelMap = new HashMap<String,Object>();		
        boolean success = true;
        String message = "ok";
        String token = null;
		try{
			token = tokenServ.generateGUID();
        }catch(Exception e){
        	success = false;
        	message = e.getMessage();
        	e.printStackTrace();
        }finally{
        	modelMap.put("success", success);
        	modelMap.put("message", message);
        	modelMap.put("token", token);
        }
		
		return modelMap;
    }
	
	
	
}
