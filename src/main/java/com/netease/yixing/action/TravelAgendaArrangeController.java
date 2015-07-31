package com.netease.yixing.action;

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

import com.netease.yixing.model.TravelAgendaArrange;
import com.netease.yixing.model.TravelScheduleAgenda;
import com.netease.yixing.model.User;
import com.netease.yixing.service.ITravelAgendaArrangeService;

@Controller
public class TravelAgendaArrangeController {

	@Autowired
	private ITravelAgendaArrangeService arrangeServ;

	public ITravelAgendaArrangeService getArrangeServ() {
		return arrangeServ;
	}

	public void setArrangeServ(ITravelAgendaArrangeService arrangeServ) {
		this.arrangeServ = arrangeServ;
	}
	
	
	@RequestMapping(value="/travel/arrange/create",method=RequestMethod.POST)
	public Map<String,Object> createAgendaArrange(HttpServletRequest request, @RequestBody Map map){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = true;
		String message = "ok";
		int arrangeId = 0;
		int agendaId = Integer.parseInt((String)map.get("agendaId"));
		String timePoint = (String)map.get("timePoint");
		String event = (String)map.get("event");
		String info = (String)map.get("info");
		int userId = Integer.parseInt((String)map.get("userId"));
		TravelAgendaArrange entity = new TravelAgendaArrange();
		entity.setEvent(event);
		entity.setInfo(info);
		entity.setTimePoint(timePoint);
		TravelScheduleAgenda agenda = new TravelScheduleAgenda();
		agenda.setAgendaId(agendaId);
		entity.setAgenda(agenda);
		User user = new User();
		user.setId(userId);
		entity.setUpdateTime(new Date());
		entity.setUser(user);
		try {
			arrangeId = arrangeServ.createTravelAgendaArrange(entity);
		} catch (Exception e) {
			success = false;
			message = e.getMessage();
			e.printStackTrace();
		}finally{
			modelMap.put("success", success);
			modelMap.put("message", message);
			modelMap.put("arrangeId", arrangeId);
		}

		return modelMap;
	}
	
	
	@RequestMapping(value="/travel/arrange/update",method=RequestMethod.PUT)
	public Map<String,Object> updateAgendaArrange(HttpServletRequest request, @RequestBody TravelAgendaArrange entity){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = true;
		String message = "ok";
		try {
			arrangeServ.updateTravelAgendaArrange(entity);
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
	
	@RequestMapping(value="/travel/arrange/delete",method=RequestMethod.POST)
	public Map<String,Object> deleteAgendaArrange(HttpServletRequest request, @RequestBody TravelAgendaArrange entity){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = true;
		String message = "ok";
		try {
			arrangeServ.deleteTravelAgendaArrange(entity);
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
	
	@RequestMapping(value="/travel/arrange/queryAll",method=RequestMethod.POST)
	public Map<String,Object> queryAllArrangeByAgendaId(HttpServletRequest request, @RequestBody TravelScheduleAgenda entity){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = true;
		String message = "ok";
		List<TravelAgendaArrange> ls = null;
		try {
			ls = arrangeServ.queryAllArrangeByAgendaId(entity);
		} catch (Exception e) {
			success = false;
			message = e.getMessage();
			e.printStackTrace();
		}finally{
			modelMap.put("success", success);
			modelMap.put("message", message);
			modelMap.put("arranges", ls);
		}
		
		return modelMap;
	}
	
	
	@RequestMapping(value="/travel/arrange/query",method=RequestMethod.POST)
	public Map<String,Object> queryArrangeById(HttpServletRequest request, @RequestBody Map map){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = true;
		String message = "ok";
		int arrangeId = Integer.parseInt((String)map.get("arrangeId"));
		TravelAgendaArrange entity = new TravelAgendaArrange();
		entity.setArrangeId(arrangeId);
		
		TravelAgendaArrange result = null;
		try {
			result = arrangeServ.queryArrangeById(entity);
		} catch (Exception e) {
			success = false;
			message = e.getMessage();
			e.printStackTrace();
		}finally{
			modelMap.put("success", success);
			modelMap.put("message", message);
			modelMap.put("arrange", result);
		}
		
		return modelMap;
	}
}
