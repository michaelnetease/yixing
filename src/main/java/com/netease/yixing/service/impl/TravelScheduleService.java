package com.netease.yixing.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netease.yixing.dao.ILoginDao;
import com.netease.yixing.dao.ITravelScheduleAgendaDao;
import com.netease.yixing.dao.ITravelScheduleDao;
import com.netease.yixing.dao.ITravelScheduleRedisDao;
import com.netease.yixing.model.TravelSchedule;
import com.netease.yixing.model.TravelScheduleAgenda;
import com.netease.yixing.model.User;
import com.netease.yixing.service.IInvitationService;
import com.netease.yixing.service.ITravelRecordService;
import com.netease.yixing.service.ITravelScheduleService;

@Service
public class TravelScheduleService implements ITravelScheduleService {

	@Autowired
	private ITravelScheduleDao travelScheduleDao;
	
	@Autowired
	private ITravelScheduleAgendaDao agendaDao;
	
	@Autowired
	private ILoginDao loginDao;
	
	@Autowired
	private ITravelScheduleRedisDao redisDao;
	
	@Autowired
	private IInvitationService invitationService;
	
	@Autowired
	private ITravelRecordService travelRecordService;
		
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

	public ITravelScheduleRedisDao getRedisDao() {
		return redisDao;
	}

	public void setRedisDao(ITravelScheduleRedisDao redisDao) {
		this.redisDao = redisDao;
	}

	public IInvitationService getInvitationService() {
		return invitationService;
	}

	public void setInvitationService(IInvitationService invitationService) {
		this.invitationService = invitationService;
	}

	public ITravelRecordService getTravelRecordService() {
		return travelRecordService;
	}

