package com.netease.yixing.utils;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.util.*;

public class PicService {
	
	Auth auth ;
	String ACCESS_KEY;
	String SECRET_KEY;
	String BUCKET;
	String PICDOMAIN;
	{
	    ACCESS_KEY=Constant.ACCESS_KEY;
	    SECRET_KEY=Constant.SECRET_KEY;
	    BUCKET=Constant.BUCKET;
	    PICDOMAIN=Constant.PICDOMAIN;
		auth= Auth.create(ACCESS_KEY, SECRET_KEY);
	}
	public String getUpToken(int seconds){
		return auth.uploadToken(BUCKET,null,seconds,null);
	}
	// 简单上传，使用默认策略
	private  String getUpToken0(){
	    return auth.uploadToken(BUCKET);
	}
	public String queryURL(String key){
		return PICDOMAIN+"/"+key;
	}
	public static void main(String args []){
		PicService ps = new PicService();
		System.out.println(ps.queryURL("1000adsf"));
	}
	// 覆盖上传
//	private String getUpToken1(String key){
//	    return auth.uploadToken(BUCKET, key);
//	}
//	// 设置指定上传策略
//	private String getUpToken2(){
//	    return auth.uploadToken(BUCKET, null, 3600, new StringMap()
//	         .put("callbackUrl", "call back url").putNotEmpty("callbackHost", "")
//	         .put("callbackBody", "key=$(key)&hash=$(etag)"));
//	}
//
//	// 设置预处理、去除非限定的策略字段
//	private String getUpToken3(){
//	    return auth.uploadToken(BUCKET, null, 3600, new StringMap()
//	            .putNotEmpty("persistentOps", "").putNotEmpty("persistentNotifyUrl", "")
//	            .putNotEmpty("persistentPipeline", ""), true);
//	}
//	
//	private String uploadToken(String bucket, String key, long expires, StringMap policy, boolean strict){
//		return "";
//	}

}
