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
import org.springframework.web.bind.annotation.ResponseBody;

import com.netease.yixing.model.Auth2;
import com.netease.yixing.model.Equipment;
import com.netease.yixing.model.Picture;
import com.netease.yixing.model.TravelRecord;
import com.netease.yixing.model.TravelSchedule;
import com.netease.yixing.model.User;
import com.netease.yixing.service.IAuthService;
import com.netease.yixing.service.IInvitationService;
import com.netease.yixing.service.ILoginService;
import com.netease.yixing.service.IPictureService;
import com.netease.yixing.service.ITravelRecordService;
import com.netease.yixing.service.ITravelScheduleService;
import com.netease.yixing.service.impl.InvitationService;
import com.netease.yixing.utils.Constant;
import com.netease.yixing.utils.PicService;
import com.netease.yixing.utils.UserCheck;
import com.netease.yixing.work.redis.RedisClientTemplate;


@Controller
@RequestMapping(value = "/record")
public class TravelRecordCotroller {

	@Autowired
	private ITravelRecordService travelRecordService;
	
	@Autowired
	private RedisClientTemplate redisClient;

	//private Logger logger = Logger.getLogger("TravelRecordCotroller.class");
	
	@Autowired
	private ILoginService loginServ;
	
	@Autowired
	private IAuthService authService;
	//private Logger logger = Logger.getLogger("TravelRecordCotroller.class");
	@Autowired
	private UserCheck  usercheck;
	
	@Autowired
	private IPictureService  pictureService; 
	
