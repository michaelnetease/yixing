package com.netease.yixing.dao;

import com.netease.yixing.model.Auth2;

public interface IAuthDao {
	public Auth2 queryByUid(int uid);
	public void insert(Auth2 auth);
	public void update(Auth2 auth);
}
