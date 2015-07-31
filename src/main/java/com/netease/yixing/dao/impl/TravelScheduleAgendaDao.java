package com.netease.yixing.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.netease.yixing.dao.ITravelScheduleAgendaDao;
import com.netease.yixing.model.TravelScheduleAgenda;

@Repository
public class TravelScheduleAgendaDao extends SqlSessionDaoSupport implements ITravelScheduleAgendaDao {

	@Override
	public void insertScheduleAgenda(TravelScheduleAgenda entity) {
		getSqlSession().insert("com.netease.yixing.model.TravelScheduleAgenda.insertAgenda",entity);	
	}

	@Override
	public void deleteScheduleAgenda(TravelScheduleAgenda entity) {
		getSqlSession().update("com.netease.yixing.model.TravelScheduleAgenda.deleteAgendaById", entity);		
	}

	@Override
	public TravelScheduleAgenda queryAgendaById(int agendaId) {
		return getSqlSession().selectOne("com.netease.yixing.model.TravelScheduleAgenda.getAgendaById", agendaId);
	}

	@Override
	public List<TravelScheduleAgenda> queryAllAgendaByScheduleId(int scheduleId) {
		return getSqlSession().selectList("com.netease.yixing.model.TravelSchedule.getScheduleById", scheduleId);
	}

}
