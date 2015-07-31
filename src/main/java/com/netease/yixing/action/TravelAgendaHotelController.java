package com.netease.yixing.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.netease.yixing.model.TravelAgendaHotel;
import com.netease.yixing.model.TravelScheduleAgenda;
import com.netease.yixing.model.User;
import com.netease.yixing.service.ITravelAgendaHotelService;

@Controller
public class TravelAgendaHotelController {

	@Autowired
	private ITravelAgendaHotelService hotelServ;
		
	public ITravelAgendaHotelService getHotelServ() {
		return hotelServ;
	}

	public void setHotelServ(ITravelAgendaHotelService hotelServ) {
		this.hotelServ = hotelServ;
	}

	@RequestMapping(value="/travel/hotel/create",method=RequestMethod.POST)
	public Map<String,Object> createAgendaHotel(HttpServletRequest request, @RequestBody Map map){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = true;
		String message = "ok";
		int agendaId = Integer.parseInt((String)map.get("agendaId"));
		int userId = Integer.parseInt((String)map.get("userId"));
		String hotelName = (String)map.get("hotelName");
		String hotelInfo = (String)map.get("hotelInfo");
		
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
			e.printStackTrace();
		}finally{
			modelMap.put("success", success);
			modelMap.put("message", message);
			modelMap.put("hotelId", hotelId);
		}

		return modelMap;
	}
	
	
	@RequestMapping(value="/travel/hotel/update",method=RequestMethod.PUT)
	public Map<String,Object> updateAgendaHotel(HttpServletRequest request, @RequestBody TravelAgendaHotel entity){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = true;
		String message = "ok";
		try {
			hotelServ.updateAgendaHotel(entity);
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
	
	@RequestMapping(value="/travel/hotel/delete",method=RequestMethod.POST)
	public Map<String,Object> deleteAgendaHotel(HttpServletRequest request, @RequestBody TravelAgendaHotel entity){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean success = true;
		String message = "ok";
		try {
			hotelServ.deleteAgendaHotel(entity);
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
	
	@RequestMapping(value="/travel/hotel/query",method=RequestMethod.POST)
	public Map<String,Object> queryAgendaHotelByAgendaId(HttpServletRequest request, @RequestBody Map map){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		int agendaId = Integer.parseInt((String) map.get("agendaId"));		
		boolean success = true;
		String message = "ok";		
		TravelAgendaHotel hotel = null;
		try {		
			hotel = hotelServ.queryAgendaHotelByAgendaId(agendaId);
		} catch (Exception e) {
			success = false;
			message = e.getMessage();
			e.printStackTrace();
		}finally{
			modelMap.put("success", success);
			modelMap.put("message", message);
			modelMap.put("hotel", hotel);
		}

		return modelMap;
	}
	
}
