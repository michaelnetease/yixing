package com.netease.yixing.dao;

import java.util.List;

import com.netease.yixing.model.Equipment;

public interface IHandlerEquipmentDao {
	public List<Equipment> getItems(int travelId);
	
	public void updateSelectedItems(Equipment equipment);
	
	public void insertBasicItems(Equipment equipment);
	
	public List<String> getSelectedItems(int travelId);
	
}
