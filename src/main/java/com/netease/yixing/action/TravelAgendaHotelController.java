package com.netease.yixing.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netease.yixing.model.TravelAgendaHotel;
import com.netease.yixing.model.TravelScheduleAgenda;
import com.netease.yixing.model.User;
import com.netease.yixing.service.ITravelAgendaHotelService;
import com.netease.yixing.utils.Constant;

@Controller
public class TravelAgendaHotelController {

	@Autowired
	private ITravelAgendaHotelService hotelServ;
	
	private Logger logger = LoggerFactory.getLogger(TravelAgendaHotelController.class);
		
	public ITravelAgendaHotelService getHotelServ() {
		return hotelServ;
	}

	public void setHotelServ(ITravelAgendaHotelService hotelServ) {
		this.hotelServ = hotelServ;
	}

	@RequestMapping(value="/travel/hotel/create",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> createAgendaHotel(@RequestBody Map map){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = true;
		String message = Constant.SUCCESS_MESSAGE;
		int agendaId = (Integer)map.get("agendaId");
		int userId = (Integer)map.get("userId");
		String hotelName = String.valueOf(map.get("hotelName"));
		String hotelInfo = String.valueOf(map.get("hotelInfo"));
		
		TravelAgendaHotel hotel = new TravelAgendaHotel();
		User user = new User();
		user.setId(userId);
		TravelScheduleAgenda agenda = new TravelScheduleAgenda();
		agenda.setAgendaId(agendaId);
		hotel.setAgenda(agenda);
		hotel.setUser(user);
		hotel.setHotelName(hotelName);
		hotel.setHotelInfo(hotelInfo);
		int hotelId = 0;
		try {
			hotelId = hotelServ.createAgendaHotel(hotel);
		} catch (Exception e) {
			success = false;
			message = e.getMessage();
			logger.error(message);
		}finally{
			modelMap.put(Constant.SUCCESS, success);
			modelMap.put(Constant.MESSAGE, message);
			modelMap.put("hotelId", hotelId);
		}

		return modelMap;
	}
	
	
	@RequestMapping(value="/travel/hotel/update",method=RequestMethod.PUT)
	@ResponseBody
	public Map<String,Object> updateAgendaHotel(@RequestBody TravelAgendaHotel entity){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = true;
		String message = Constant.SUCCESS_MESSAGE;
		try {
			hotelServ.updateAgendaHotel(entity);
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
	
	@RequestMapping(value="/travel/hotel/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> deleteAgendaHotel(HttpServletRequest request, @RequestBody TravelAgendaHotel entity){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = true;
		String message = Constant.SUCCESS_MESSAGE;
		try {
			hotelServ.deleteAgendaHotel(entity);
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
	
	@RequestMapping(value="/travel/hotel/query",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryAgendaHotelByAgendaId(@RequestBody Map map){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		int agendaId = (Integer) map.get("agendaId");		
		boolean success = true;
		String message = Constant.SUCCESS_MESSAGE;		
		TravelAgendaHotel hotel = null;
		try {		
			hotel = hotelServ.queryAgendaHotelByAgendaId(agendaId);
		} catch (Exception e) {
			success = false;
			message = e.getMessage();
			logger.error(message);
		}finally{
			modelMap.put(Constant.SUCCESS, success);
			modelMap.put(Constant.MESSAGE, message);
			modelMap.put("hotel", hotel);
		}

		return modelMap;
	}
	
}
