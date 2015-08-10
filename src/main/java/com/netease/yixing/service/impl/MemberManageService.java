package com.netease.yixing.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.yixing.dao.ILoginDao;
import com.netease.yixing.dao.IMemberManageDao;
import com.netease.yixing.dao.ITravelScheduleDao;
import com.netease.yixing.model.TravelSchedule;
import com.netease.yixing.model.User;
import com.netease.yixing.service.IMemberManageService;

@Service
public class MemberManageService implements IMemberManageService {

	
	@Autowired
	private IMemberManageDao memberManageDao;
	
	@Autowired
	private ITravelScheduleDao travelScheduleDao;
	
	@Autowired
	private ILoginDao loginDao;
	

	public IMemberManageDao getMemberManageDao() {
		return memberManageDao;
	}

	public void setMemberManageDao(IMemberManageDao memberManageDao) {
		this.memberManageDao = memberManageDao;
	}
	
	

	public ITravelScheduleDao getTravelScheduleDao() {
		return travelScheduleDao;
	}

	public void setTravelScheduleDao(ITravelScheduleDao travelScheduleDao) {
		this.travelScheduleDao = travelScheduleDao;
	}
	

	public ILoginDao getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(ILoginDao loginDao) {
		this.loginDao = loginDao;
	}

	@Override
	public String getMembersByTravelId(int travelId) throws Exception {
		
		return this.getMemberManageDao().getMembersByTravelId(travelId);
	}

	@Override
	public void updateMembers(Map insertData) throws Exception {
		this.getMemberManageDao().updateMembers(insertData);
		
	}

	@Override
	public String getPicByUserId(int userId) throws Exception {
		// TODO Auto-generated method stub
		return this.getMemberManageDao().getPicByUserId(userId);
	}
	@Override
	public User getUserById(int userId) throws Exception
	{
		return this.getMemberManageDao().getUserById(userId);
	}
	
	
	@Override
	public void addMember(int userId,int schedule_id) throws Exception
	{
		String addId = String.valueOf(userId);
		String oldId = getMembersByTravelId(schedule_id);
		boolean isNum = addId.matches("[0-9]*");
		if (!isNum  || oldId==null  ||  oldId.contains(addId)) {
			throw new Exception();
		} else {
			String temp = oldId + ";;;" + addId;
			Map<String, String> updateData = new HashMap<String, String>();
			updateData.put("group_members", temp);
			updateData.put("scheduleId", String.valueOf(schedule_id));
			updateMembers(updateData);
		}
	}
	
	public List<User> queryLatestScheduleMembersByUserId(int userId) throws Exception {			
		List<User> members = null;
		User user = loginDao.queryUserById(userId);
		if(user!=null){
			String joinScheduleStr = user.getJoinTravelSchedule();
			String[] schedules = joinScheduleStr.split(";;;");
			if(schedules.length>0){
				String latestScheduleId = schedules[schedules.length-1];
				TravelSchedule latestSchedule = travelScheduleDao.getSimpleScheduleById(Integer.parseInt(latestScheduleId));
				if(latestSchedule!=null){
					String groupMembers = memberManageDao.getMembersByTravelId(latestSchedule.getScheduleId());
					if(groupMembers!=null && !groupMembers.isEmpty()){
						String[] groupMemberIdStr = groupMembers.split(";;;");
						int[] groupMemberIds = new int[groupMemberIdStr.length];
						for(int i=0;i<groupMemberIds.length;i++){
							groupMemberIds[i] = Integer.parseInt(groupMemberIdStr[i]);
						}
						members = loginDao.queryMembersByIds(groupMemberIds);
					}
				}
				
			}
		}
		return members;
	}


	
	

	
}
