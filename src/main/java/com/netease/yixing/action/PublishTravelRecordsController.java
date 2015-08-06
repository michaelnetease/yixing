package com.netease.yixing.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.netease.yixing.model.Picture;
import com.netease.yixing.model.TravelRecord;
import com.netease.yixing.model.TravelSchedule;
import com.netease.yixing.model.User;
import com.netease.yixing.service.IPictureService;
import com.netease.yixing.service.ITravelRecordService;
import com.netease.yixing.service.ITravelScheduleService;
import com.netease.yixing.service.impl.MemberManageService;
import com.netease.yixing.utils.Constant;
import com.netease.yixing.view.DayHappened;
import com.netease.yixing.view.Record;


@Controller
@RequestMapping(value = "/travelNote")
public class PublishTravelRecordsController{
	@Autowired
	private ITravelRecordService travelRecordService;
	
	@Autowired
	private MemberManageService memberManageService;
	
	@Autowired
	private ITravelScheduleService travelScheduleService;
	
	@Autowired
	private IPictureService pictureService;
	
	
	public IPictureService getPictureService() {
		return pictureService;
	}

	public void setPictureService(IPictureService pictureService) {
		this.pictureService = pictureService;
	}

	public ITravelRecordService getTravelRecordService() {
		return travelRecordService;
	}

	public void setTravelRecordService(ITravelRecordService travelRecordService) {
		this.travelRecordService = travelRecordService;
	}
	
		

	public ITravelScheduleService getTravelScheduleService() {
		return travelScheduleService;
	}

	public void setTravelScheduleService(ITravelScheduleService travelScheduleService) {
		this.travelScheduleService = travelScheduleService;
	}

	public MemberManageService getMemberManageService() {
		return memberManageService;
	}

	public void setMemberManageService(MemberManageService memberManageService) {
		this.memberManageService = memberManageService;
	}

	@RequestMapping(value="/publish",method=RequestMethod.GET)
	protected ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> model=new HashMap<String,Object>();
		int travelId=Integer.parseInt(String.valueOf(request.getParameter("travelId")));
		List<TravelRecord> travelRecordList=travelRecordService.queryByTravelId(travelId);
		TravelSchedule ts=this.getTravelScheduleService().queryScheduleDetailsByScheduleId(travelId);
		String fenxiang = "http://"+Constant.PICDOMAIN+"/"+"fenxiang.png";
		if(ts==null || travelRecordList==null ||travelRecordList.size()==0)
		{
			System.err.println("未发起这样的行程");
			model.put("travelStartTime", null);
			model.put("travelEndTime", null);
			model.put("travelSchedule", "未发起这样的行程");
			model.put("dayItemList", null);
			model.put("fenxiang", fenxiang);
			return new ModelAndView("/publish",model);
		}
		
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date firstDay=ts.getStartTime();
		int daySeq=0;
		Map<Integer, DayHappened> dayHappenedMap=new HashMap<Integer, DayHappened>();
		try {
			for (TravelRecord tr : travelRecordList) {
				daySeq=(int) ((tr.getUptime().getTime()-firstDay.getTime())/86400000+1);
				DayHappened dayHappened=dayHappenedMap.get(daySeq);
				if(dayHappened==null)
				{
					dayHappened=new DayHappened();
					dayHappened.setDaySeq(daySeq);
					dayHappenedMap.put(daySeq, dayHappened);
				}
				List<Record> recordList=dayHappened.getRecordList();
				if(recordList==null)
				{
					recordList=new LinkedList<Record>();
					dayHappened.setRecordList(recordList);
				}
				Record record=new Record();
				User u=this.memberManageService.getUserById(Integer.parseInt(tr.getUid()));
				record.setAuthor(u!=null?u.getNickname():"匿名");
				record.setIconUrl(u!=null?("http://"+Constant.PICDOMAIN+"/"+u.getPicId()+Constant.ICON):"");
				record.setLocation(tr.getLocation());
				record.setText(tr.getText());
				record.setTime(df.format(tr.getUptime()));
				List<String> picUrls=new LinkedList<String>();
				List<Picture> pics=this.pictureService.queryByRecordId(tr.getId());
				for(Picture pic:pics)
				{
					picUrls.add("http://"+Constant.PICDOMAIN+"/"+pic.getKey()+Constant.HANDLERMAP);
				}
				record.setPicUrls(picUrls);
				recordList.add(record);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		int size=dayHappenedMap.size();
		List<DayHappened> finalData=new ArrayList<DayHappened>();
		Set<Integer> keySet=dayHappenedMap.keySet();
		Integer[] keys=new Integer[size];
		keySet.toArray(keys);
		Arrays.sort(keys);
		
		for(Integer ite:keys)
		{
			finalData.add(dayHappenedMap.get(ite));
		}
		

		Date start=ts.getStartTime();
		Date end=ts.getEndTime();
		model.put("travelStartTime", df.format(start));
		model.put("travelEndTime", df.format(end));
		model.put("travelSchedule", ts);
		model.put("fenxiang", fenxiang);
		model.put("dataList", finalData);
		return new ModelAndView("../publish2",model);
	}

}
