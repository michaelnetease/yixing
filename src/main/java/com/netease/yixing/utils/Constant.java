package com.netease.yixing.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;



public class Constant {
	public	static String ACCESS_KEY;
	public	static String SECRET_KEY;
	public	static String BUCKET ;
	public	static String PICDOMAIN ;
	public  static String  LOGINCONSTANT;
	public  static  String ICON;
	
	//以下五项为备忘录专用
	public  static  String WENJIANZILIAO;
	public  static  String SHENGHUOYONGPIN;
	public  static  String GONGJU;
	public  static  String YAOPIN;
	public  static  String ZIDINGYI;
	
    
    public static final String TOP_K_MARK_TRAVEL_SCHEDULE = "Top.K.Mark.Schedule";
    
    public static int TOP_K_TRAVEL_SCHEDULE = 5;
	
    public static final String DEFAULT_TOKEN_NAME = "Dup.token";
    
    public static final String TOP_K_VISIT_TRAVEL_SCHEDULE = "Top.K.Visit.Schedule";
    
    public static final String SUCCESS = "success";
    
    public static final String MESSAGE = "message";
    
    public static final String SUCCESS_MESSAGE = "ok";
    
    
	public static String HANDLERMAP;
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
	    HANDLERMAP=p.getProperty("HANDLERMAP");
	    LOGINCONSTANT=p.getProperty("LOGINCONSTANT");
	    ICON=p.getProperty("ICON");
	    
	  //以下五项为备忘录专用
		WENJIANZILIAO="身份证;;;学生证;;;护照;;;信用卡;;;人民币;;;美元;;;当地货币;;;机票";
		SHENGHUOYONGPIN="化妆品;;;毛巾;;;泳衣;;;旅行枕;;;隐形眼镜;;;纸巾;;;湿巾;;;帽子;;;塑料袋;;;雨衣;;;泳镜;;;雨伞;;;睡袋;;;运动鞋;;;短裤;;;沙滩裤";
		GONGJU="灭蚊器;;;充电器;;;转换器;;;电扇;;;数据卡;;;电话卡;;;备用电池;;;数码相机";
		YAOPIN="消炎药;;;防蚊水;;;晕车药;;;止痛片;;;创可贴;;;止泻药;;;感冒药;;;防暑药";
		ZIDINGYI="";

	}
}
