package com.netease.yixing.action;

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
import com.netease.yixing.model.TravelScheduleAgenda;
import com.netease.yixing.model.User;
import com.netease.yixing.service.ITravelAgendaArrangeService;
import com.netease.yixing.utils.Constant;

@Controller
public class TravelAgendaArrangeController {

	@Autowired
	private ITravelAgendaArrangeService arrangeServ;
	
	private Logger logger = LoggerFactory.getLogger(TravelAgendaArrangeController.class);

	public ITravelAgendaArrangeService getArrangeServ() {
		return arrangeServ;
	}

	public void setArrangeServ(ITravelAgendaArrangeService arrangeServ) {
		this.arrangeServ = arrangeServ;
	}
	
	
	@RequestMapping(value="/travel/arrange/create",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> createAgendaArrange(@RequestBody Map map){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = true;
		String message = Constant.SUCCESS_MESSAGE;
		int arrangeId = 0;
		int agendaId = (Integer)map.get("agendaId");
		String timePoint = String.valueOf(map.get("timePoint"));
		String event = String.valueOf(map.get("event"));
		String info = String.valueOf(map.get("info"));
		int userId = (Integer)map.get("userId");
		TravelAgendaArrange entity = new TravelAgendaArrange();
		entity.setEvent(event);
		entity.setInfo(info);
		entity.setTimePoint(timePoint);
		TravelScheduleAgenda agenda = new TravelScheduleAgenda();
		agenda.setAgendaId(agendaId);
		entity.setAgenda(agenda);
		User user = new User();
		user.setId(userId);
		entity.setUser(user);
		entity.setUpdateTime(new Date());		
		try {
			arrangeId = arrangeServ.createTravelAgendaArrange(entity);
		} catch (Exception e) {
			success = false;
			message = e.getMessage();
			logger.error(message);
		}finally{
			modelMap.put(Constant.SUCCESS, success);
			modelMap.put(Constant.MESSAGE, message);
			modelMap.put("arrangeId", arrangeId);
		}

		return modelMap;
	}
	
	
	@RequestMapping(value="/travel/arrange/update",method=RequestMethod.PUT)
	@ResponseBody
	public Map<String,Object> updateAgendaArrange(@RequestBody Map map){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		int arrangeId = (Integer)map.get("arrangeId");
		String timePoint = String.valueOf(map.get("timePoint"));
		String event = String.valueOf(map.get("event"));
		String info = String.valueOf(map.get("info"));		
		TravelAgendaArrange entity = new TravelAgendaArrange();
		entity.setArrangeId(arrangeId);
		entity.setEvent(event);
		entity.setInfo(info);
		entity.setTimePoint(timePoint);		
		
		boolean success = true;
		String message = Constant.SUCCESS_MESSAGE;
		try {
			arrangeServ.updateTravelAgendaArrange(entity);
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
	
	@RequestMapping(value="/travel/arrange/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> deleteAgendaArrange(@RequestBody TravelAgendaArrange entity){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = true;
		String message = Constant.SUCCESS_MESSAGE;
		try {
			arrangeServ.deleteTravelAgendaArrange(entity);
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
	
	@RequestMapping(value="/travel/arrange/queryAll",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryAllArrangeByAgendaId(@RequestBody TravelScheduleAgenda entity){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = true;
		String message = Constant.SUCCESS_MESSAGE;
		List<TravelAgendaArrange> ls = null;
		try {
			ls = arrangeServ.queryAllArrangeByAgendaId(entity);
		} catch (Exception e) {
			success = false;
			message = e.getMessage();
			logger.error(message);
		}finally{
			modelMap.put(Constant.SUCCESS, success);
			modelMap.put(Constant.MESSAGE, message);
			modelMap.put("arranges", ls);
		}
		
		return modelMap;
	}
	
	
	@RequestMapping(value="/travel/arrange/query",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryArrangeById(@RequestBody Map map){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = true;
		String message = Constant.SUCCESS_MESSAGE;
		int arrangeId = (Integer)map.get("arrangeId");
		TravelAgendaArrange entity = new TravelAgendaArrange();
		entity.setArrangeId(arrangeId);
		
		TravelAgendaArrange result = null;
		try {
			result = arrangeServ.queryArrangeById(entity);
		} catch (Exception e) {
			success = false;
			message = e.getMessage();
			logger.error(message);
		}finally{
			modelMap.put(Constant.SUCCESS, success);
			modelMap.put(Constant.MESSAGE, message);
			modelMap.put("arrange", result);
		}
		
		return modelMap;
	}
}
