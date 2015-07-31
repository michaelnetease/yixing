package com.netease.yixing.service;

import java.util.List;

import com.netease.yixing.model.TravelAgendaArrange;
import com.netease.yixing.model.TravelScheduleAgenda;

public interface ITravelAgendaArrangeService {

	public int createTravelAgendaArrange(TravelAgendaArrange entity) throws Exception;
	
	public void updateTravelAgendaArrange(TravelAgendaArrange entity) throws Exception;
	
	public void deleteTravelAgendaArrange(TravelAgendaArrange entity) throws Exception;
	
	public List<TravelAgendaArrange> queryAllArrangeByAgendaId(TravelScheduleAgenda entity) throws Exception;
	
    public TravelAgendaArrange queryArrangeById(TravelAgendaArrange entity) throws Exception;
    
	
}
