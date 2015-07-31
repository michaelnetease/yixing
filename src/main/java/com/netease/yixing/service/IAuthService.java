package com.netease.yixing.service;

import com.netease.yixing.model.Auth2;

public interface IAuthService {
	public Auth2 queryByUid(int uid);
	public void insert(Auth2 auth);
	public void update(Auth2 auth);
}
