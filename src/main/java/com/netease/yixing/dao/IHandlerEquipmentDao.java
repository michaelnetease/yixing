package com.netease.yixing.dao;

import java.util.List;

import com.netease.yixing.model.Equipment;

public interface IHandlerEquipmentDao {
	public List<Equipment> getItems(int travelId) throws Exception;
	
	public void updateSelectedItems(Equipment equipment)throws Exception;
	
	public void insertBasicItems(Equipment equipment)throws Exception;
	
	public List<String> getSelectedItems(int travelId)throws Exception;
	
}
