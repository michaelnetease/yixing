package com.netease.yixing.dao;

import com.netease.yixing.model.TravelAgendaHotel;

public interface ITravelAgendaHotelDao {

    public void insertAgendaHotel(TravelAgendaHotel entity);
    
    public void updateTravelAgendaHotel(TravelAgendaHotel entity);
    
    public void deleteTravelAgendaHotel(TravelAgendaHotel entity);
	
	public TravelAgendaHotel queryTravelAgendHotelByAgendaId(int agendaId);
	
	public void deleteHotelByAgendaId(int agendaId);
	
}
