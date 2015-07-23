package com.netease.yixing.service;

import java.util.List;

import com.netease.yixing.model.Equipment;

public interface IHandlerEquipmentService {
	
	public List<Equipment> getItems(int travelId);
	
	public void updateSelectedItems(Equipment equipment);
	
	public void updateSelectedItems(List<Equipment> equipmentList);
	
	public void insertBasicItems(Equipment equipment) ;
	
	public void insertBasicItems(List<Equipment> equipmentList);
	
	public String getSelectedItems(int travelId);
}
