package com.netease.yixing.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnore;
import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonIgnore;
public class Equipment implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 2747728702378380485L;
	public int id;
	public int travelId;
	public String type;
	public String items;
	public String selectedItems;
	
	@JsonIgnore
	private TravelSchedule schedule;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTravelId() {
		return travelId;
	}
	public void setTravelId(int travelId) {
		this.travelId = travelId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
	public String getSelectedItems() {
		return selectedItems;
	}
	public void setSelectedItems(String selectedItems) {
		this.selectedItems = selectedItems;
	}
	public TravelSchedule getSchedule() {
		return schedule;
	}
	public void setSchedule(TravelSchedule schedule) {
		this.schedule = schedule;
	}
	

	
	

	
	
}
