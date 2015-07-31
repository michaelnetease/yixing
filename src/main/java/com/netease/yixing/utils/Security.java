package com.netease.yixing.utils;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Formatter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Security {
	private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";  
    private static String toHexString(byte[] bytes) {        
        Formatter formatter = new Formatter();                
        for (byte b : bytes) {           
            formatter.format("%02x", b);
        }        
        return formatter.toString(); 
   }  
    
   public static String HMAC_SHA1(String data, String key) throws SignatureException, NoSuchAlgorithmException, InvalidKeyException   {       
       SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);     
       Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);      
       mac.init(signingKey);       
       return toHexString(mac.doFinal(data.getBytes()));  
    }    
     
   
   public final static String MD5(String s) {
       char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       

       try {
           byte[] btInput = s.getBytes();
           // 获得MD5摘要算法的 MessageDigest 对象
           MessageDigest mdInst = MessageDigest.getInstance("MD5");
           // 使用指定的字节更新摘要
           mdInst.update(btInput);
           // 获得密文
           byte[] md = mdInst.digest();
           // 把密文转换成十六进制的字符串形式
           int j = md.length;
           char str[] = new char[j * 2];
           int k = 0;
           for (int i = 0; i < j; i++) {
               byte byte0 = md[i];
               str[k++] = hexDigits[byte0 >>> 4 & 0xf];
               str[k++] = hexDigits[byte0 & 0xf];
           }
           return new String(str);
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
   }
    public static void main(String[] args) throws Exception {      
        String hmac = HMAC_SHA1("data", "nihao");       
        System.out.println(hmac);     
        String ans = MD5("haha");
        System.out.println(ans);
    }
    
    
    
}
