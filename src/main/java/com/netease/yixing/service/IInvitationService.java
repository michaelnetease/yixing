package com.netease.yixing.service;

import com.netease.yixing.model.Invitation;

public interface IInvitationService {
	public void insertInvitation(Invitation invitation);
	public Invitation queryByRnd(String rnd);
	public Invitation queryByTravelId(String travelId);
}
