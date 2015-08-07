package com.netease.yixing.dao;

import com.netease.yixing.model.Invitation;

public interface IInvitationDao {
	public void insertInvitation(Invitation invitation);
	public Invitation queryByRnd(String rnd);
	public Invitation queryByTravelId(String travelId);
}
