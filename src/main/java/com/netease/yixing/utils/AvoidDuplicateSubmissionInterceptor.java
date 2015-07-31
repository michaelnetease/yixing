package com.netease.yixing.utils;


import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;



public class AvoidDuplicateSubmissionInterceptor<HandlerMethod> extends HandlerInterceptorAdapter {

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
			HttpSession session=request.getSession();
			System.out.println("+++++++++++++++"+session.getId()+"+++++++++++++++++");
			if(session.getAttribute("time")==null)
			{
				session.setAttribute("time", System.currentTimeMillis());
			}
			else
			{
				long temp=System.currentTimeMillis()-Long.parseLong((String.valueOf(session.getAttribute("time"))));
				session.setAttribute("time", System.currentTimeMillis());
				System.out.println("距离上次提交请求时间间隔    "+temp/1000+" s");
			}

			return true;
	}
	
    
}
