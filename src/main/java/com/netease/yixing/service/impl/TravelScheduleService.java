package com.netease.yixing.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netease.yixing.dao.ILoginDao;
import com.netease.yixing.dao.ITravelScheduleAgendaDao;
import com.netease.yixing.dao.ITravelScheduleDao;
import com.netease.yixing.model.TravelSchedule;
import com.netease.yixing.model.TravelScheduleAgenda;
import com.netease.yixing.model.User;
import com.netease.yixing.service.ITravelScheduleService;

@Service
public class TravelScheduleService implements ITravelScheduleService {

	@Autowired
	private ITravelScheduleDao travelScheduleDao;
	
	@Autowired
	private ITravelScheduleAgendaDao agendaDao;
	
	@Autowired
	private ILoginDao loginDao;
		
	public ITravelScheduleDao getTravelScheduleDao() {
		return travelScheduleDao;
	}

	public void setTravelScheduleDao(ITravelScheduleDao travelScheduleDao) {
		this.travelScheduleDao = travelScheduleDao;
	}

	public ITravelScheduleAgendaDao getAgendaDao() {
		return agendaDao;
	}

	public void setAgendaDao(ITravelScheduleAgendaDao agendaDao) {
		this.agendaDao = agendaDao;
	}

	public ILoginDao getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(ILoginDao loginDao) {
		this.loginDao = loginDao;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor=Exception.class)
	public int createTravelSchedule(TravelSchedule entity) throws Exception {
		travelScheduleDao.insertTravelSchedule(entity);

		TravelScheduleAgenda firstDay = new TravelScheduleAgenda();
		firstDay.setSchedule(entity);
		firstDay.setTravelDay(entity.getStartTime());
		firstDay.setUpdateTime(new Date());
		firstDay.setUser(entity.getCreateUser());
		       
		TravelScheduleAgenda secondDay = new TravelScheduleAgenda();
		secondDay.setSchedule(entity);
		secondDay.setTravelDay(entity.getEndTime());
		secondDay.setUpdateTime(new Date());
		secondDay.setUser(entity.getCreateUser());

		agendaDao.insertScheduleAgenda(firstDay);
		agendaDao.insertScheduleAgenda(secondDay);
		return entity.getScheduleId();
	}
	

	@Override
	public void updateTravelSchedule(TravelSchedule entity) throws Exception {
		entity.setUpdateTime(new Date());
		travelScheduleDao.updateTravelSchedule(entity);
	}
	

	public TravelSchedule queryScheduleDetailsByScheduleId(int scheduleId) throws Exception {		
		return travelScheduleDao.queryScheduleDetailsByScheduleId(scheduleId);
	}

	@Override
	public void deleteTravelSchedule(TravelSchedule entity) throws Exception {
		entity.setUpdateTime(new Date());
		entity.setVisable(false);
		travelScheduleDao.deleteTravelSchedule(entity);
	}

	@Override
	public List<TravelSchedule> queryFixedLengthTravelInfoByUserId(int userId, int startIndex, int length)
			throws Exception {
		User user = new User();
		user.setId(userId);
		user = loginDao.queryUser(user);
		List<TravelSchedule> ls = null;
		String joinSchedulesStr = user.getJoinTravelSchedule();
		if(joinSchedulesStr!=null || joinSchedulesStr.length()>0){
			String[] joinSchedules = joinSchedulesStr.split(";;;");
			int[] scheduleIds = new int[length];
			int index = 0;
			for(int i=scheduleIds.length-1;i>=0;i--){
				if(i>=startIndex && i<startIndex+length){
					scheduleIds[index++] = Integer.parseInt(joinSchedules[i]);
				}			
			}
			ls = travelScheduleDao.getAllJoinTravelSchedules(scheduleIds);		
		}
		return ls;
	}

	@Override
	public List<TravelSchedule> queryTravelInfoByUserId(int userId) throws Exception {
		User user = new User();
		user.setId(userId);
		user = loginDao.queryUser(user);
		List<TravelSchedule> ls = null;
		String joinSchedulesStr = user.getJoinTravelSchedule();
		if(joinSchedulesStr!=null || joinSchedulesStr.length()>0){
			String[] joinSchedules = joinSchedulesStr.split(";;;");
			int[] scheduleIds = new int[joinSchedules.length];
			for(int i=0;i<scheduleIds.length;i++){
				scheduleIds[i] = Integer.parseInt(joinSchedules[i]);
			}
			ls = travelScheduleDao.getAllJoinTravelSchedules(scheduleIds);			
		}
		return ls;
	}
}
