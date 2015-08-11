package com.netease.yixing.model;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;

public class TravelAgendaHotel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3008570675271235221L;

	private int hotelId;	
	
	private String hotelName;
	
	private String hotelInfo;
	
	private Date updateTime;
	
	@JsonIgnore
	private TravelScheduleAgenda agenda;
	
	@JsonIgnore
	private int visable;
	
	@JsonIgnore
	private User user;
	
	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getHotelInfo() {
		return hotelInfo;
	}

	public void setHotelInfo(String hotelInfo) {
		this.hotelInfo = hotelInfo;
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	
	
	
}
