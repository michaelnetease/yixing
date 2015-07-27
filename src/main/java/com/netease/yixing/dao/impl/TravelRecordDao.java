package com.netease.yixing.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import com.netease.yixing.dao.ITravelRecordDao;
import com.netease.yixing.model.TravelRecord;

@Repository
public class TravelRecordDao  extends SqlSessionDaoSupport implements ITravelRecordDao{

	@Override
	public List<TravelRecord> queryByTravelId(int travelId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("com.netease.yixing.model.TravelRecord.queryTravelRecordByTravelId",travelId); 
	}
	
	@Override
	public int insertTravelRecord(TravelRecord travelrecord) {
		// TODO Auto-generated method stub
		int ret =  getSqlSession().insert("com.netease.yixing.model.TravelRecord.insertTravelRecord",travelrecord);
		return ret;
	}

	@Override
	public void removeTravelRecordById(int id) {
		// TODO Auto-generated method stub
		getSqlSession().update("com.netease.yixing.model.TravelRecord.updateTravelRecord4notvalid",id);
	}

	@Override
	public boolean editTravelRecord(TravelRecord travelrecord) {
		// TODO Auto-generated method stub
		getSqlSession().insert("com.netease.yixing.model.TravelRecord.editTravelRecord",travelrecord);
		return true;
	}

}
