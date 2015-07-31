package com.netease.yixing.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.netease.yixing.dao.ITravelAgendaArrangeDao;
import com.netease.yixing.model.TravelAgendaArrange;
import com.netease.yixing.model.TravelScheduleAgenda;

@Repository
public class TravelAgendaArrangeDao extends SqlSessionDaoSupport implements ITravelAgendaArrangeDao {

	@Override
	public void insertTravelAgendaArrange(TravelAgendaArrange entity) {
		getSqlSession().insert("com.netease.yixing.model.AgendaArrange.insertArrange", entity);

	}

	@Override
	public void updateTravelAgendaArrange(TravelAgendaArrange entity) {
		getSqlSession().update("com.netease.yixing.model.AgendaArrange.updateArrange", entity);

	}

	@Override
	public void deleteTravelAgendaArrange(TravelAgendaArrange entity) {
		getSqlSession().update("com.netease.yixing.model.AgendaArrange.deleteArrangeByArrangeId", entity);

	}

	@Override
	public List<TravelAgendaArrange> queryAllArrangeByAgendaId(TravelScheduleAgenda entity) {
		return getSqlSession().selectList("com.netease.yixing.model.AgendaArrange.queryAllArrangeByAgendaId", entity);
	}

	@Override
	public TravelAgendaArrange queryArrangeById(TravelAgendaArrange entity) {
		return getSqlSession().selectOne("com.netease.yixing.model.AgendaArrange.queryArrangeById",entity.getArrangeId());
	}

	@Override
	public void deleteAllArrangeByAgendaId(int agendaId) {
		getSqlSession().update("com.netease.yixing.model.AgendaArrange.deleteArrangeByAgendaId", agendaId);		
	}

}
