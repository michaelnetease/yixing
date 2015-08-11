package com.netease.yixing.model;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;

public class TravelAgendaArrange implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2916715808520303132L;

	private int arrangeId;
	
	private String timePoint;
	
	private String event;
	
	private String info;
	
	private Date updateTime;
	
	@JsonIgnore
	private TravelScheduleAgenda agenda;
	@JsonIgnore
	private int visable;
	
	@JsonIgnore
	private User user;

	public int getArrangeId() {
		return arrangeId;
	}

	public void setArrangeId(int arrangeId) {
		this.arrangeId = arrangeId;
	}

	public String getTimePoint() {
		return timePoint;
	}

	public void setTimePoint(String timePoint) {
		this.timePoint = timePoint;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public TravelScheduleAgenda getAgenda() {
		return agenda;
	}

	public void setAgenda(TravelScheduleAgenda agenda) {
		this.agenda = agenda;
	}




	public int getVisable() {
		return visable;
	}

	public void setVisable(int visable) {
		this.visable = visable;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
	
}
