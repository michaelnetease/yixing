package com.netease.yixing.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.netease.yixing.dao.IHandlerEquipmentDao;
import com.netease.yixing.model.Equipment;

@Repository
public class HandlerEquipmentDao extends SqlSessionDaoSupport implements IHandlerEquipmentDao{


	@Override
	public List<Equipment> getItems(int travelId)throws Exception {
		
		return getSqlSession().selectList("getItems", travelId);
	}

	@Override
	public void updateSelectedItems(Equipment equipment) throws Exception{
		getSqlSession().update("updateSelectedItems", equipment);
		
	}

	@Override
	public void insertBasicItems(Equipment equipment) throws Exception{
		getSqlSession().insert("insertBasicItems", equipment);
		
	}
	
	@Override
	public List<String> getSelectedItems(int travelId) throws Exception
	{
		return getSqlSession().selectList("getSelectedItems", travelId);
	}

}
