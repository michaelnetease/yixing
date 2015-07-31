package com.netease.yixing.dao.impl;

import java.util.List;

import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import com.netease.yixing.cache.redis.AbstractBaseRedisDao;
import com.netease.yixing.dao.ITravelScheduleRedisDao;
import com.netease.yixing.model.TravelSchedule;
import com.netease.yixing.utils.Constant;

@Repository
public class TravelScheduleRedisDao extends AbstractBaseRedisDao<String, List<TravelSchedule>> implements ITravelScheduleRedisDao {
		
	@Override
	public boolean saveTopKVisitedTravelScheduleToMemory(final List<TravelSchedule> schedules,final int k) {
        boolean success = true;
        try{
        	ValueOperations<String, List<TravelSchedule>> valOps = redisTemplate.opsForValue();
        	redisTemplate.setValueSerializer(jdkSerializer);
        	valOps.set(Constant.TOP_K_VISIT_TRAVEL_SCHEDULE+"."+k, schedules);        	
        }catch(Exception e){
        	success = false;
        	e.printStackTrace();
        }

		return success;
	}

	@Override
	public List<TravelSchedule> queryTopKVisitedTravelScheduleInMemory(int k) {
		ValueOperations<String, List<TravelSchedule>> valOps = redisTemplate.opsForValue();
		redisTemplate.setValueSerializer(jdkSerializer);
		List<TravelSchedule> result = null;
		try{
			result = valOps.get(Constant.TOP_K_VISIT_TRAVEL_SCHEDULE+"."+k);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;	
	}

	@Override
	public boolean saveTopKMarkedTravelScheduleToMemory(List<TravelSchedule> schedules, int k) {
		boolean success = true;
		try{
			ValueOperations<String, List<TravelSchedule>> valOps = redisTemplate.opsForValue();
			redisTemplate.setValueSerializer(jdkSerializer);
			valOps.set(Constant.TOP_K_MARK_TRAVEL_SCHEDULE+"."+k, schedules);
		}catch(Exception e){
			success = false;
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public List<TravelSchedule> queryTopKMarkedTravelScheduleInMemory(int k) {
		ValueOperations<String, List<TravelSchedule>> valOps = redisTemplate.opsForValue();
		redisTemplate.setValueSerializer(jdkSerializer);
		List<TravelSchedule> result = null;
		try{
			result = valOps.get(Constant.TOP_K_MARK_TRAVEL_SCHEDULE+"."+k);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void cleanTopKVisitedTravelScheduleInMemory(int k) {
		redisTemplate.delete(Constant.TOP_K_VISIT_TRAVEL_SCHEDULE+"."+k);		
	}

	@Override
	public void cleanTopKMarkedTravelScheduleInMemory(int k) {
		redisTemplate.delete(Constant.TOP_K_MARK_TRAVEL_SCHEDULE+"."+k);		
	}

}
