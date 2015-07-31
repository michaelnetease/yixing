package com.netease.yixing.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.netease.yixing.model.TravelRecord;
import com.netease.yixing.model.TravelSchedule;
import com.netease.yixing.model.User;
import com.netease.yixing.service.ITravelRecordService;
import com.netease.yixing.service.ITravelScheduleService;
import com.netease.yixing.service.impl.MemberManageService;
import com.netease.yixing.utils.Constant;


@Controller
@RequestMapping(value = "/travelNote")
public class PublishTravelRecordsController{
	@Autowired
	private ITravelRecordService travelRecordService;
	
	@Autowired
	private MemberManageService memberManageService;
	
	@Autowired
	private ITravelScheduleService travelScheduleService;

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
		// TODO Auto-generated method stub
		
		int travelId=Integer.parseInt(String.valueOf(request.getParameter("travelId")));
		System.out.println("共享游记！"+travelId);
		List<TravelRecord> recordList=travelRecordService.queryByTravelId(travelId);
		System.out.println(recordList.toString());
		Map<String,Object> model=new HashMap<String,Object>();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm");
		for(TravelRecord record : recordList){
			String url = Constant.PICDOMAIN+"/"+record.getPictureKey();
			record.setPictureKey("http://"+url+Constant.HANDLERMAP);
			User user=this.memberManageService.getUserById(Integer.parseInt(record.getUid()));
			Date d=record.getUptime();
			
			
			record.setValid(user.getNickname()+"  "+df.format(d));   //这里借用valid字段，设置成用户的nickname
			
		}
		model.put("recordList", recordList);
		
		TravelSchedule ts=this.getTravelScheduleService().queryScheduleDetailsByScheduleId(travelId);
		Date start=ts.getStartTime();
		Date end=ts.getEndTime();
		model.put("travelStartTime", df.format(start));
		model.put("travelEndTime", df.format(end));
		model.put("travelSchedule", ts);
		return new ModelAndView("/publish",model);
	}

}
