package com.netease.yixing.intercepator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.netease.yixing.service.ITokenHandlerService;
import com.netease.yixing.utils.Constant;

public class TokenValidInterceptor implements HandlerInterceptor{
 
	@Autowired
	private ITokenHandlerService tokenServ;
	
    public ITokenHandlerService getTokenServ() {
		return tokenServ;
	}

	public void setTokenServ(ITokenHandlerService tokenServ) {
		this.tokenServ = tokenServ;
	}

	public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object arg2, Exception arg3)
            throws Exception {
    }
 
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
            Object arg2, ModelAndView arg3) throws Exception {
         
    }
    
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object arg2) throws Exception {
    	String token = request.getHeader(Constant.DEFAULT_TOKEN_NAME);
        if(!tokenServ.validToken(token)){
            //response.sendRedirect(Constants.DEFAULT_TOKEN_MSG_JSP);
            return false;
        }
        return true;
    }
 
}