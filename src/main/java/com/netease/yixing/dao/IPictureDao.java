package com.netease.yixing.dao;

import java.util.List;
import com.netease.yixing.model.Picture;

public interface IPictureDao {
	
    public List<Picture> queryByRecordId(int recordid);
	
	public void insertPicture(Picture pic);
	
}
