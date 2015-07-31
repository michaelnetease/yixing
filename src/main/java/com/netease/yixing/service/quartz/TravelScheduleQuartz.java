package com.netease.yixing.service.quartz;

import org.springframework.beans.factory.annotation.Autowired;

import com.netease.yixing.dao.ITravelScheduleRedisDao;
import com.netease.yixing.utils.Constant;

public class TravelScheduleQuartz {

	@Autowired
	private ITravelScheduleRedisDao redisDao;
	
	public ITravelScheduleRedisDao getRedisDao() {
		return redisDao;
	}

	public void setRedisDao(ITravelScheduleRedisDao redisDao) {
		this.redisDao = redisDao;
	}


	public void cleanTopKTravelScheduleInMemory(){
		System.out.println("hello");
		redisDao.cleanTopKMarkedTravelScheduleInMemory(Constant.TOP_K_TRAVEL_SCHEDULE);
		redisDao.cleanTopKVisitedTravelScheduleInMemory(Constant.TOP_K_TRAVEL_SCHEDULE);
	}
	
	
}
