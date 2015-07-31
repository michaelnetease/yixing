package com.netease.yixing.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.yixing.dao.ITravelAgendaArrangeDao;
import com.netease.yixing.model.TravelAgendaArrange;
import com.netease.yixing.model.TravelScheduleAgenda;
import com.netease.yixing.service.ITravelAgendaArrangeService;

@Service
public class TravelAgendaArrangeService implements ITravelAgendaArrangeService {

	@Autowired
	private ITravelAgendaArrangeDao arrangeDao;
	
	public ITravelAgendaArrangeDao getArrangeDao() {
		return arrangeDao;
	}

	public void setArrangeDao(ITravelAgendaArrangeDao arrangeDao) {
		this.arrangeDao = arrangeDao;
	}

	@Override
	public int createTravelAgendaArrange(TravelAgendaArrange entity) throws Exception {
		entity.setUpdateTime(new Date());
		arrangeDao.insertTravelAgendaArrange(entity);
		return entity.getArrangeId();
	}

	@Override
	public void updateTravelAgendaArrange(TravelAgendaArrange entity) throws Exception {
		entity.setUpdateTime(new Date());
		arrangeDao.updateTravelAgendaArrange(entity);
	}

	@Override
	public void deleteTravelAgendaArrange(TravelAgendaArrange entity) throws Exception {
		entity.setUpdateTime(new Date());
		entity.setVisable(false);
		arrangeDao.deleteTravelAgendaArrange(entity);
	}

	@Override
	public List<TravelAgendaArrange> queryAllArrangeByAgendaId(TravelScheduleAgenda entity) throws Exception {
		return arrangeDao.queryAllArrangeByAgendaId(entity);
	}

	@Override
	public TravelAgendaArrange queryArrangeById(TravelAgendaArrange entity) throws Exception {
		return arrangeDao.queryArrangeById(entity);
	}

}
