package com.netease.yixing.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netease.yixing.model.Equipment;
import com.netease.yixing.service.IHandlerEquipmentService;

@Controller
public class HandlerEquipmentController {

	@Autowired
	private IHandlerEquipmentService handlerEquipmentService;

	public IHandlerEquipmentService getHandlerEquipentService() {
		return handlerEquipmentService;
	}
   
	public void setHandlerEquipentService(IHandlerEquipmentService handlerEquipentService) {
		this.handlerEquipmentService = handlerEquipentService;
	}


	@RequestMapping(value = "/handlerEquipment/getEquipments", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getEquipments(HttpServletRequest request, @RequestBody Map travelId) {
		int id = Integer.parseInt(String.valueOf( travelId.get("travelId")));
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			List<Equipment> equipList = this.handlerEquipmentService.getItems(id);

			List<Map<String, String>> basicData = new ArrayList<Map<String, String>>();
			if (equipList.size() == 0) {
				modelMap.put("success", 1);
				modelMap.put("travelId", id);
				modelMap.put("data", "");
				return modelMap;
			} else {
				modelMap.put("success", 1);
				StringBuffer selectedItems = new StringBuffer();
				for (Equipment eq : equipList) {
					Map<String, String> basic = new HashMap<String, String>();
					basic.put("type", eq.getType());
					basic.put("items", eq.getItems()==null?"":eq.getItems());
					basic.put("selectedItems", eq.getSelectedItems()==null?"":eq.getSelectedItems());
					basicData.add(basic);
					if (eq.getSelectedItems() == null || eq.getSelectedItems().trim().equals(""))
						continue;
					String[] temp = eq.getSelectedItems().split(";;;");
					for (int i = 0; i < temp.length; i++)
						selectedItems.append(temp[i] + ";;;");
				}
				int index=selectedItems.lastIndexOf(";;;");
				if(index>=0)
					selectedItems.delete(index, selectedItems.length());
				modelMap.put("travelId", id);
				modelMap.put("data", basicData==null?"":basicData);
				modelMap.put("selected", selectedItems.toString());
				return modelMap;
			}
		} catch (Exception e) {
			modelMap.put("success", 0);
			e.printStackTrace();
			return modelMap;
		}

	}

	@RequestMapping(value = "/handlerEquipment/getSelectItems", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getSelectItems(HttpServletRequest request, @RequestBody Map travelId) {
		System.out.println("test getSelectItems");
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int id = Integer.parseInt(String.valueOf(travelId.get("travelId")));
		try {
			String selectedItems = this.handlerEquipmentService.getSelectedItems(id);
			if (selectedItems != null && selectedItems.length() > 0) {
				modelMap.put("success", 1);
				modelMap.put("selectedItems", selectedItems);
			} else
			{
				modelMap.put("success", 1);
				modelMap.put("selectedItems", "");
			}
			return modelMap;
		} catch (Exception e) {
			modelMap.put("success", 0);
			e.printStackTrace();
			return modelMap;
		}
	}


	@RequestMapping(value = "/handlerEquipment/updataSelectedItems", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updataSelectedItems(HttpServletRequest request, @RequestBody Map updataData)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		System.out.println(updataData.toString());
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int travelId = Integer.parseInt(String.valueOf(updataData.get("travelId")));
		try {
			List<Equipment> tempData = this.handlerEquipmentService.getItems(travelId);
			if (tempData == null || tempData.size() == 0) {
				Equipment e1 = new Equipment();
				e1.setTravelId(travelId);
				e1.setType("自定义");
				e1.setItems("");
				e1.setSelectedItems("");
				this.handlerEquipmentService.insertBasicItems(e1);
				Equipment e2 = new Equipment();
				e2.setTravelId(travelId);
				e2.setType("文件资料");
				e2.setItems("名片;;;身份证;;;学生证;;;信用卡;;;护照;;;人民币;;;当地货币;;;机票");
				e2.setSelectedItems("");
				this.handlerEquipmentService.insertBasicItems(e2);
				Equipment e3 = new Equipment();
				e3.setTravelId(travelId);
				e3.setType("生活用品");
				e3.setItems(
						"帐篷;;;化妆品;;;香皂;;;梳子;;;毛巾;;;牙膏;;;棉签;;;笔记本;;;腰包;;;塑料袋;;;眼镜;;;洗发水;;;帽子;;;雨伞;;;太阳镜;;;睡袋;;;拖鞋;;;短裤");
				e3.setSelectedItems("");
				this.handlerEquipmentService.insertBasicItems(e3);
				Equipment e4 = new Equipment();
				e4.setTravelId(travelId);
				e4.setType("工具");
				e4.setItems("电子灭蚊器;;;手机和充电器;;;电脑;;;备用电池;;;相机;;;手电筒;;;透明胶");
				e4.setSelectedItems("");
				this.handlerEquipmentService.insertBasicItems(e4);
				Equipment e5 = new Equipment();
				e5.setTravelId(travelId);
				e5.setType("药品");
				e5.setItems("消炎药;;;防蚊水;;;晕车药;;;头疼药;;;床上软膏;;;创口贴;;;止泻药;;;感冒药");
				e5.setSelectedItems("");
				this.handlerEquipmentService.insertBasicItems(e5);
				tempData.add(e1);
				tempData.add(e2);
				tempData.add(e3);
				tempData.add(e4);
				tempData.add(e5);
				modelMap.put("success", 1);
				return modelMap;
			} else {
				List equipList = (List) updataData.get("equipList");
				if(equipList==null || equipList.size()==0)
				{
					modelMap.put("success", 0);
				}
				Map<String,StringBuffer> ud=new HashMap<String,StringBuffer>();
				for (Object obj : equipList) {
					Map map = (Map) obj;
					String type=String.valueOf(map.get("type"));
					String selected=String.valueOf(map.get("selectedItems"));
					
					if(ud.get(type)==null)
					{
						StringBuffer sb=new StringBuffer();
						sb.append(selected+";;;");
						ud.put(type, sb);
					}
					else
					{
						ud.get(type).append(selected+";;;");
					}
					
				}
				Set<String> s=ud.keySet();
				for(String type:s)
				{
					Equipment equip=new Equipment();
					equip.setTravelId(travelId);
					equip.setType(type);
					StringBuffer sb =ud.get(type);
					sb=sb.delete(sb.length()-3, sb.length());
					equip.setSelectedItems(sb.toString());
					this.handlerEquipmentService.updateSelectedItems(equip);
				}	
				modelMap.put("success", 1);
				return modelMap;
			}
		} catch (Exception e) {
			modelMap.put("success", 0);
			e.printStackTrace();
			return modelMap;
		}
	}
}
