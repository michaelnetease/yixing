package com.netease.yixing.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.netease.yixing.dao.ITravelAgendaHotelDao;
import com.netease.yixing.model.TravelAgendaHotel;

@Repository
public class TravelAgendaHotelDao extends SqlSessionDaoSupport implements ITravelAgendaHotelDao {

	@Override
	public void insertAgendaHotel(TravelAgendaHotel entity) {
		getSqlSession().insert("com.netease.yixing.model.TravelAgendaHotel.insertHotel", entity);
	}

	@Override
	public void updateTravelAgendaHotel(TravelAgendaHotel entity) {
		getSqlSession().update("com.netease.yixing.model.TravelAgendaHotel.updateHotel", entity);
	}

	@Override
	public void deleteTravelAgendaHotel(TravelAgendaHotel entity) {
		getSqlSession().update("com.netease.yixing.model.TravelAgendaHotel.deleteHotelByHotelId", entity);
	}

	@Override
	public TravelAgendaHotel queryTravelAgendHotelByAgendaId(int agendaId) {
		return getSqlSession().selectOne("com.netease.yixing.model.TravelAgendaHotel.selectHotel", agendaId);
	}

	@Override
	public void deleteHotelByAgendaId(int agendaId) {
		getSqlSession().update("com.netease.yixing.model.TravelAgendaHotel.deleteHotelByAgendaId", agendaId);
		
	}

}
