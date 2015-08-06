package com.netease.yixing.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.netease.yixing.dao.IPictureDao;
import com.netease.yixing.model.Picture;
@Repository
public class PictureDao extends SqlSessionDaoSupport implements IPictureDao{

	@Override
	public List<Picture> queryByRecordId(int recordid) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("com.netease.yixing.model.Picture.queryPictureByRecordId",recordid);
	}

	@Override
	public void insertPicture(Picture pic) {
		// TODO Auto-generated method stub
		getSqlSession().insert("com.netease.yixing.model.Picture.insertPicture",pic);
	}

}
