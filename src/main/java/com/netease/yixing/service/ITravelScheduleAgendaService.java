package com.netease.yixing.service;

import java.util.List;

import com.netease.yixing.model.TravelScheduleAgenda;

public interface ITravelScheduleAgendaService {
	
	public int createScheduleAgenda(TravelScheduleAgenda entity) throws Exception;

    public void deleteScheduleAgenda(TravelScheduleAgenda entity) throws Exception;
    
    public TravelScheduleAgenda queryAgendaById(int agendaId) throws Exception;
    
    public List<TravelScheduleAgenda> queryAllScheduleAgendaByScheuldeId(int scheduleId) throws Exception;
	
}
