package com.netease.yixing.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.netease.yixing.model.TravelSchedule;
import com.netease.yixing.model.User;
import com.netease.yixing.service.ITravelScheduleService;

@Controller
public class TravelScheduleController {
	
	@Autowired
	private ITravelScheduleService travelScheduleServ;
	
	public ITravelScheduleService getTravelScheduleServ() {
		return travelScheduleServ;
	}

	public void setTravelScheduleServ(ITravelScheduleService travelScheduleServ) {
		this.travelScheduleServ = travelScheduleServ;
	}


	@RequestMapping(value="/travel/schedule/create",method = RequestMethod.POST)
	public Map<String,Object> createTravelSchedule(HttpServletRequest request, @RequestBody Map map){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = true;
		String message = "ok";
		int scheduleId = 0;
		try {
			String title = (String)map.get("title");
			int userId = Integer.parseInt((String)map.get("userId"));
			String startTimeStr = (String)map.get("startTime");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date startTime = sdf.parse(startTimeStr);
			TravelSchedule schedule = new TravelSchedule();
			User user = new User();
			user.setId(userId);		
			schedule.setCreateUser(user);
			schedule.setTitle(title);
			schedule.setStartTime(startTime);
			Calendar cal = Calendar.getInstance();
	        cal.setTime(startTime);
	        cal.add(Calendar.DATE, 1);
			schedule.setEndTime(cal.getTime());
			schedule.setUpdateTime(new Date());
			schedule.setGroupMembers(String.valueOf(userId));
			scheduleId = travelScheduleServ.createTravelSchedule(schedule);
		} catch (Exception e) {
			success = false;
			message = e.getMessage();
			e.printStackTrace();
		}finally{
			modelMap.put("success", success);
			modelMap.put("message", message);
			modelMap.put("scheduleId", scheduleId);
		}

		return modelMap;
	}
	
	
	@RequestMapping(value="/travel/schedule/update",method = RequestMethod.PUT)
	public Map<String,Object> updateTravelSchedule(HttpServletRequest request, @RequestBody TravelSchedule entity){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = false;
		String message = "ok";
		try {
			travelScheduleServ.updateTravelSchedule(entity);
		} catch (Exception e) {
			success = false;
			message = e.getMessage();
			e.printStackTrace();
		}finally{
			modelMap.put("success", success);
			modelMap.put("message", message);
		}

		return modelMap;
	}
	
	@RequestMapping(value="/travel/schedule/delete",method = RequestMethod.POST)
	public Map<String,Object> deleteTravelSchedule(HttpServletRequest request, @RequestBody TravelSchedule entity){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = false;
		String message = "ok";
		try {
			travelScheduleServ.deleteTravelSchedule(entity);
		} catch (Exception e) {
			success = false;
			message = e.getMessage();
			e.printStackTrace();
		}finally{
			modelMap.put("success", success);
			modelMap.put("message", message);
		}

		return modelMap;
	}
	
	@RequestMapping(value="/travel/schedule/querybyuser", method = RequestMethod.GET)
	public Map<String,Object> queryAllJoinTravelSchedule(HttpServletRequest request, @RequestBody Map<String,Object> requestMap){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		List<TravelSchedule> scheduleInfos = null;
		boolean success = false;
		int userId = Integer.parseInt((String)requestMap.get("userId"));		
		try{
			scheduleInfos = travelScheduleServ.queryTravelInfoByUserId(userId);
		}catch(Exception e){
			success = false;
			e.printStackTrace();
		}finally{
			modelMap.put("success", success);
			modelMap.put("schedules", scheduleInfos);
		}
		return modelMap;
	}
	
	@RequestMapping(value="/travel/schedule/pageQuery", method = RequestMethod.GET)
	public Map<String,Object> queryFixedNumJoinTravelSchedule(HttpServletRequest request, 
			@RequestBody Map<String,Object> map){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		List<TravelSchedule> scheduleInfos = null;
		boolean success = false;
		int userId = Integer.parseInt((String)map.get("userId"));	
		int startIndex = Integer.parseInt((String)map.get("startIndex"));
		int length = Integer.parseInt((String)map.get("length"));
		try{
			scheduleInfos = travelScheduleServ.queryFixedLengthTravelInfoByUserId(userId,startIndex,length);
		}catch(Exception e){
			success = false;
			e.printStackTrace();
		}finally{
			modelMap.put("success", success);
			modelMap.put("schedules", scheduleInfos);
		}
		return modelMap;
	}
	
	@RequestMapping(value="/travel/schedule/querydetails", method = RequestMethod.POST)
	public Map<String,Object> queryTravelScheduleDetailsByScheduleId(HttpServletRequest request, @RequestBody Map<String,Object> requestMap){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = false;
		TravelSchedule schedule = null;
		int scheduleId = Integer.parseInt((String)requestMap.get("scheduleId"));		
		try{
			schedule = travelScheduleServ.queryScheduleDetailsByScheduleId(scheduleId);
		}catch(Exception e){
			success = false;
			e.printStackTrace();
		}finally{
			modelMap.put("success", success);
			modelMap.put("schedule", schedule);
		}
		return modelMap;
	}

}