	@Autowired
	private ITravelScheduleService travelScheduleServ;
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> create(HttpServletRequest request, @RequestBody Map travelRecordMap){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		TravelRecord travelRecord = new TravelRecord();
		try{
			travelRecord.setLocation((String)travelRecordMap.get("location"));
			travelRecord.setPictureKey("");
			travelRecord.setText((String)travelRecordMap.get("note"));
			travelRecord.setTravelId((String)travelRecordMap.get("tripid"));
			travelRecord.setUid((String)travelRecordMap.get("userid"));
			long ltime= Long.parseLong(String.valueOf(travelRecordMap.get("time")));
			List<Map<String,String>> lmap = (List)travelRecordMap.get("picture");
			Date dt = new Date(getTimeStamp(ltime));
			travelRecord.setUptime(dt);
			travelRecord.setValid("1");
			travelRecord.setId(0);
			travelRecordService.insertTravelRecord(travelRecord);
			int travelnoteid = travelRecord.getId();
			String firstPic="";
			for(Map<String,String> a:lmap){
				Picture pic = new Picture();
				String height =(String)a.get("height");
				String width = (String)a.get("width");
				String key = (String)a.get("key");
				String position = (String)a.get("position");
				if(firstPic.equals("")){
					firstPic = key;
				}
				if(position.equals("1")){
					firstPic = key;
				}
				pic.setHeight(height);
				pic.setWidth(width);
				pic.setKey(key);
				pic.setPosition(position);
				pic.setRecordid(travelnoteid+"");
				pictureService.insertPicture(pic);
			}
			travelRecord.setPictureKey(firstPic);
			travelRecordService.editTravelRecord(travelRecord);
		
			modelMap.put("success",1);
			modelMap.put("travelnoteid",travelnoteid);
		}catch(Exception e){
			modelMap.put("success",0);
		}
		
		return modelMap;
	}
	//弃用
	//@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Map<String,Object> edit(HttpServletRequest request, @RequestBody Map travelRecordMap){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		try{
			TravelRecord travelRecord = new TravelRecord();
			travelRecord.setId(Integer.parseInt(String.valueOf(travelRecordMap.get("travelnoteid"))));
			travelRecord.setLocation(String.valueOf(travelRecordMap.get("location")));
			travelRecord.setPictureKey(String.valueOf(travelRecordMap.get("picture")));
			travelRecord.setText(String.valueOf(travelRecordMap.get("note")));
			travelRecord.setUid(String.valueOf(travelRecordMap.get("userid")));
			long ltime = Long.parseLong(String.valueOf(travelRecordMap.get("time")));
			Date dt = new Date(getTimeStamp(ltime));
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
	@ResponseBody
	public Map<String,Object> remove(HttpServletRequest request, @RequestBody Map travelRecordMap){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		try{
				int travelnoteid = Integer.parseInt((String)travelRecordMap.get("travelnoteid"));
				travelRecordService.removeTravelRecordById(travelnoteid);
				modelMap.put("success",1);
				return modelMap;
		}catch(Exception e){
			modelMap.put("success",0);
			modelMap.put("message","错误");
			return modelMap;
		}
	}
	
	
//	@RequestMapping(value="/queryByTripId",method=RequestMethod.POST)
//	@ResponseBody
//	public Map<String,Object> queryLastById2(HttpServletRequest request, @RequestBody Map requestMap){
//		
//	
//		Map<String,Object> modelMap = new HashMap<String,Object>();
//		try{
//			    int skip=Integer.parseInt((String)requestMap.get("skip"));
//			    int length = Integer.parseInt((String)requestMap.get("length"));
//				int tripid = Integer.parseInt((String)requestMap.get("tripid"));
//				List<TravelRecord> travelRecordList = travelRecordService.queryByTravelIdAndPage(tripid,skip,length);
//				
//				TravelSchedule schedule =travelScheduleServ.queryScheduleDetailsByScheduleId(tripid);
//				modelMap.put("success",1);
//				modelMap.put("starttime", schedule.getStartTime().getTime()/1000+"");
//				List< Map<String,String> > lst = new ArrayList<Map<String,String > >();
//				TravelRecord tmp = null;
//				for(TravelRecord a:travelRecordList){
//					Map<String,String> mp = new HashMap<String ,String>();
//					mp.put("userid", a.getUid());
//					mp.put("title","");
//					mp.put("tripid", a.getTravelId());
//					mp.put("travelnoteid", a.getId()+"");
//					mp.put("picture", a.getPictureKey());
//					mp.put("note", a.getText());
//					mp.put("time", a.getUptime().getTime()/1000+"");
//					mp.put("location", a.getLocation());
//					User u = loginServ.selectUserById(a.getUid()+"");
//					mp.put("userPic", u.getPicId());
//					mp.put("usernickname",u.getNickname());
//					lst.add(mp);
//					tmp = a;
//				}
//				modelMap.put("notelist", lst);
//				return modelMap;
//		}catch(Exception e){
//			modelMap.put("success",0);
//			modelMap.put("message","错误");
//			return modelMap;
//		}
//	}
	@RequestMapping(value="/queryAllByTripId",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryAllByTripId(HttpServletRequest request, @RequestBody Map travelRecordMap){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		try{
				int tripid = Integer.parseInt((String)travelRecordMap.get("tripid"));
				int length = Integer.parseInt((String)travelRecordMap.get("length"));
				int skip=Integer.parseInt((String)travelRecordMap.get("skip"));
				List<TravelRecord> travelRecordList = travelRecordService.queryByTravelIdAndPage(tripid,skip,length);
				TravelSchedule schedule = travelScheduleServ.queryScheduleDetailsByScheduleId(tripid);
				if(schedule==null){
					modelMap.put("success",0);
					modelMap.put("message","此行程id不存在");
					return modelMap;
				}
				modelMap.put("success",1);
				
				List< Map<String,Object> > lst = new ArrayList<Map<String,Object > >();
				TravelRecord tmp = null;
				for(TravelRecord a:travelRecordList){
					
					Map<String,Object> mp = new HashMap<String ,Object>();
					List<Map<String,String> >lpic = new ArrayList<Map<String,String> >();
					
					List<Picture> lp = pictureService.queryByRecordId(a.getId());
					for(Picture tpic : lp){
						Map<String,String> pic = new HashMap<String,String>();
						pic.put("height",tpic.getHeight());
						pic.put("width",tpic.getWidth());
						pic.put("key",tpic.getKey());
						pic.put("position",tpic.getPosition());
						pic.put("id",tpic.getId()+"");
						lpic.add(pic);
					}
					mp.put("travelnoteid", a.getId()+"");
					mp.put("tripid", a.getTravelId());
					mp.put("title","");
					mp.put("picture", lpic); 
					mp.put("note", a.getText());
					mp.put("time", a.getUptime().getTime()/1000+"");
					mp.put("starttime", schedule.getStartTime().getTime()/1000+"");
					mp.put("location", a.getLocation());
					mp.put("userid", a.getUid());
					User u = loginServ.selectUserById(a.getUid()+"");
					if(u!=null){
						mp.put("userPic", u.getPicId());
						mp.put("usernickname",u.getNickname());
					}else{
						mp.put("userPic","");
						mp.put("usernickname", "");
					}
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
	
	@RequestMapping(value="/queryLastPageByUserId",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryLastById(HttpServletRequest request, @RequestBody Map requestMap){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = true;
		TravelSchedule schedule = null;
		
	    try {
			int userId = Integer.parseInt((String)requestMap.get("userId"));
			int skip=Integer.parseInt((String)requestMap.get("skip"));
		    int length = Integer.parseInt((String)requestMap.get("length"));
		    
			schedule = travelScheduleServ.queryLatestScheduleDetailsByUserId(userId);
			int tripid = 0;
			if(schedule!=null){
				tripid= schedule.getScheduleId();
			}else{
				modelMap.put("success",0);
				modelMap.put("message","行程不存在");
				return  modelMap;
			}
			List<TravelRecord> travelRecordList = travelRecordService.queryByTravelIdAndPage(tripid,skip,length);
			modelMap.put("success",1);
			List< Map<String,Object> > lst = new ArrayList<Map<String,Object > >();
			if(travelRecordList==null){
				modelMap.put("success",1);
				modelMap.put("notelist",lst);
				return  modelMap;
			}
			TravelRecord tmp = null;
			for(TravelRecord a:travelRecordList){
				
				Map<String,Object> mp = new HashMap<String ,Object>();
				List<Map<String,String> >lpic = new ArrayList<Map<String,String> >();
				
				List<Picture> lp = pictureService.queryByRecordId(a.getId());
				for(Picture tpic : lp){
					Map<String,String> pic = new HashMap<String,String>();
					pic.put("height",tpic.getHeight());
					pic.put("width",tpic.getWidth());
					pic.put("key",tpic.getKey());
					pic.put("position",tpic.getPosition());
					pic.put("id",tpic.getId()+"");
					lpic.add(pic);
				}
				mp.put("travelnoteid", a.getId()+"");
				mp.put("tripid", a.getTravelId());
				mp.put("title", "");
				mp.put("picture", lpic);
				mp.put("note", a.getText());
				mp.put("time", a.getUptime().getTime()/1000+"");
				mp.put("starttime", schedule.getStartTime().getTime()/1000+"");
				mp.put("location", a.getLocation());
				mp.put("userid", a.getUid());
				User u = loginServ.selectUserById(a.getUid()+"");
				mp.put("userPic", u.getPicId());
				mp.put("usernickname",u.getNickname());
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
	
	@RequestMapping(value="/queryAllByUserId",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryAllById(HttpServletRequest request, @RequestBody Map requestMap){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = true;
		TravelSchedule schedule = null;
	
	    try {
	    	int userId = Integer.parseInt((String)requestMap.get("userId"));	
			int skip=Integer.parseInt((String)requestMap.get("skip"));
		     int length = Integer.parseInt((String)requestMap.get("length"));
			List<TravelRecord> travelRecordList = travelRecordService.queryAllByUserId(userId,skip,length);
			modelMap.put("success",1);
			Map<Integer,TravelSchedule> id2Schedule = new HashMap<Integer,TravelSchedule>();
			for(TravelRecord a:travelRecordList){
				int tripid = Integer.parseInt(a.getTravelId());
				if(!id2Schedule.containsKey(tripid)){
					TravelSchedule schedule2 = travelScheduleServ.queryScheduleDetailsByScheduleId(tripid);
					id2Schedule.put(tripid, schedule2);
				}
			}
			List< Map<String,Object> > lst = new ArrayList<Map<String,Object > >();
			TravelRecord tmp = null;
			for(TravelRecord a:travelRecordList){
				Map<String,Object> mp = new HashMap<String ,Object>();
				List<Map<String,String> >lpic = new ArrayList<Map<String,String> >();
				
				List<Picture> lp = pictureService.queryByRecordId(a.getId());
				for(Picture tpic : lp){
					Map<String,String> pic = new HashMap<String,String>();
					pic.put("height",tpic.getHeight());
					pic.put("width",tpic.getWidth());
					pic.put("key",tpic.getKey());
					pic.put("position",tpic.getPosition());
					pic.put("id",tpic.getId()+"");
					lpic.add(pic);
				}
				mp.put("travelnoteid", a.getId()+"");
				mp.put("tripid", a.getTravelId());
				int tripid = Integer.parseInt(a.getTravelId());
				TravelSchedule sd = id2Schedule.get(tripid);
				String title = sd.getTitle();
				mp.put("title",title);
				mp.put("picture", lpic);
				mp.put("note", a.getText());
				mp.put("time", a.getUptime().getTime()/1000+"");
				mp.put("starttime", "0");
				mp.put("location", a.getLocation());
				mp.put("userid", a.getUid());
				User u = loginServ.selectUserById(a.getUid()+"");
				if(u!=null){
					mp.put("userPic", u.getPicId());
					mp.put("usernickname",u.getNickname());
				}else{
					mp.put("userPic", "");
					mp.put("usernickname","");
				}
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
	
	@Autowired
	IInvitationService invitationService;
	@RequestMapping(value = "/b")
	@ResponseBody
	public  Map<String,Object> aaa(HttpServletRequest request, @RequestBody Map travelRecordMap) {
		
		invitationService.insertInvitation(3);

		Map<String,Object> modelMap = new HashMap<String,Object>();
	
		
		return modelMap;
		
	}
	public long getTimeStamp(long t){
		int a =getLength(t);
		System.out.println(a);
		if(a<=10){
			return t*1000;
		}
		return t;
	}
	public int getLength(long t){
		int ret = 0;
		while(t>0){
			t/=10;
			ret++;
		}
		return ret;
		
	}
}
