package com.netease.yixing.dao;

import java.util.HashMap;

public interface ITokenRedisDao {

	public Object getTokenName(String tokenName);
	
	public boolean saveToken(String tokenName, HashMap<String,String> tokenKeyValMap);
	
}
