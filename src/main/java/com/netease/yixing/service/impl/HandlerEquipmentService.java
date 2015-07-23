package com.netease.yixing.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.netease.yixing.dao.IHandlerEquipmentDao;
import com.netease.yixing.model.Equipment;
import com.netease.yixing.service.IHandlerEquipmentService;

@Service
public class HandlerEquipmentService implements IHandlerEquipmentService {

	private IHandlerEquipmentDao handlerEquipmentDao;
	
	


	public IHandlerEquipmentDao getHandlerEquipmentDao() {
		return handlerEquipmentDao;
	}

	public void setHandlerEquipmentDao(IHandlerEquipmentDao handlerEquipmentDao) {
		this.handlerEquipmentDao = handlerEquipmentDao;
	}

	@Override
	public List<Equipment> getItems(int travelId){
		List<Equipment> equipmentList=handlerEquipmentDao.getItems(travelId);
		return equipmentList;
	}

	@Override
	public void updateSelectedItems(Equipment equipment)  {
		
		handlerEquipmentDao.updateSelectedItems(equipment);
		
	}

	@Override
	public void updateSelectedItems(List<Equipment> equipmentList) {
		for(Equipment temp:equipmentList)
		{
			handlerEquipmentDao.updateSelectedItems(temp);
		}
		
	}

	@Override
	public void insertBasicItems(Equipment equipment) {
		handlerEquipmentDao.insertBasicItems(equipment);
		
	}

	@Override
	public void insertBasicItems(List<Equipment> equipmentList){
		for(Equipment temp:equipmentList)
		{
			handlerEquipmentDao.insertBasicItems(temp);
		}
		
	}
	
	@Override
	public String getSelectedItems(int travelId)
	{
		List<String> tempList=handlerEquipmentDao.getSelectedItems(travelId);
		StringBuffer sb=new StringBuffer();
		for(String str:tempList)
		{	
			if(str==null) continue;
			String[] temps=str.split(";;;");
			for(String s:temps)
				sb.append(s+",");
		}
		sb.delete(sb.toString().length()-1, sb.toString().length());
		return sb.toString();
	}


	


}
