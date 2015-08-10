package com.netease.yixing.service;

import java.util.List;

import com.netease.yixing.model.TravelSchedule;
import com.netease.yixing.model.User;

public interface ITravelScheduleService {

	public int createTravelSchedule(TravelSchedule entity) throws Exception;
	
	public void updateTravelSchedule(TravelSchedule entity) throws Exception;
	
	public void deleteTravelSchedule(TravelSchedule entity) throws Exception;
	
	public List<TravelSchedule> queryFixedLengthTravelInfoByUserId(int userId,int startIndex,int length) throws Exception;
	
	public List<TravelSchedule> queryTravelInfoByUserId(int userId) throws Exception;
	
	public TravelSchedule queryScheduleDetailsByScheduleId(int scheduleId) throws Exception;
	
	public TravelSchedule queryLatestScheduleDetailsByUserId(int userId) throws Exception;
	
	public List<TravelSchedule> queryTopKVisitedTravelSchedule(int k);
	
	public List<TravelSchedule> queryTopKMarkedTravelSchedule(int k);
	
	public int getJoinUserNumbersInSchedule(int scheduleId);
	
	public void updateSchedulePhoto(int scheduleId,String photo);
	
	public String getPhotoKeyByScheduleId(int scheduleId);
	
	public void updateJoinSchedule(int userId,int scheduleId);
	
}
