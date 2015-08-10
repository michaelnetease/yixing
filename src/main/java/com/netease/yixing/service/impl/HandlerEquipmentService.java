package com.netease.yixing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.yixing.dao.IHandlerEquipmentDao;
import com.netease.yixing.model.Equipment;
import com.netease.yixing.service.IHandlerEquipmentService;

@Service
public class HandlerEquipmentService implements IHandlerEquipmentService {

	@Autowired
	private IHandlerEquipmentDao handlerEquipmentDao;
	
	public IHandlerEquipmentDao getHandlerEquipmentDao() {
		return handlerEquipmentDao;
	}

	public void setHandlerEquipmentDao(IHandlerEquipmentDao handlerEquipmentDao) {
		this.handlerEquipmentDao = handlerEquipmentDao;
	}

	@Override
	public List<Equipment> getItems(int travelId)throws Exception{
		List<Equipment> equipmentList=handlerEquipmentDao.getItems(travelId);
		return equipmentList;
	}

	@Override
	public void updateSelectedItems(Equipment equipment) throws Exception {
		
		handlerEquipmentDao.updateSelectedItems(equipment);
		
	}

	@Override
	public void updateSelectedItems(List<Equipment> equipmentList) throws Exception{
		for(Equipment temp:equipmentList)
		{
			handlerEquipmentDao.updateSelectedItems(temp);
		}
	}

	@Override
	public void insertBasicItems(Equipment equipment) throws Exception{
		handlerEquipmentDao.insertBasicItems(equipment);
		
	}

	@Override
	public void insertBasicItems(List<Equipment> equipmentList)throws Exception{
		for(Equipment temp:equipmentList)
		{
			handlerEquipmentDao.insertBasicItems(temp);
		}
		
	}
	
	@Override
	public String getSelectedItems(int travelId)throws Exception
	{
		List<String> tempList=handlerEquipmentDao.getSelectedItems(travelId);
		StringBuffer sb=new StringBuffer();
		for(String str:tempList)
		{	
			if(str==null || str.length()==0) continue;
			String[] temps=str.split(";;;");
			for(String s:temps)
			{
				if(s.length()>0)
					sb.append(s+",");
			}
		}
		
		int index=sb.lastIndexOf(",");
		if(index>=0)
			sb.deleteCharAt(index);
		return sb.toString();
	}


	


}
