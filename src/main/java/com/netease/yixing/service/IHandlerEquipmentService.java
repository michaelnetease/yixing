package com.netease.yixing.service;

import java.util.List;

import com.netease.yixing.model.Equipment;

public interface IHandlerEquipmentService {
	
	public List<Equipment> getItems(int travelId)throws Exception;
	
	public void updateSelectedItems(Equipment equipment)throws Exception;
	
	public void updateSelectedItems(List<Equipment> equipmentList) throws Exception;
	
	public void insertBasicItems(Equipment equipment)throws Exception ;
	
	public void insertBasicItems(List<Equipment> equipmentList)throws Exception;
	
	public String getSelectedItems(int travelId)throws Exception;
}
