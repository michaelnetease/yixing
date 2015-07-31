package com.netease.yixing.model;

import java.util.Date;

/*
 * CREATE TABLE `travel_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(20) NOT NULL,
  `travel_id` int(20) NOT NULL,
  `text` varchar(256) ,
  `picture_url` var(256),
  `upload_time` datetime NOT NULL,
  `valid` tinyint(2) NOT NULL, 
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 * */

public class TravelRecord {
	private int id;// Record ID
	private String uid; // USER　ID	 
	private String  travelId; // Travel ID 行程 ID
	private String text; // 文本信息
	private String pictureKey; // 图片的key
	private String location;
	private Date uptime; // 时间
	private String valid; //  是否展示 
	public String toString(){
		return "[id="+id+",uid="+uid+",travelID="+travelId+",text="+text+",Picturekey="+pictureKey+",location="+location+",uptime="+uptime+",valid="+valid+"]";
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getTravelId() {
		return travelId;
	}

	public void setTravelId(String travelId) {
		this.travelId = travelId;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getPictureKey() {
		return pictureKey;
	}
	public void setPictureKey(String pictureKey) {
		this.pictureKey = pictureKey;
	}
	public Date getUptime() {
		return uptime;
	}
	public void setUptime(Date uptime) {
		this.uptime = uptime;
	}
	public String getValid() {
		return valid;
	}
	public void setValid(String valid) {
		this.valid = valid;
	}
	
	
}


