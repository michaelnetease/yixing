package com.netease.yixing.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.netease.yixing.utils.Constant;
import com.netease.yixing.utils.PicService;

@Controller
@RequestMapping(value = "/picture")
public class PictureServiceCotroller {
	@RequestMapping(value = "/querytoken")
	public Map<String,Object> querytoken1(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> modelMap = new HashMap<String,Object>();
		try {
			response.setContentType("text/html;charset=utf-8");
			PicService ps = new PicService();
			String tt = ps.getUpToken(3600);
			modelMap.put("success", 1);
			modelMap.put("token", tt);
			return modelMap;
		} catch (Exception e) {
			modelMap.put("success", 0);
			return modelMap;
		}
	}
	
	@RequestMapping(value = "/querytoken2",method=RequestMethod.POST)
	public Map<String,Object> querytoken2(HttpServletRequest request, @RequestBody Map<String,Object> mp) {
		Map<String,Object> modelMap = new HashMap<String,Object>();
		try {
			String s =String.valueOf(mp.get("expires"));
			int expires = Integer.parseInt(s);
			PicService ps = new PicService();
			String tt = ps.getUpToken(expires);
			modelMap.put("success", 1);
			modelMap.put("token", tt);
			return modelMap;
		} catch (Exception e) {
			modelMap.put("success", 0);
			return modelMap;
		}
	}
	
	@RequestMapping(value = "/querydomain")
	public Map<String,Object> querydomain(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> modelMap = new HashMap<String,Object>();
		try {
			response.setContentType("text/html;charset=utf-8");
			PicService ps = new PicService();
			String tt = Constant.PICDOMAIN;
			modelMap.put("success", 1);
			modelMap.put("domain", tt);
			return modelMap;
		} catch (Exception e) {
			modelMap.put("success", 0);
			return modelMap;
		}
	}
}
