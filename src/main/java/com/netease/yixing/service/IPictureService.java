package com.netease.yixing.service;

import java.util.List;

import com.netease.yixing.model.Picture;

public interface IPictureService {
	    
	    public List<Picture> queryByRecordId(int recordid);
		
		public void insertPicture(Picture pic);
}
