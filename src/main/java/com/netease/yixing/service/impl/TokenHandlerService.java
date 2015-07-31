package com.netease.yixing.service.impl;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.yixing.dao.ITokenRedisDao;
import com.netease.yixing.service.ITokenHandlerService;
import com.netease.yixing.utils.Constant;

@Service
public class TokenHandlerService implements ITokenHandlerService{
	
	private static Logger logger = Logger.getLogger(TokenHandlerService.class);
	 
    static HashMap<String, String> springmvc_token = null;
    
    @Autowired
    private ITokenRedisDao tokenRedisDao;
     
    public ITokenRedisDao getTokenRedisDao() {
		return tokenRedisDao;
	}

	public void setTokenRedisDao(ITokenRedisDao tokenRedisDao) {
		this.tokenRedisDao = tokenRedisDao;
	}

	@SuppressWarnings("unchecked")
    public synchronized String generateGUID() {
        String token = "";
        try {
            //Object obj =  session.getAttribute(DEFAULT_TOKEN_NAME);
            Object obj = tokenRedisDao.getTokenName(Constant.DEFAULT_TOKEN_NAME);
            if(obj!=null)
                springmvc_token = (HashMap<String,String>)obj;
            else
               springmvc_token = new HashMap<String, String>();
            token = new BigInteger(165, new Random()).toString(36).toUpperCase();
            springmvc_token.put(Constant.DEFAULT_TOKEN_NAME +"."+ token, token);
                        
            //session.setAttribute(DEFAULT_TOKEN_NAME, springmvc_token);
            tokenRedisDao.saveToken(Constant.DEFAULT_TOKEN_NAME, springmvc_token);
        } catch (IllegalStateException e) {
            logger.error("generateGUID() mothod find bug,by token session...");
        }
        return token;
    }
 

    @SuppressWarnings("unchecked")
    public boolean validToken(String inputToken) {
        if (inputToken == null) {
            logger.warn("token is not valid!inputToken is NULL");
            return false;
        }
 
        HashMap<String, String> tokenMap =(HashMap<String, String>)  tokenRedisDao.getTokenName(Constant.DEFAULT_TOKEN_NAME);
        //Map<String, String> tokenMap = (Map<String, String>) session.getAttribute(DEFAULT_TOKEN_NAME);
        if (tokenMap == null || tokenMap.size() < 1) {
            logger.warn("token is not valid!redisToken is NULL");
            return false;
        }
        String redisToken = tokenMap.get(Constant.DEFAULT_TOKEN_NAME+ "." + inputToken);
        if (!inputToken.equals(redisToken)) {
            logger.warn("token is not valid!inputToken='" + inputToken
                    + "',redisToken = '" + redisToken + "'");
            return false;
        }
        tokenMap.remove(Constant.DEFAULT_TOKEN_NAME + "." + inputToken);
        tokenRedisDao.saveToken(Constant.DEFAULT_TOKEN_NAME, tokenMap);
        //session.setAttribute(DEFAULT_TOKEN_NAME, tokenMap);
        return true;
    }
 
}
