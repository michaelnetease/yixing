package com.netease.yixing.action;


import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.netease.yixing.model.Equipment;
import com.netease.yixing.model.TravelRecord;
import com.netease.yixing.model.User;
import com.netease.yixing.service.ILoginService;
import com.netease.yixing.service.ITravelRecordService;
import com.netease.yixing.utils.Constant;
import com.netease.yixing.utils.PicService;
import com.netease.yixing.work.redis.RedisClientTemplate;


@Controller
@RequestMapping(value = "/record")
public class TravelRecordCotroller {

	@Autowired
	private ITravelRecordService travelRecordService;
	
	@Autowired
	private RedisClientTemplate redisClient;
	private Logger logger = Logger.getLogger("TravelRecordCotroller.class");
	
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public Map<String,Object> create(HttpServletRequest request, @RequestBody Map travelRecordMap){
		TravelRecord travelRecord = new TravelRecord();
		travelRecord.setLocation((String)travelRecordMap.get("location"));
		travelRecord.setPictureKey((String)travelRecordMap.get("picture"));
		travelRecord.setText((String)travelRecordMap.get("note"));
		travelRecord.setTravelId((String)travelRecordMap.get("tripid"));
		travelRecord.setUid((String)travelRecordMap.get("userid"));
		Date dt = new Date(Long.parseLong(String.valueOf(travelRecordMap.get("time"))));
		travelRecord.setUptime(dt);
		travelRecord.setValid("1");
		travelRecord.setId(0);
		Map<String,Object> modelMap = new HashMap<String,Object>();
		try{
			travelRecordService.insertTravelRecord(travelRecord);
			int travelnoteid = travelRecord.getId();
			modelMap.put("success",1);
			modelMap.put("travelnoteid",travelnoteid);
		}catch(Exception e){
			modelMap.put("success",0);
		}
		
		return modelMap;
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Map<String,Object> edit(HttpServletRequest request, @RequestBody Map travelRecordMap){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		try{
			TravelRecord travelRecord = new TravelRecord();
			travelRecord.setId(Integer.parseInt(String.valueOf(travelRecordMap.get("travelnoteid"))));
			travelRecord.setLocation(String.valueOf(travelRecordMap.get("location")));
			travelRecord.setPictureKey(String.valueOf(travelRecordMap.get("picture")));
			travelRecord.setText(String.valueOf(travelRecordMap.get("note")));
			travelRecord.setUid(String.valueOf(travelRecordMap.get("userid")));
			Date dt = new Date(Long.parseLong(String.valueOf(travelRecordMap.get("time"))));
			travelRecord.setUptime(dt);
			travelRecordService.editTravelRecord(travelRecord);
			modelMap.put("success", 1);
			return modelMap;
		}catch(Exception e){
			modelMap.put("success", 0);
			return modelMap;
		}
	}
	
	@RequestMapping(value="/remove",method=RequestMethod.POST)
	public Map<String,Object> remove(HttpServletRequest request, @RequestBody Map travelRecordMap){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		
		int travelnoteid = Integer.parseInt((String)travelRecordMap.get("travelnoteid"));
		try{
				travelRecordService.removeTravelRecordById(travelnoteid);
				modelMap.put("success",1);
				return modelMap;
		}catch(Exception e){
			modelMap.put("success",0);
			modelMap.put("message","错误");
			return modelMap;
		}
	}
	
	@RequestMapping(value="/queryById",method=RequestMethod.POST)
	public Map<String,Object> queryById(HttpServletRequest request, @RequestBody Map travelRecordMap){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		
		int tripid = Integer.parseInt((String)travelRecordMap.get("tripid"));
		try{
				List<TravelRecord> travelRecordList = travelRecordService.queryByTravelId(tripid);

				modelMap.put("success",1);
				List< Map<String,String> > lst = new ArrayList<Map<String,String > >();
				TravelRecord tmp = null;
				for(TravelRecord a:travelRecordList){
					Map<String,String> mp = new HashMap<String ,String>();
					mp.put("userid", a.getUid());
					mp.put("tripid", a.getTravelId());
					mp.put("travelnoteid", a.getId()+"");
					mp.put("picture", Constant.PICDOMAIN+"/"+a.getPictureKey());
					mp.put("note", a.getText());
					mp.put("time", ""+a.getUptime().getTime());
					mp.put("location", a.getLocation());
					lst.add(mp);
					tmp = a;
				}
				modelMap.put("notelist", lst);
				return modelMap;
		}catch(Exception e){
			modelMap.put("success",0);
			modelMap.put("message","错误");
			return modelMap;
		}
	}
			
	@RequestMapping(value = "/b")
	public void aaa(HttpServletRequest request, HttpServletResponse response) {
		try {
			redisClient.set("a", "abc");	
		} catch (Exception e) {
			
		}
	}

}
