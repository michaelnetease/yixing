package com.netease.yixing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.yixing.dao.ITravelRecordDao;
import com.netease.yixing.dao.impl.TravelRecordDao;
import com.netease.yixing.model.TravelRecord;
import com.netease.yixing.service.ITravelRecordService;

@Service
public class TravelRecordService implements ITravelRecordService{
	@Autowired
	private ITravelRecordDao travelRecordDao;
	@Override
	public List<TravelRecord> queryByTravelId(int travelId) {
		// TODO Auto-generated method stub
		return travelRecordDao.queryByTravelId(travelId);
	}

	@Override
	public int insertTravelRecord(TravelRecord travelrecord) {
		// TODO Auto-generated method stub
		return travelRecordDao.insertTravelRecord(travelrecord);
	}

	@Override
	public void removeTravelRecordById(int id) {
		// TODO Auto-generated method stub
		travelRecordDao.removeTravelRecordById(id);
	}

	@Override
	public boolean editTravelRecord(TravelRecord travelrecord) {
		// TODO Auto-generated method stub
		//(((((checkuser_)))))) 
		
		return travelRecordDao.editTravelRecord(travelrecord);
	}
}
