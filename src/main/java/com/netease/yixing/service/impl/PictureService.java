package com.netease.yixing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.yixing.dao.IPictureDao;
import com.netease.yixing.model.Picture;
import com.netease.yixing.service.IPictureService;

@Service
public class PictureService implements IPictureService{
	
	@Autowired
	private IPictureDao pictureDao;
	@Override
	public List<Picture> queryByRecordId(int recordid) {
		// TODO Auto-generated method stub
		return pictureDao.queryByRecordId(recordid);
	}

	@Override
	public void insertPicture(Picture pic) {
		// TODO Auto-generated method stub
		pictureDao.insertPicture(pic);
	}

}
