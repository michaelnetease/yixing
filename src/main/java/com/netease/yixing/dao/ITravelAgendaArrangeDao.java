package com.netease.yixing.dao;

import java.util.List;

import com.netease.yixing.model.TravelAgendaArrange;
import com.netease.yixing.model.TravelScheduleAgenda;

public interface ITravelAgendaArrangeDao {

	public void insertTravelAgendaArrange(TravelAgendaArrange entity);
	
	public void updateTravelAgendaArrange(TravelAgendaArrange entity);
	
	public void deleteTravelAgendaArrange(TravelAgendaArrange entity);
	
	public List<TravelAgendaArrange> queryAllArrangeByAgendaId(TravelScheduleAgenda entity);
	
	public TravelAgendaArrange queryArrangeById(TravelAgendaArrange entity);
	
	public void deleteAllArrangeByAgendaId(int agendaId);
	
}
