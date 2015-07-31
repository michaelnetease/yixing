package com.netease.yixing.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.yixing.dao.ITravelAgendaHotelDao;
import com.netease.yixing.model.TravelAgendaHotel;
import com.netease.yixing.service.ITravelAgendaHotelService;

@Service
public class TravelAgendaHotelService implements ITravelAgendaHotelService {

	@Autowired
	private ITravelAgendaHotelDao hotelDao;
	
	
	public ITravelAgendaHotelDao getHotelDao() {
		return hotelDao;
	}

	public void setHotelDao(ITravelAgendaHotelDao hotelDao) {
		this.hotelDao = hotelDao;
	}

	@Override
	public int createAgendaHotel(TravelAgendaHotel entity) throws Exception {
		entity.setUpdateTime(new Date());
		hotelDao.insertAgendaHotel(entity);
		return entity.getHotelId();
	}

	@Override
	public void updateAgendaHotel(TravelAgendaHotel entity) throws Exception {
		entity.setUpdateTime(new Date());
		hotelDao.updateTravelAgendaHotel(entity);
	}

	@Override
	public void deleteAgendaHotel(TravelAgendaHotel entity) throws Exception {
		entity.setUpdateTime(new Date());
		hotelDao.deleteTravelAgendaHotel(entity);
	}

	@Override
	public TravelAgendaHotel queryAgendaHotelByAgendaId(int agendaId) throws Exception {
		return hotelDao.queryTravelAgendHotelByAgendaId(agendaId);
	}

}
