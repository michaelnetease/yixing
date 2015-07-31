package com.netease.yixing.service;

public interface ITokenHandlerService {

	public String generateGUID();
	
	public boolean validToken(String token);
	
}
