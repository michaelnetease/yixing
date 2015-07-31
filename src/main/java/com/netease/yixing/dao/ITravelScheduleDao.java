package com.netease.yixing.dao;

import java.util.List;

import com.netease.yixing.model.TravelSchedule;
import com.netease.yixing.model.User;

public interface ITravelScheduleDao {

    public void insertTravelSchedule(TravelSchedule entity);
    
    public void updateTravelSchedule(TravelSchedule entity);
    
    public void deleteTravelSchedule(TravelSchedule entity);
	
	public List<TravelSchedule> getAllJoinTravelSchedules(int[] scheduleIds) ;
	
	public TravelSchedule queryScheduleDetailsByScheduleId(int scheduleId);
	
	public List<TravelSchedule> queryTopKVisitedTravelSchedule(int k);
	
	public List<TravelSchedule> queryTopKMarkedTravelSchedule(int k);
}
