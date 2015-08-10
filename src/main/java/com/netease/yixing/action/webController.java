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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.netease.yixing.model.Invitation;
import com.netease.yixing.model.Picture;
import com.netease.yixing.model.TravelRecord;
import com.netease.yixing.model.TravelSchedule;
import com.netease.yixing.model.User;
import com.netease.yixing.service.IInvitationService;
import com.netease.yixing.service.IMemberManageService;
import com.netease.yixing.service.IPictureService;
import com.netease.yixing.service.ITravelRecordService;
import com.netease.yixing.service.ITravelScheduleService;
import com.netease.yixing.service.impl.InvitationService;
import com.netease.yixing.service.impl.MemberManageService;
import com.netease.yixing.utils.Constant;
import com.netease.yixing.view.DayHappened;
import com.netease.yixing.view.Record;


@Controller
@RequestMapping(value = "/travelNote")
public class webController{
	@Autowired
	private ITravelRecordService travelRecordService;
	
	@Autowired
	private MemberManageService memberManageService;
	
	@Autowired
	private ITravelScheduleService travelScheduleService;
	
	@Autowired
	private IInvitationService invitationService;
	
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
			System.err.println("未发起这样的行程或者没有任何的游记");
			model.put("errorMessage", "未发起这样的行程或者没有任何的游记");
			return new ModelAndView("../jsp/invitationError",model);
		}
		
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date firstDay=ts.getStartTime();
		int daySeq=0;
		Map<Integer, DayHappened> dayHappenedMap=new HashMap<Integer, DayHappened>();
		try {
			for (TravelRecord tr : travelRecordList) {
				daySeq=(int) ((tr.getUptime().getTime()-firstDay.getTime())/86400000+1);
				if(daySeq<1 || daySeq>31) daySeq=30;
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
				if(u.getPicId()==null || u.getPicId().length()==0)
				{
					u.setPicId("me_empty.png");
				}
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
			return new ModelAndView("../jsp/invatationError",model);
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
		return new ModelAndView("../jsp/publish",model);
	}
	@RequestMapping(value="/n",method=RequestMethod.GET)
	protected ModelAndView handle2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> model=new HashMap<String,Object>();
		String id =request.getParameter("id");
		try{
		Invitation it = invitationService.queryByRnd(id);
		String travelId = it.getTravelId();
		TravelSchedule ts = travelScheduleService.getSimpleScheduleById(Integer.parseInt(travelId));
		User user = ts.getCreateUser();
		Date time=ts.getStartTime();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		model.put("time", sdf.format(time));
		model.put("id", id);
		model.put("title",ts.getTitle());
		model.put("faqiren", user!=null?user.getNickname():"");
		model.put("icon", user!=null?("http://"+Constant.PICDOMAIN+"/"+user.getPicId()+Constant.ICON):"");
		model.put("number", this.travelScheduleService.getJoinUserNumbersInSchedule(Integer.parseInt(travelId)));
		return new ModelAndView("../jsp/invitation",model);
		}catch(Exception e)
		{
			e.printStackTrace();
			model.put("errorMessage", "错误的邀请码");
			return  new ModelAndView("../jsp/invitationError",model);
		}
	}
	
	
	@RequestMapping(value="/queryRndById",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> edit(HttpServletRequest request, @RequestBody Map mp){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		try{
			String travelId = String.valueOf(mp.get("id"));
			Invitation it = invitationService.queryByTravelId(travelId);
			if(it==null){
				modelMap.put("success", 0);
				modelMap.put("message", "行程信息不存在");
				return modelMap;
			}
			String rnd = it.getRnd();
			modelMap.put("success", 1);
			modelMap.put("rnd", rnd);
			return modelMap;
		}catch(Exception e){
			modelMap.put("success", 0);
			modelMap.put("message", "行程信息不存在");
			return modelMap;
		}
	}
	
	@RequestMapping(value="/addMember",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addmember(HttpServletRequest request, @RequestBody Map mp){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		try{
			String uid = String.valueOf(mp.get("uid"));
			String rnd = String.valueOf(mp.get("rnd"));
			Invitation it = invitationService.queryByRnd(rnd);
			String travelId = it.getTravelId();
			
			int userId = Integer.parseInt(uid);
			int schedule_id = Integer.parseInt(travelId);
			memberManageService.addMember(userId, schedule_id);
			TravelSchedule schedule = travelScheduleService.queryScheduleDetailsByScheduleId(schedule_id);
			String title = schedule.getTitle();
			modelMap.put("success", 1);
			modelMap.put("title", title);
			return modelMap;
		}catch(Exception e){
			modelMap.put("success", 0);
			modelMap.put("message", "error");
			return modelMap;
		}
	}
	
}
