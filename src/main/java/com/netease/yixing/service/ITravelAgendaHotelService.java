package com.netease.yixing.service;

import com.netease.yixing.model.TravelAgendaHotel;

public interface ITravelAgendaHotelService {

	public int createAgendaHotel(TravelAgendaHotel entity) throws Exception;
	
	public void updateAgendaHotel(TravelAgendaHotel entity) throws Exception;
	
	public void deleteAgendaHotel(TravelAgendaHotel entity) throws Exception;
	
	public TravelAgendaHotel queryAgendaHotelByAgendaId(int agendaId) throws Exception;
	
}
