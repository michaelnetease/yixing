package com.netease.yixing.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

public class TravelScheduleAgenda implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8856917976564909085L;

	private int agendaId;
	
	private Date travelDay;
	
	private TravelSchedule schedule;
	
	private Date updateTime;
	
	@JsonIgnore
	private boolean visable;
	
	@JsonIgnore
	private User user;
	
	private List<TravelAgendaArrange> arrangeList;
	
	private List<TravelAgendaHotel> hotelList;

	public int getAgendaId() {
		return agendaId;
	}

	public void setAgendaId(int agendaId) {
		this.agendaId = agendaId;
	}

	public Date getTravelDay() {
		return travelDay;
	}

	public void setTravelDay(Date travelDay) {
		this.travelDay = travelDay;
	}

	public TravelSchedule getSchedule() {
		return schedule;
	}

	public void setSchedule(TravelSchedule schedule) {
		this.schedule = schedule;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public boolean isVisable() {
		return visable;
	}

	public void setVisable(boolean visable) {
		this.visable = visable;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<TravelAgendaArrange> getArrangeList() {
		return arrangeList;
	}

	public void setArrangeList(List<TravelAgendaArrange> arrangeList) {
		this.arrangeList = arrangeList;
	}

	public List<TravelAgendaHotel> getHotelList() {
		return hotelList;
	}

	public void setHotelList(List<TravelAgendaHotel> hotelList) {
		this.hotelList = hotelList;
	}

	


	
	
		
}
