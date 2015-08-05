package com.netease.yixing.service;

import java.util.List;

import com.netease.yixing.model.TravelRecord;

public interface ITravelRecordService {
	public List<TravelRecord> queryByTravelId(int travelId);
	
	public int insertTravelRecord(TravelRecord travelrecord);
	
	public void removeTravelRecordById(int id);
	
	public boolean editTravelRecord(TravelRecord travelrecord);
	
	public List<TravelRecord> queryByTravelIdAndPage(int travelId,int skip,int length);
	
	public List<TravelRecord> queryAllByUserId(int travelId,int skip,int length);
	
}
