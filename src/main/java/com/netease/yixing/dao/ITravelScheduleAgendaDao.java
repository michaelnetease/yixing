package com.netease.yixing.dao;

import java.util.List;

import com.netease.yixing.model.TravelSchedule;
import com.netease.yixing.model.TravelScheduleAgenda;

public interface ITravelScheduleAgendaDao {

    public void insertScheduleAgenda(TravelScheduleAgenda entity);
    
    public void deleteScheduleAgenda(TravelScheduleAgenda entity);
    
    public TravelScheduleAgenda queryAgendaById(int agendaId);
    
    public List<TravelScheduleAgenda> queryAllAgendaByScheduleId(int scheduleId);
    
    
}
