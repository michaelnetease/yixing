package com.netease.yixing.utils;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

@Service
public class YunxinService {
	 static String AppKey="f4e77b4a002beae6c45e2c240abd2e1d";
	 static String AppSecret="df3e9b36f2f7";
	 static String CreateURI="https://api.netease.im/nimserver/user/create.action";
	 static String UpdateURI="https://api.netease.im/nimserver/user/update.action";
	
	//字节码转换成16进制
	public static String bytesToHexString(byte[] src){  
	    StringBuilder stringBuilder = new StringBuilder("");  
	    if (src == null || src.length <= 0) {  
	        return null;  
	    }  
	    for (int i = 0; i < src.length; i++) {  
	        int v = src[i] & 0xFF;  
	        String hv = Integer.toHexString(v); 
	        if (hv.length() < 2) {  
	            stringBuilder.append(0);  
	        }  
	        stringBuilder.append(hv);  
	    }  
	    return stringBuilder.toString();  
	}  
	
	//发包
	public static String doPost(String url,Map<String,String> map,Map<String, String> head,String charset){  
        HttpClient httpClient = null;  
        HttpPost httpPost = null;  
        String result = null;  
        try{  
            httpClient = new SSLClient();  
            httpPost = new HttpPost(url);
            httpPost.setHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
            httpPost.setHeader("AppKey", head.get("AppKey"));
            httpPost.setHeader("Nonce", head.get("Nonce"));
            httpPost.setHeader("CurTime", head.get("CurTime"));
            httpPost.setHeader("CheckSum",head.get("CheckSum"));

            
            
            //设置参数   
            List<NameValuePair> list = new ArrayList<NameValuePair>();  
            Iterator iterator = map.entrySet().iterator();  
            while(iterator.hasNext()){  
                Entry<String,String> elem = (Entry<String, String>) iterator.next();  
                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));  
            }  
            if(list.size() > 0){  
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);  
                httpPost.setEntity(entity);  
            }  
            HttpResponse response = httpClient.execute(httpPost);  
            if(response != null){  
                HttpEntity resEntity = response.getEntity();  
                if(resEntity != null){  
                    result = EntityUtils.toString(resEntity,charset);  
                }  
            }  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
        return result;  
    }  

	public   String registerYunXing( String accid, String name, String token) throws NoSuchAlgorithmException, IOException {
		Map<String, String> head=new HashMap<String, String>();
		String key=AppKey;
		head.put("AppKey", key);
		int ran=123456;
		head.put("Nonce", String.valueOf(ran));
		long now=111111;
		head.put("CurTime", String.valueOf(now));
		String toSha=AppSecret+ran+""+now;
		MessageDigest md=MessageDigest.getInstance("sha-1");
		md.update(toSha.getBytes());
		byte[] shaRe=md.digest();
		String shaHX=YunxinService.bytesToHexString(shaRe);
		head.put("CheckSum", shaHX);
		Map<String, String> para=new HashMap<String,String>();
		para.put("accid", accid);
		para.put("name", name);
		para.put("token", token);
		String result=YunxinService.doPost(CreateURI, para, head, "utf-8");
		return result;
	}
	
	
	
	public  String updatePwd( String accid, String name, String token) throws NoSuchAlgorithmException, IOException {
		Map<String, String> head=new HashMap<String, String>();
		String key=AppKey;
		head.put("AppKey", key);
		int ran=123456;
		head.put("Nonce", String.valueOf(ran));
		long now=111111;
		head.put("CurTime", String.valueOf(now));
		String toSha=AppSecret+ran+""+now;
		MessageDigest md=MessageDigest.getInstance("sha-1");
		md.update(toSha.getBytes());
		byte[] shaRe=md.digest();
		String shaHX=YunxinService.bytesToHexString(shaRe);
		head.put("CheckSum", shaHX);
		Map<String, String> para=new HashMap<String,String>();
		para.put("accid", accid);
		para.put("name", name);
		para.put("token", token);
		String result=YunxinService.doPost(UpdateURI, para, head, "utf-8");
		System.out.println(result);
		return result;
	}
}
