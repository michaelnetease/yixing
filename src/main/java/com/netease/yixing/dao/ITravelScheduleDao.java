package com.netease.yixing.dao;

import java.util.List;

import com.netease.yixing.model.TravelSchedule;

public interface ITravelScheduleDao {

    public void insertTravelSchedule(TravelSchedule entity);
    
    public void updateTravelSchedule(TravelSchedule entity);
    
    public void deleteTravelSchedule(TravelSchedule entity);
	
	public List<TravelSchedule> getAllJoinTravelSchedules(int[] scheduleIds) ;
	
	public TravelSchedule queryScheduleDetailsByScheduleId(int scheduleId);
	
	public List<TravelSchedule> querySchedulesByUserId(int userId);
	
	public List<TravelSchedule> queryTopKVisitedTravelSchedule(int k);
	
	public List<TravelSchedule> queryTopKMarkedTravelSchedule(int k);
	
	public TravelSchedule getJoinUserNumbersInSchedule(int scheduleId);
	
	public void updateSchedulePhoto(int scheduleId,String photoKey);
	
	public String getPhotoKeyByScheduleId(int scheduleId);
}
