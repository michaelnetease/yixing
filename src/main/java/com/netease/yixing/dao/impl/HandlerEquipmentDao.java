package com.netease.yixing.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.netease.yixing.dao.IHandlerEquipmentDao;
import com.netease.yixing.model.Equipment;

@Repository
public class HandlerEquipmentDao extends SqlSessionDaoSupport implements IHandlerEquipmentDao{


	@Override
	public List<Equipment> getItems(int travelId) {
		
		return getSqlSession().selectList("getItems", travelId);
	}

	@Override
	public void updateSelectedItems(Equipment equipment) {
		getSqlSession().update("updateSelectedItems", equipment);
		
	}

	@Override
	public void insertBasicItems(Equipment equipment) {
		getSqlSession().insert("insertBasicItems", equipment);
		
	}
	
	@Override
	public List<String> getSelectedItems(int travelId)
	{
		return getSqlSession().selectList("getSelectedItems", travelId);
	}

}
