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
import org.springframework.web.bind.annotation.ResponseBody;

import com.netease.yixing.model.User;
import com.netease.yixing.service.IMemberManageService;
import com.netease.yixing.service.ITravelScheduleService;

@Controller
public class MemberManageController {

	@Autowired
	private IMemberManageService memberManageService;

	@Autowired
	private ITravelScheduleService travelScheduleService;

	public IMemberManageService getMemberManageService() {
		return memberManageService;
	}

	public void setMemberManageService(IMemberManageService memberManageService) {
		this.memberManageService = memberManageService;
	}

	public ITravelScheduleService getTravelScheduleService() {
		return travelScheduleService;
	}

	public void setTravelScheduleService(ITravelScheduleService travelScheduleService) {
		this.travelScheduleService = travelScheduleService;
	}

	@RequestMapping(value = "/memberManage/addMember", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addMember(HttpServletRequest request, @RequestBody Map data) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			int schedule_id = Integer.parseInt(String.valueOf(data.get("travelId")));
			String addId = String.valueOf(data.get("uId"));
			String oldId = this.getMemberManageService().getMembersByTravelId(schedule_id);
			boolean isNum = addId.matches("[0-9]*");
			if (!isNum) {
				modelMap.put("success", 0);
				modelMap.put("message", "非法输入");
				return modelMap;
			}
			if (oldId == null) {
				modelMap.put("success", 0);
				modelMap.put("message", "错误的行程Id");
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
			e.printStackTrace();
		}
		return modelMap;
	}

	@RequestMapping(value = "/memberManage/delMember", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delMember(HttpServletRequest request, @RequestBody Map data) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			int id = Integer.parseInt(String.valueOf(data.get("travelId")));
			String delId = String.valueOf(data.get("uId"));
			String oldId = this.getMemberManageService().getMembersByTravelId(id);
			boolean isNum = delId.matches("[0-9]*");
			if (!isNum) {
				modelMap.put("success", 0);
				modelMap.put("message", "非法输入");
				return modelMap;
			}
			if (oldId == null) {
				modelMap.put("success", 0);
				modelMap.put("message", "错误的行程Id");
				return modelMap;
			}
			if (!oldId.contains(delId)) {
				modelMap.put("success", 1);
				modelMap.put("message", "数据库中没有该用户信息");
			} else {
				String[] temp = oldId.split(";;;");
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < temp.length; i++) {
					if (temp[i].equals(delId))
						continue;
					else
						sb.append(temp[i] + ";;;");
				}
				sb.delete(sb.length() - 3, sb.length());
				Map<String, String> updateData = new HashMap<String, String>();
				updateData.put("group_members", sb.toString());
				updateData.put("scheduleId", String.valueOf(id));
				this.getMemberManageService().updateMembers(updateData);
				modelMap.put("success", 1);

			}
		} catch (Exception e) {
			modelMap.put("success", 0);
			e.printStackTrace();
		}
		return modelMap;
	}

	@RequestMapping(value = "/memberManage/getMembers", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getMembers(HttpServletRequest request, @RequestBody Map data) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			int id = Integer.parseInt(String.valueOf(data.get("travelId")));
			String membersId = this.getMemberManageService().getMembersByTravelId(id);
			modelMap.put("success", 1);
			modelMap.put("data", membersId == null ? "" : membersId);
		} catch (Exception e) {
			modelMap.put("success", 0);
			e.printStackTrace();
		}
		return modelMap;
	}

	@RequestMapping(value = "/memberManage/getUidAndPic", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getUidAndPic(HttpServletRequest request, @RequestBody Map data) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			int id = Integer.parseInt(String.valueOf(data.get("scheduleId")));
			String membersIdStr = this.getMemberManageService().getMembersByTravelId(id);
			String[] membersId = membersIdStr.split(";;;");
			if (membersIdStr == null || membersIdStr.length() == 0 || membersId == null || membersId.length == 0) {
				modelMap.put("success", 1);
				modelMap.put("data", "");
				return modelMap;
			}
			List<Map<String, Object>> membersList = new LinkedList<Map<String, Object>>();
			for (String str : membersId) {
				int temp = Integer.parseInt(str);
				String picUri = this.memberManageService.getPicByUserId(temp);
				Map<String, Object> memberMap = new HashMap<String, Object>();
				memberMap.put("userId", temp);
				memberMap.put("picUri", picUri);
				membersList.add(memberMap);
			}
			modelMap.put("success", 1);
			modelMap.put("data", membersList);
		} catch (Exception e) {
			modelMap.put("success", 0);
			e.printStackTrace();
		}
		return modelMap;
	}

	@RequestMapping(value = "/memberManage/getPicNickUidByTravelId", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPicNickUidByTravelId(HttpServletRequest request, @RequestBody Map data) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			int id = Integer.parseInt(String.valueOf(data.get("scheduleId")));
			String membersIdStr = this.getMemberManageService().getMembersByTravelId(id);
			String[] membersId = membersIdStr.split(";;;");
			if (membersIdStr == null || membersIdStr.length() == 0 || membersId == null || membersId.length == 0) {
				modelMap.put("success", 1);
				modelMap.put("data", "");
				return modelMap;
			}

			List<Map<String, Object>> membersList = new LinkedList<Map<String, Object>>();
			for (String str : membersId) {
				int uid = Integer.parseInt(str);
				User u = this.memberManageService.getUserById(uid);
				Map<String, Object> memberMap = new HashMap<String, Object>();
				memberMap.put("userId", uid);
				memberMap.put("picId", u.getPicId());
				memberMap.put("nickname", u.getNickname());
				memberMap.put("username", u.getUsername());
				membersList.add(memberMap);
			}
			modelMap.put("success", 1);
			modelMap.put("data", membersList);
		} catch (Exception e) {
			modelMap.put("success", 0);
			e.printStackTrace();
		}
		return modelMap;
	}

	@RequestMapping(value = "/memberManage/getLatestScheduleMembersByUserId", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getLatestScheduleMembersByUserId(@RequestBody Map map) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<User> members = null;
		boolean success = true;
		try {
			int userId = Integer.parseInt((String) map.get("userId"));
			members = memberManageService.queryLatestScheduleMembersByUserId(userId);
			if(members!=null){
				for (User user : members) {
					user.setPassword(null);
					user.setJoinTravelSchedule(null);
				}
			}
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
		} finally {
			modelMap.put("success", success);
			modelMap.put("members", members);
		}
		return modelMap;
	}
}
