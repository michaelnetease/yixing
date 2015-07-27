package com.netease.yixing.service;

import java.util.List;

import com.netease.yixing.model.TravelRecord;

public interface ITravelRecordService {
	public List<TravelRecord> queryByTravelId(int travelId);
	
	public int insertTravelRecord(TravelRecord travelrecord);
	
	public void removeTravelRecordById(int id);
	
	public boolean editTravelRecord(TravelRecord travelrecord);
}
