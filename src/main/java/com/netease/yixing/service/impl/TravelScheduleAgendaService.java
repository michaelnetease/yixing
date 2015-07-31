package com.netease.yixing.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netease.yixing.dao.ITravelAgendaArrangeDao;
import com.netease.yixing.dao.ITravelAgendaHotelDao;
import com.netease.yixing.dao.ITravelScheduleAgendaDao;
import com.netease.yixing.model.TravelScheduleAgenda;
import com.netease.yixing.service.ITravelScheduleAgendaService;

@Service
public class TravelScheduleAgendaService implements ITravelScheduleAgendaService {

	@Autowired
	private ITravelScheduleAgendaDao agendaDao;
	
	@Autowired
	private ITravelAgendaArrangeDao arrangeDao;
	
	@Autowired
	private ITravelAgendaHotelDao hotelDao;
	
	public ITravelScheduleAgendaDao getAgendaDao() {
		return agendaDao;
	}

	public void setAgendaDao(ITravelScheduleAgendaDao agendaDao) {
		this.agendaDao = agendaDao;
	}	

	public ITravelAgendaArrangeDao getArrangeDao() {
		return arrangeDao;
	}

	public void setArrangeDao(ITravelAgendaArrangeDao arrangeDao) {
		this.arrangeDao = arrangeDao;
	}

	public ITravelAgendaHotelDao getHotelDao() {
		return hotelDao;
	}

	public void setHotelDao(ITravelAgendaHotelDao hotelDao) {
		this.hotelDao = hotelDao;
	}

	@Transactional
	@Override
	public int createScheduleAgenda(TravelScheduleAgenda entity) throws Exception {
		entity.setUpdateTime(new Date());
		agendaDao.insertScheduleAgenda(entity);
		return entity.getAgendaId();
	}

	@Transactional
	@Override
	public void deleteScheduleAgenda(TravelScheduleAgenda entity) throws Exception {
		arrangeDao.deleteAllArrangeByAgendaId(entity.getAgendaId());
		hotelDao.deleteHotelByAgendaId(entity.getAgendaId());
		entity.setUpdateTime(new Date());		
		agendaDao.deleteScheduleAgenda(entity);	
	}

	@Override
	public TravelScheduleAgenda queryAgendaById(int agendaId) throws Exception {
		return agendaDao.queryAgendaById(agendaId);
	}

	@Override
	public List<TravelScheduleAgenda> queryAllScheduleAgendaByScheuldeId(int scheduleId) throws Exception {
		return agendaDao.queryAllAgendaByScheduleId(scheduleId);
	}



}
