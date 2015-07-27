package com.netease.yixing.action;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.netease.yixing.model.User;
import com.netease.yixing.service.IMemberManageService;

@Controller
public class MemberManageController {

	@Autowired
	private IMemberManageService memberManageService;

	public IMemberManageService getMemberManageService() {
		return memberManageService;
	}

	public void setMemberManageService(IMemberManageService memberManageService) {
		this.memberManageService = memberManageService;
	}

	@RequestMapping(value = "/memberManage/addMember", method = RequestMethod.POST)
	public Map<String, Object> addMember(HttpServletRequest request, @RequestBody Map data) {
		System.out.println("***********************测试添加成员**********************");
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			int schedule_id = Integer.parseInt(String.valueOf(data.get("travelId")));
			String addId = String.valueOf(data.get("uId"));
			String oldId = this.getMemberManageService().getMembersByTravelId(schedule_id);
			boolean isNum = addId.matches("[0-9]*");
			System.out.println(isNum);
			if (!isNum) {
				modelMap.put("success", 0);
				modelMap.put("message", "非法输入");
				return modelMap;
			}
			if (oldId.contains(addId)) {
				modelMap.put("success", 1);
				modelMap.put("message", "之前添加过");
			} else {
				String temp = oldId + ";;;" + addId;
				Map<String, String> updateData = new HashMap<String, String>();
				updateData.put("group_members", temp);
				updateData.put("scheduleId", String.valueOf(schedule_id));
				this.getMemberManageService().updateMembers(updateData);
				modelMap.put("success", 1);
			}
		} catch (Exception e) {
			modelMap.put("success", 0);
			System.err.println(e.getMessage());
		}
		return modelMap;
	}

	@RequestMapping(value = "/memberManage/delMember", method = RequestMethod.POST)
	public Map<String, Object> delMember(HttpServletRequest request, @RequestBody Map data) {
		System.out.println("***********************测试删除成员**********************");
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			int id = Integer.parseInt(String.valueOf(data.get("travelId")));
			String delId = String.valueOf(data.get("uId"));
			String oldId = this.getMemberManageService().getMembersByTravelId(id);
			boolean isNum = delId.matches("[0-9]*");
			System.out.println(isNum);
			if (!isNum) {
				modelMap.put("success", 0);
				modelMap.put("message", "非法输入");
				return modelMap;
			}
			if (!oldId.contains(delId)) {
				modelMap.put("success", 1);
				modelMap.put("message", "数据库中没有该用户信息");
			} else {
				System.out.println(delId);
				System.out.println(oldId);
				String[] temp = oldId.split(";;;");
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < temp.length; i++) {
					if (temp[i].equals(delId))
						continue;
					else
						sb.append(temp[i] + ";;;");
				}
				sb.delete(sb.length() - 3, sb.length());
				Map<String, String> insertData = new HashMap<String, String>();
				insertData.put("group_members", sb.toString());
				insertData.put("scheduleId", String.valueOf(id));
				this.getMemberManageService().updateMembers(insertData);
				modelMap.put("success", 1);

			}
		} catch (Exception e) {
			modelMap.put("success", 0);
			System.err.println(e.getMessage());
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/memberManage/getMembers", method = RequestMethod.POST)
	public Map<String, Object> getMembers(HttpServletRequest request, @RequestBody Map data) {
		System.out.println("***********************测试获取成员**********************");
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			int id = Integer.parseInt(String.valueOf(data.get("travelId")));
			String membersId = this.getMemberManageService().getMembersByTravelId(id);
			modelMap.put("success", 1);
			modelMap.put("data", membersId);
		} catch (Exception e) {
			modelMap.put("success", 0);
			System.err.println(e.getMessage());
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/memberManage/getUidAndPic", method = RequestMethod.POST)
	public Map<String, Object> getUidAndPic(HttpServletRequest request, @RequestBody Map data) {
		System.out.println("***********************测试获取uid和pic**********************");
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			int id = Integer.parseInt(String.valueOf(data.get("travelId")));
			String membersIdStr = this.getMemberManageService().getMembersByTravelId(id);
			
			String[] membersId=membersIdStr.split(";;;");
			
			
			if(membersIdStr==null || membersIdStr.length()==0 || membersId==null  || membersId.length==0)
			{
				modelMap.put("success", 1);
				modelMap.put("data", "");
				return modelMap;
			}
			
			List<Map<String,Object>> membersList=new LinkedList<Map<String,Object>>();
			for(String str:membersId)
			{
				int temp=Integer.parseInt(str);
 				String picUri=this.memberManageService.getPicByUserId(temp);
				Map<String,Object> memberMap=new HashMap<String,Object>();
				memberMap.put("userId", temp);
				memberMap.put("picUri", picUri);
				membersList.add(memberMap);
			}
			modelMap.put("success", 1);
			modelMap.put("data", membersList);
		} catch (Exception e) {
			modelMap.put("success", 0);
			System.err.println(e.getMessage());
		}
		return modelMap;
	}
	
	
	
	@RequestMapping(value = "/memberManage/getPicNickUidByTravelId", method = RequestMethod.POST)
	public Map<String, Object> getPicNickUidByTravelId(HttpServletRequest request, @RequestBody Map data) {
		System.out.println("***********************测试获取pic  nickname  uid**********************");
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			int id = Integer.parseInt(String.valueOf(data.get("travelId")));
			String membersIdStr = this.getMemberManageService().getMembersByTravelId(id);
			
			String[] membersId=membersIdStr.split(";;;");
			
			
			if(membersIdStr==null || membersIdStr.length()==0 || membersId==null  || membersId.length==0)
			{
				modelMap.put("success", 1);
				modelMap.put("data", "");
				return modelMap;
			}
			
			List<Map<String,Object>> membersList=new LinkedList<Map<String,Object>>();
			for(String str:membersId)
			{
				int temp=Integer.parseInt(str);
 				User u=this.memberManageService.getUserById(temp);
				Map<String,Object> memberMap=new HashMap<String,Object>();
				memberMap.put("userId", temp);
				memberMap.put("picId", u.getPicId());
				memberMap.put("nickName", u.getNickname());
				membersList.add(memberMap);
			}
			modelMap.put("success", 1);
			modelMap.put("data", membersList);
		} catch (Exception e) {
			modelMap.put("success", 0);
			System.err.println(e.getMessage());
		}
		return modelMap;
	}
	
}