	public void setTravelRecordService(ITravelRecordService travelRecordService) {
		this.travelRecordService = travelRecordService;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor=Exception.class)
	public int createTravelSchedule(TravelSchedule entity) throws Exception {
		travelScheduleDao.insertTravelSchedule(entity);
		invitationService.insertInvitation(entity.getScheduleId());
		if(entity.getCreateUser()!=null){
			updateJoinSchedule(entity.getCreateUser().getId(), entity.getScheduleId());
			TravelScheduleAgenda firstDay = new TravelScheduleAgenda();
			firstDay.setSchedule(entity);
			firstDay.setTravelDay(entity.getStartTime());
			firstDay.setUpdateTime(new Date());
			firstDay.setUser(entity.getCreateUser());
			       
			TravelScheduleAgenda secondDay = new TravelScheduleAgenda();
			Calendar cal = Calendar.getInstance();
	        cal.setTime(entity.getStartTime());
	        cal.add(Calendar.DATE, 1);
			
			secondDay.setSchedule(entity);
			secondDay.setTravelDay(cal.getTime());
			secondDay.setUpdateTime(new Date());
			secondDay.setUser(entity.getCreateUser());

			agendaDao.insertScheduleAgenda(firstDay);
			agendaDao.insertScheduleAgenda(secondDay);
		}				

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
	
	
	public TravelSchedule queryLatestScheduleDetailsByUserId(int userId) throws Exception {	
		User user = loginDao.queryUserById(userId);
		TravelSchedule latestSchedule = null;
		if(user!=null){
			String allJoinSchedules = user.getJoinTravelSchedule();		
			if(allJoinSchedules!=null && !allJoinSchedules.isEmpty()){
				String[] scheduleIdStrs = allJoinSchedules.split(";;;");
				int[] scheduleIds = new int[scheduleIdStrs.length];
				for(int i=0;i<scheduleIds.length;i++){
					scheduleIds[i] = Integer.parseInt(scheduleIdStrs[i]);
				}
				List<TravelSchedule> scheduleList = travelScheduleDao.getAllJoinTravelSchedules(scheduleIds);
				if(scheduleList!=null && !scheduleList.isEmpty()){
					latestSchedule = scheduleList.get(0);
				}	
			}
		}

		return latestSchedule;
	}

	@Override
	@Transactional
	public void deleteTravelSchedule(TravelSchedule entity) throws Exception {
		entity.setUpdateTime(new Date());
		entity.setVisable(0);
		travelScheduleDao.deleteTravelSchedule(entity);
		travelRecordService.removeTravelRecordBySheduleId(entity.getScheduleId());
	}

	@Override
	public List<TravelSchedule> queryFixedLengthTravelInfoByUserId(int userId, int startIndex, int length)
			throws Exception {
		User user = loginDao.queryUserById(userId);
		List<TravelSchedule> result = new ArrayList<TravelSchedule>();
		if(user!=null){
			String joinSchedulesStr = user.getJoinTravelSchedule();
			if(joinSchedulesStr!=null && !joinSchedulesStr.isEmpty()){
				String[] joinSchedules = joinSchedulesStr.split(";;;");
				int[] scheduleIds = new int[joinSchedules.length];				
				for(int i=0;i<joinSchedules.length;i++){
					scheduleIds[i] = Integer.parseInt(joinSchedules[i]);	
				}
				List<TravelSchedule> ls = travelScheduleDao.getAllJoinTravelSchedules(scheduleIds);
				if(ls!=null && !ls.isEmpty()){
					for(int i=startIndex;i< startIndex + length && i < ls.size();i++){
						result.add(ls.get(i));
					}
				}
			}
		}
		
		return result;
	}

	@Override
	public List<TravelSchedule> queryTravelInfoByUserId(int userId) throws Exception {
		User user = loginDao.queryUserById(userId);
		List<TravelSchedule> ls = null;
		if(user!=null){
			String joinSchedulesStr = user.getJoinTravelSchedule();
			if(joinSchedulesStr!=null && !joinSchedulesStr.isEmpty()){
				String[] joinSchedules = joinSchedulesStr.split(";;;");
				int[] scheduleIds = new int[joinSchedules.length];
				for(int i=0; i < scheduleIds.length;i++){
					scheduleIds[i] = Integer.parseInt(joinSchedules[i]);
				}
				ls = travelScheduleDao.getAllJoinTravelSchedules(scheduleIds);			
			}
		}
		
		return ls;
	}

	@Override
	public List<TravelSchedule> queryTopKVisitedTravelSchedule(int k) {
		List<TravelSchedule> result = redisDao.queryTopKVisitedTravelScheduleInMemory(k);
		if(result==null || result.size() ==0){
			result = travelScheduleDao.queryTopKVisitedTravelSchedule(k);
			if(result!=null){
				redisDao.saveTopKVisitedTravelScheduleToMemory(result,k);
			}			
		}
		return result;
	}

	@Override
	public List<TravelSchedule> queryTopKMarkedTravelSchedule(int k) {
		List<TravelSchedule> result = redisDao.queryTopKMarkedTravelScheduleInMemory(k);
		if(result==null || result.size()==0){
			result = travelScheduleDao.queryTopKMarkedTravelSchedule(k);
			if(result !=null){
				redisDao.saveTopKMarkedTravelScheduleToMemory(result,k);
			}
		}
		return result;
	}

	@Override
	public int getJoinUserNumbersInSchedule(int scheduleId) {
		TravelSchedule schedule = travelScheduleDao.getJoinUserNumbersInSchedule(scheduleId);
		int num = 0;
		if(schedule!=null){
			String groupMemeberStr = schedule.getGroupMembers();
			if(groupMemeberStr!=null){
				String[] groupMembers = groupMemeberStr.split(";;;");
				num = groupMembers.length;
			}	
		}
		
		return num;	
	}

	@Override
	public void updateSchedulePhoto(int scheduleId, String photo) {
		travelScheduleDao.updateSchedulePhoto(scheduleId, photo);		
	}

	@Override
	public TravelSchedule getSimpleScheduleById(int scheduleId) {
		return travelScheduleDao.getSimpleScheduleById(scheduleId);
	}

	@Override
	public void updateJoinSchedule(int userId, int scheduleId) {
		User user = loginDao.queryUserById(userId);
		boolean flag = false;
		if(user!=null){
			String schedules = user.getJoinTravelSchedule();
			if(schedules==null || schedules.isEmpty()){
				schedules = ""+ scheduleId;
			}else{
				String[] schedule = schedules.split(";;;");
				for(String sid:schedule){
					if(sid.equals(scheduleId)){
						flag = true;
						break;
					}
				}
				
				if(!flag){
					schedules+= ";;;"+ scheduleId;
				}				
			}
			
			if(!flag){
				user.setJoinTravelSchedule(schedules);
				loginDao.updateJoinSchedule(user);
			}
		}		
	}	
	
	@Transactional
	public void removeJoinSchedule(int userId, int scheduleId) {
		User user = loginDao.queryUserById(userId);
		StringBuffer sb = new StringBuffer();
		if(user!=null){
			String schedules = user.getJoinTravelSchedule();
			if(schedules!=null && !schedules.isEmpty()){
				String[] schedule = schedules.split(";;;");
				for(String sid:schedule){
					if(!sid.equals(String.valueOf(scheduleId))){
						sb.append(sid);
						sb.append(";;;");
					}
				}
				
				if(sb.length()>=3){
					sb.delete(sb.length()-3, sb.length());
					user.setJoinTravelSchedule(sb.toString());
					loginDao.updateJoinSchedule(user);
				}	
			}

		}		
	}
	
}
