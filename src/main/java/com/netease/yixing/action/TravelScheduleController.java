package com.netease.yixing.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netease.yixing.model.TravelAgendaArrange;
import com.netease.yixing.model.TravelAgendaHotel;
import com.netease.yixing.model.TravelRecord;
import com.netease.yixing.model.TravelSchedule;
import com.netease.yixing.model.TravelScheduleAgenda;
import com.netease.yixing.model.User;
import com.netease.yixing.service.ITravelScheduleService;
import com.netease.yixing.utils.Constant;

@Controller
public class TravelScheduleController {
	
	@Autowired
	private ITravelScheduleService travelScheduleServ;
	
	private static Logger logger = LoggerFactory.getLogger(TravelScheduleController.class);
	
	public ITravelScheduleService getTravelScheduleServ() {
		return travelScheduleServ;
	}

	public void setTravelScheduleServ(ITravelScheduleService travelScheduleServ) {
		this.travelScheduleServ = travelScheduleServ;
	}


	@RequestMapping(value="/travel/schedule/create",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> createTravelSchedule(@RequestBody Map map){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = true;
		String message = Constant.SUCCESS_MESSAGE;
		int scheduleId = 0;
		try {
			String title = String.valueOf(map.get("title")) ;
			int userId = (Integer)map.get("userId");
			String startTimeStr = String.valueOf(map.get("startTime")) ;
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
	        cal.add(Calendar.DATE, 2);
			schedule.setEndTime(cal.getTime());
			schedule.setUpdateTime(new Date());
			schedule.setGroupMembers(String.valueOf(userId));
			scheduleId = travelScheduleServ.createTravelSchedule(schedule);
		} catch (Exception e) {
			success = false;
			message = e.getMessage();
			logger.error(message);
		}finally{
			modelMap.put(Constant.SUCCESS, success);
			modelMap.put(Constant.MESSAGE, message);
			modelMap.put("scheduleId", scheduleId);
		}

		return modelMap;
	}
	
	
	@RequestMapping(value="/travel/schedule/update",method = RequestMethod.PUT)
	@ResponseBody
	public Map<String,Object> updateTravelSchedule(@RequestBody TravelSchedule entity){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = true;
		String message = Constant.SUCCESS_MESSAGE;
		try {
			travelScheduleServ.updateTravelSchedule(entity);
		} catch (Exception e) {
			success = false;
			message = e.getMessage();
			logger.error(message);
		}finally{
			modelMap.put(Constant.SUCCESS, success);
			modelMap.put(Constant.MESSAGE, message);
		}

		return modelMap;
	}
	
	@RequestMapping(value="/travel/schedule/delete",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> deleteTravelSchedule(@RequestBody TravelSchedule entity){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = true;
		String message = Constant.SUCCESS_MESSAGE;
		try {
			travelScheduleServ.deleteTravelSchedule(entity);
		} catch (Exception e) {
			success = false;
			message = e.getMessage();
			logger.error(message);
		}finally{
			modelMap.put(Constant.SUCCESS, success);
			modelMap.put(Constant.MESSAGE, message);
		}

		return modelMap;
	}
	
	@RequestMapping(value="/travel/schedule/querybyuser", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> queryAllJoinTravelSchedule(@RequestBody Map<String,Object> requestMap){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		List<TravelSchedule> scheduleInfos = null;
		boolean success = true;
		int userId = (Integer)requestMap.get("userId");		
		try{
			scheduleInfos = travelScheduleServ.queryTravelInfoByUserId(userId);
		}catch(Exception e){
			success = false;
			logger.error(e.getMessage());
		}finally{
			modelMap.put("success", success);
			modelMap.put("schedules", scheduleInfos);
		}
		return modelMap;
	}
	
	@RequestMapping(value="/travel/schedule/pageQuery", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryFixedNumJoinTravelSchedule(@RequestBody Map<String,Object> map){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		List<TravelSchedule> scheduleInfos = null;
		boolean success = true;
		int userId = (Integer)map.get("userId");	
		int startIndex = (Integer)map.get("startIndex");
		int length = (Integer)map.get("length");
		String pictureKey = null;
		String location = null;
		String message = Constant.SUCCESS_MESSAGE;

		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		try{
			scheduleInfos = travelScheduleServ.queryFixedLengthTravelInfoByUserId(userId,startIndex,length);
			if(scheduleInfos!=null){
				for(TravelSchedule schedule:scheduleInfos){
					Map<String,Object> resultMap = new HashMap<String,Object>();
					resultMap.put("scheduleId", schedule.getScheduleId());
					resultMap.put("title", schedule.getTitle());
					resultMap.put("startTime", schedule.getStartTime());
					User user = schedule.getCreateUser();
					if(user!=null){
						user.setPassword(null);
					}
					resultMap.put("createUser", user);				
					List<TravelRecord> recordList = schedule.getRecordList();
					TravelRecord firstRecord = (recordList!=null && recordList.size()>0)?recordList.get(0):null;
					pictureKey = firstRecord!=null? firstRecord.getPictureKey(): null;
					location = firstRecord!=null? firstRecord.getLocation():null;
					resultMap.put("pictureKey", pictureKey);				
					resultMap.put("location", location);
					
					result.add(resultMap);
				}
			}
		}catch(Exception e){
			success = false;
			message = e.getMessage();
			logger.error(message);
		}finally{
			modelMap.put("success", success);
			modelMap.put("message", message);
			modelMap.put("schedules", result);
		}
		return modelMap;
	}
	
	@RequestMapping(value="/travel/schedule/querydetails", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryTravelScheduleDetailsByScheduleId(@RequestBody Map<String,Object> requestMap){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = true;
		TravelSchedule schedule = null;
		int scheduleId = (Integer)requestMap.get("scheduleId");		
		String message = Constant.SUCCESS_MESSAGE;
		try{
			schedule = travelScheduleServ.queryScheduleDetailsByScheduleId(scheduleId);
			if(schedule!=null && schedule.getCreateUser()!=null){
				User user = schedule.getCreateUser();
				user.setPassword(null);
			}
		}catch(Exception e){
			success = false;
			message = e.getMessage();
			logger.error(message);
		}finally{
			modelMap.put(Constant.SUCCESS, success);
			modelMap.put(Constant.MESSAGE, message);
			modelMap.put("schedule", schedule);
		}
		return modelMap;
	}
	
	@RequestMapping(value="/travel/schedule/querylatest", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryLateastTravelScheduleDetailsByUserId(@RequestBody Map<String,Object> requestMap){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = true;
		TravelSchedule schedule = null;
		int userId = (Integer)requestMap.get("userId");	
		String message = Constant.SUCCESS;
		try{
			schedule = travelScheduleServ.queryLatestScheduleDetailsByUserId(userId);
			if(schedule!=null && schedule.getCreateUser()!=null){
				User user = schedule.getCreateUser();
				user.setPassword(null);
				user.setJoinTravelSchedule(null);
				List<TravelScheduleAgenda> agendaList =  schedule.getAgendaList();
				if(agendaList!=null && agendaList.size() > 0){
					for(TravelScheduleAgenda agenda:agendaList){
						List<TravelAgendaArrange> arrangeList = agenda.getArrangeList();
						List<TravelAgendaHotel> hotelList = agenda.getHotelList();
						List<TravelAgendaArrange> arrangeListData = new ArrayList<TravelAgendaArrange>();
						List<TravelAgendaHotel> hotelListData = new ArrayList<TravelAgendaHotel>();
						if(arrangeList!=null && arrangeList.size()>0){
							for(TravelAgendaArrange arrange : arrangeList){
								if(arrange.getArrangeId()!=0){
									arrangeListData.add(arrange);
								}
							}

						}
						
						if(hotelList!=null && hotelList.size()>0){
							for(TravelAgendaHotel hotel: hotelList){
								if(hotel.getHotelId()!=0){
									hotelListData.add(hotel);
								}
							}
						}
						
						agenda.setArrangeList(arrangeListData);
						agenda.setHotelList(hotelListData);
					}
				}
			}
		}catch(Exception e){
			success = false;
			message = e.getMessage();
			logger.error(message);
		}finally{
			modelMap.put(Constant.SUCCESS, success);
			modelMap.put(Constant.MESSAGE, message);
			modelMap.put("schedule", schedule);
		}
		return modelMap;
	}
	
	
	@RequestMapping(value="/travel/schedule/querytopvisit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryTopKVistedSchedule(@RequestBody Map params){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		int k = (Integer)params.get("k");
		boolean success = true;
		List<TravelSchedule> schedules = null;	
		String message = Constant.SUCCESS_MESSAGE;
		try{
			schedules = travelScheduleServ.queryTopKVisitedTravelSchedule(k);
		}catch(Exception e){
			success = false;
			message = e.getMessage();
			logger.error(message);
		}finally{
			modelMap.put(Constant.SUCCESS, success);
			modelMap.put(Constant.MESSAGE, message);
			modelMap.put("schedules", schedules);
		}
		return modelMap;
	}
	
	
	@RequestMapping(value="/travel/schedule/querytopmark", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryTopKMarkedSchedule(@RequestBody Map<String,Object> map){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		int k = (Integer)map.get("k");
		Constant.TOP_K_TRAVEL_SCHEDULE = k;
		boolean success = true;
		List<TravelSchedule> schedules = null;	
		String message = Constant.SUCCESS_MESSAGE;
		try{
			schedules = travelScheduleServ.queryTopKMarkedTravelSchedule(k);
		}catch(Exception e){
			success = false;
			message = e.getMessage();
			logger.error(message);
		}finally{
			modelMap.put(Constant.SUCCESS, success);
			modelMap.put(Constant.MESSAGE, message);
			modelMap.put("schedules", schedules);
		}
		return modelMap;
	}
	
	//test interface
//	@RequestMapping(value="/travel/schedule/members", method = RequestMethod.POST)
//	@ResponseBody
//	public Map<String,Object> queryUserNumbers(@RequestBody Map<String,Object> map){
//		Map<String,Object> modelMap = new HashMap<String,Object>();
//		boolean success = true;
//		int scheduleId = (Integer)map.get("scheduleId");
//		String message = Constant.SUCCESS_MESSAGE;
//		int result = 0;
//		try{
//			result = travelScheduleServ.getJoinUserNumbersInSchedule(scheduleId);
//		}catch(Exception e){
//			success = false;
//			message = e.getMessage();
//			logger.error(message);
//		}finally{
//			modelMap.put(Constant.SUCCESS, success);
//			modelMap.put(Constant.MESSAGE, message);
//			modelMap.put("users", result);
//		}
//		return modelMap;
//	}
	
	@RequestMapping(value="/travel/photo/update", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String,Object> updatePhoto(@RequestBody Map<String,Object> map){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = true;
		int scheduleId = (Integer)map.get("scheduleId");
		String photoKey = String.valueOf(map.get("photoKey"));
		String message = Constant.SUCCESS_MESSAGE;
		try{
			travelScheduleServ.updateSchedulePhoto(scheduleId, photoKey);
		}catch(Exception e){
			success = false;
			message = e.getMessage();
			logger.error(message);
		}finally{
			modelMap.put(Constant.SUCCESS, success);
			modelMap.put(Constant.MESSAGE, message);
		}
		return modelMap;
	}
	
	@RequestMapping(value="/travel/photo/query", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryUserNumbers(@RequestBody Map<String,Object> map){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = true;
		int scheduleId = (Integer)map.get("scheduleId");
		String message = Constant.SUCCESS_MESSAGE;
		TravelSchedule schedule = null;
		String photoKey = null;
		try{
			schedule = travelScheduleServ.getSimpleScheduleById(scheduleId);
			if(schedule!=null){
				photoKey = schedule.getPhoto();
			}
		}catch(Exception e){
			success = false;
			message = e.getMessage();
			logger.error(message);
		}finally{
			modelMap.put(Constant.SUCCESS, success);
			modelMap.put(Constant.MESSAGE, message);
			modelMap.put("photoKey", photoKey);
		}
		return modelMap;
	}

}
