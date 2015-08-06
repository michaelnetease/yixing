package com.netease.yixing.view;

import java.util.List;

public class DayHappened implements Comparable{
	
	public int daySeq;
	public List<Record> recordList;
	public int getDaySeq() {
		return daySeq;
	}
	public void setDaySeq(int daySeq) {
		this.daySeq = daySeq;
	}
	public List<Record> getRecordList() {
		return recordList;
	}
	public void setRecordList(List<Record> recordList) {
		this.recordList = recordList;
	}
	@Override
	public int compareTo(Object dh) {
		DayHappened d=(DayHappened)dh; 
		return this.daySeq-d.daySeq;
	}


	
	
	

}
