package com.netease.yixing.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.netease.yixing.dao.ITravelScheduleDao;
import com.netease.yixing.model.TravelSchedule;
import com.netease.yixing.model.TravelScheduleAgenda;
import com.netease.yixing.model.User;

@Repository
public class TravelScheduleDao extends SqlSessionDaoSupport implements ITravelScheduleDao {

	@Override
	public void insertTravelSchedule(TravelSchedule entity) {
		getSqlSession().insert("com.netease.yixing.model.TravelSchedule.insertSchedule",entity);

	}

	@Override
	public void updateTravelSchedule(TravelSchedule entity) {
		getSqlSession().update("com.netease.yixing.model.TravelSchedule.updateSchedule",entity);		
	}

	@Override
	public void deleteTravelSchedule(TravelSchedule entity) {
		getSqlSession().update("com.netease.yixing.model.TravelSchedule.deleteSchedule",entity);			
	}

	
	@Override
	public List<TravelSchedule> getAllJoinTravelSchedules(int[] scheduleIds) {
		return getSqlSession().selectList("com.netease.yixing.model.TravelSchedule.getAllJoinSchedules", scheduleIds);
	}

	@Override
	public TravelSchedule queryScheduleDetailsByScheduleId(int scheduleId) {
		return getSqlSession().selectOne("com.netease.yixing.model.TravelSchedule.getScheduleById",scheduleId);
	}


}
