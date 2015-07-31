package com.netease.yixing.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
 
import java.util.List;
 
public class TravelSchedule implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1920165662411898143L;

	private int scheduleId;
	
	private String title;
	
	private Date startTime;
	
	private Date endTime;
	
	private User createUser;
	
	private Date updateTime;
	
	private String groupMembers;
	
	private int mark;
	
	private int visit;
	
	private boolean visable;
	
	private List<TravelScheduleAgenda> agendaList;
	
	private List<Equipment> equipmentList;
	
	private List<TravelRecord> recordList;

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getGroupMembers() {
		return groupMembers;
	}

	public void setGroupMembers(String groupMembers) {
		this.groupMembers = groupMembers;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public int getVisit() {
		return visit;
	}

	public void setVisit(int visit) {
		this.visit = visit;
	}

	public boolean isVisable() {
		return visable;
	}

	public void setVisable(boolean visable) {
		this.visable = visable;
	}
	


	public List<TravelScheduleAgenda> getAgendaList() {
		return agendaList;
	}

	public void setAgendaList(List<TravelScheduleAgenda> agendaList) {
		this.agendaList = agendaList;
	}

	public List<Equipment> getEquipmentList() {
		return equipmentList;
	}

	public void setEquipmentList(List<Equipment> equipmentList) {
		this.equipmentList = equipmentList;
	}

	public List<TravelRecord> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<TravelRecord> recordList) {
		this.recordList = recordList;
	}
	
	
}
