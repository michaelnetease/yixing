package com.netease.yixing.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public class Constant {
	public	static String ACCESS_KEY;
	public	static String SECRET_KEY;
	public	static String BUCKET ;
	public	static String PICDOMAIN ;
	public  static String  LOGINCONSTANT;
	
    public static final String DEFAULT_TOKEN_NAME = "Dup.token";
    
    public static final String TOP_K_VISIT_TRAVEL_SCHEDULE = "Top.K.Visit.Schedule";
    
    public static final String TOP_K_MARK_TRAVEL_SCHEDULE = "Top.K.Mark.Schedule";
    
    public static int TOP_K_TRAVEL_SCHEDULE = 5;
	
	static{
		String path = Thread.currentThread().getContextClassLoader().getResource("/").getPath()+"com/netease/yixing/utils/QiNiuConfig";
		System.out.println(path);
	    Properties p = new Properties();   
	    try {   
	       p.load(new FileInputStream(path));   
	    } catch (IOException e1) {   
	       e1.printStackTrace();   
	    }
	    ACCESS_KEY=p.getProperty("ACCESS_KEY");
	    SECRET_KEY=p.getProperty("SECRET_KEY");
	    BUCKET=p.getProperty("BUCKET");
	    PICDOMAIN=p.getProperty("PICDOMAIN");
	    LOGINCONSTANT=p.getProperty("LOGINCONSTANT");
	}
}
