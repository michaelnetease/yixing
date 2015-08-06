package com.netease.yixing.action;

import java.text.SimpleDateFormat;
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

import com.netease.yixing.model.TravelSchedule;
import com.netease.yixing.model.TravelScheduleAgenda;
import com.netease.yixing.model.User;
import com.netease.yixing.service.ITravelScheduleAgendaService;
import com.netease.yixing.utils.Constant;

@Controller
public class TravelScheduleAgendaController {

	@Autowired
	private ITravelScheduleAgendaService agendaServ;
	
	private static Logger logger = LoggerFactory.getLogger(TravelScheduleAgendaController.class);

	public ITravelScheduleAgendaService getAgendaServ() {
		return agendaServ;
	}

	public void setAgendaServ(ITravelScheduleAgendaService agendaServ) {
		this.agendaServ = agendaServ;
	}
	
	@RequestMapping(value="/travel/agenda/create",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> createScheduleAgenda(@RequestBody Map map){
		Map<String,Object> modelMap = new HashMap<String,Object>();   
		boolean success = true;
		String message = Constant.SUCCESS_MESSAGE;
		int agendaId = 0;
		try {
			String travelDayStr = String.valueOf(map.get("travelDay"));
			int userId = (Integer)map.get("userId");
			int scheduleId = (Integer)map.get("scheduleId");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			Date travelDay = sdf.parse(travelDayStr);
			
			TravelScheduleAgenda entity = new TravelScheduleAgenda();
			TravelSchedule schedule = new TravelSchedule();						
			schedule.setScheduleId(scheduleId);
			entity.setSchedule(schedule);
			entity.setTravelDay(travelDay);
			entity.setUpdateTime(new Date());
			User user = new User();
			user.setId(userId);
			entity.setUser(user);
			agendaId = agendaServ.createScheduleAgenda(entity);
		} catch (Exception e) {
			success = false;
			message = e.getMessage();
			logger.error(message);
		}finally{
			modelMap.put(Constant.SUCCESS, success);
			modelMap.put(Constant.MESSAGE, message);
			modelMap.put("agendaId", agendaId);
		}
		return modelMap;
	}
	
	@RequestMapping(value="/travel/agenda/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> deleteAgenda(@RequestBody TravelScheduleAgenda entity){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = true;
		String message = Constant.SUCCESS_MESSAGE;
		try {
			agendaServ.deleteScheduleAgenda(entity);
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
	
	@RequestMapping(value="/travel/agenda/query",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryAgendaById(@RequestBody TravelScheduleAgenda entity){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = true;
		String message = Constant.SUCCESS_MESSAGE;
		TravelScheduleAgenda result = null;
		try {
			result = agendaServ.queryAgendaById(entity.getAgendaId());
		} catch (Exception e) {
			success = false;
			message = e.getMessage();
			logger.error(message);
		}finally{
			modelMap.put(Constant.SUCCESS, success);
			modelMap.put(Constant.MESSAGE, message);
			modelMap.put("agenda", result);
		}
		
		return modelMap;
	}
	
	
	@RequestMapping(value="/travel/agenda/queryAll",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryAllAgendaByScheduleId(@RequestBody Map map){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		int scheduleId = (Integer)map.get("scheduleId");		
		boolean success = true;
		String message = Constant.SUCCESS_MESSAGE;
		List<TravelScheduleAgenda> result = null;
		try {
			result = agendaServ.queryAllScheduleAgendaByScheuldeId(scheduleId);
		} catch (Exception e) {
			success = false;
			message = e.getMessage();
			logger.error(message);
		}finally{
			modelMap.put(Constant.SUCCESS, success);
			modelMap.put(Constant.MESSAGE, message);
			modelMap.put("allAgenda", result);
		}
		
		return modelMap;
	}
	
}
