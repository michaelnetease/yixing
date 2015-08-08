package com.netease.yixing.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.netease.yixing.dao.ITravelScheduleDao;
import com.netease.yixing.model.TravelSchedule;

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

	@Override
	public List<TravelSchedule> queryTopKVisitedTravelSchedule(int k) {
		return getSqlSession().selectList("com.netease.yixing.model.TravelSchedule.getTopKVisitedSchedule", k);
	}

	@Override
	public List<TravelSchedule> queryTopKMarkedTravelSchedule(int k) {
		return getSqlSession().selectList("com.netease.yixing.model.TravelSchedule.getTopKMarkedSchedule", k);
	}

	@Override
	public List<TravelSchedule> querySchedulesByUserId(int userId) {
		return getSqlSession().selectList("com.netease.yixing.model.TravelSchedule.getSchedulesByUserId",userId);
	}

	@Override
	public TravelSchedule getJoinUserNumbersInSchedule(int scheduleId) {
		return getSqlSession().selectOne("com.netease.yixing.model.TravelSchedule.getJoinUserNumbersInSchedule", scheduleId);
	}

	@Override
	public void updateSchedulePhoto(int scheduleId, String photoKey) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("scheduleId", scheduleId);
		map.put("photo", photoKey);
		getSqlSession().update("com.netease.yixing.model.TravelSchedule.updateSchedulePhoto", map);
		
	}

	@Override
	public String getPhotoKeyByScheduleId(int scheduleId) {
		TravelSchedule schedule = getSqlSession().selectOne("com.netease.yixing.model.TravelSchedule.getPhotoByScheduleId", scheduleId);
		return schedule!=null?schedule.getPhoto():null;
	}


}
