package com.netease.yixing.dao;

import java.util.List;

import com.netease.yixing.model.TravelRecord;

public interface ITravelRecordDao {

	public List<TravelRecord> queryByTravelId(int travelId);
	
	public int insertTravelRecord(TravelRecord travelrecord);
	
	public boolean editTravelRecord(TravelRecord travelrecord);
	
	public void removeTravelRecordById(int id);
}
