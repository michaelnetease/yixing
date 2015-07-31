package com.netease.yixing.dao;

import java.util.List;

import com.netease.yixing.model.TravelSchedule;

public interface ITravelScheduleRedisDao {

	public boolean saveTopKVisitedTravelScheduleToMemory(List<TravelSchedule> schedules,int k);
	
	public List<TravelSchedule> queryTopKVisitedTravelScheduleInMemory(int k);
	
	public boolean saveTopKMarkedTravelScheduleToMemory(List<TravelSchedule> schedules,int k);
	
	public List<TravelSchedule> queryTopKMarkedTravelScheduleInMemory(int k);
	
	public void cleanTopKVisitedTravelScheduleInMemory(int k);
	
	public void cleanTopKMarkedTravelScheduleInMemory(int k);
	
}
