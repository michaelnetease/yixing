package com.netease.yixing.action;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.netease.yixing.model.Equipment;
import com.netease.yixing.service.IHandlerEquipmentService;

@Controller
public class HandlerEquipmentController {

	@Autowired
	private IHandlerEquipmentService handlerEquipentService;
	


	public IHandlerEquipmentService getHandlerEquipentService() {
		return handlerEquipentService;
	}

	public void setHandlerEquipentService(IHandlerEquipmentService handlerEquipentService) {
		this.handlerEquipentService = handlerEquipentService;
	}

	@RequestMapping(value="/handlerEquipment/test", method=RequestMethod.POST)
	public Map<String,Object> test(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		System.out.println("test ok!!!!!!!!!!!");
		return modelMap;
	}
	
	@RequestMapping(value="/handlerEquipment/getEquipments",method=RequestMethod.POST)
	public Map<String,Object> getItems(HttpServletRequest request, @RequestBody Map travelId){
		int id=Integer.parseInt((String) travelId.get("travelId"));
		List<Equipment> equipList= this.handlerEquipentService.getItems(id);
		Map<String,Object> modelMap = new HashMap<String,Object>();
		if(equipList.size()==0)
		{
			Equipment e1=new Equipment();
			e1.setTravelId(id);
			e1.setType("自定义");
			e1.setItems("");
			e1.setSelectedItems("");
			this.handlerEquipentService.insertBasicItems(e1);
			Equipment e2=new Equipment();
			e2.setTravelId(id);
			e2.setType("文件材料");
			e2.setItems("名片;;;身份证;;;学生证;;;信用卡;;;护照;;;人民币;;;当地货币;;;机票;;;");
			e2.setSelectedItems("");
			this.handlerEquipentService.insertBasicItems(e2);
			Equipment e3=new Equipment();
			e3.setTravelId(id);
			e3.setType("生活用品");
			e3.setItems("帐篷;;;化妆品;;;香皂;;;梳子;;;毛巾;;;牙膏;;;棉签;;;笔记本;;;腰包;;;塑料袋;;;眼镜;;;洗发水;;;帽子;;;雨伞;;;太阳镜;;;睡袋;;;拖鞋;;;短裤;;;");
			e3.setSelectedItems("");
			this.handlerEquipentService.insertBasicItems(e3);
			Equipment e4=new Equipment();
			e4.setTravelId(id);
			e4.setType("工具");
			e4.setItems("电子灭蚊器;;;手机和充电器;;;电脑;;;备用电池;;;相机;;;手电筒;;;透明胶;;;");
			e4.setSelectedItems("");
			this.handlerEquipentService.insertBasicItems(e4);
			Equipment e5=new Equipment();
			e5.setTravelId(id);
			e5.setType("药品");
			e5.setItems("消炎药;;;防蚊水;;;晕车药;;;头疼药;;;床上软膏;;;创口贴;;;止泻药;;;感冒药;;;");
			e5.setSelectedItems("");
			this.handlerEquipentService.insertBasicItems(e5);
			equipList.add(e1);
			equipList.add(e2);
			equipList.add(e3);
			equipList.add(e4);
			equipList.add(e5);
			System.out.println("用户第一次查询，往数据库中添加新的数据");
			modelMap.put("success", true);
			modelMap.put("equipList", equipList);
			return modelMap;
		}
		else
		{
			System.out.println("数据库中已经有数据了");
			
			modelMap.put("data", equipList);
			modelMap.put("success", true);
			
			return modelMap;
		}
		
	}
	
	
	@RequestMapping(value="/handlerEquipment/getSelectItems",method=RequestMethod.POST)
	public Map<String,Object> login(HttpServletRequest request, @RequestBody Map travelId){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		int id=Integer.parseInt((String)travelId.get("travelId"));
		
		String selectedItems=this.handlerEquipentService.getSelectedItems(id);
		if(selectedItems!=null && selectedItems.length()>0)
		{
			modelMap.put("equipList", selectedItems);
			modelMap.put("success", true);
		}
		else
			modelMap.put("success", false);
		System.out.println("获得selectedItems");
		return modelMap;
	}
	
	@RequestMapping(value="/handlerEquipment/updataSelectedItems",method=RequestMethod.POST)
//	public Map<String,Object> updataSelectedItems(HttpServletRequest request, @RequestBody List<Equipment> equipList){
	public Map<String,Object> updataSelectedItems(HttpServletRequest request, @RequestBody Map equipList) throws UnsupportedEncodingException{	
		request.setCharacterEncoding("UTF-8"); 
	Map<String,Object> modelMap = new HashMap<String,Object>();
	System.out.println(equipList.toString());
	
	List elist=(List) equipList.get("equipList");
	for(Object obj:elist)
	{
		Map map=(Map)obj;
		Equipment equip=new Equipment();
		equip.setTravelId((int)map.get("travelId"));
		equip.setType((String)map.get("type"));
		equip.setSelectedItems((String)map.get("selectedItems"));
		System.out.println(equip.getType());
		System.out.println(equip.getTravelId());
		System.out.println(equip.getSelectedItems());
		this.handlerEquipentService.updateSelectedItems(equip);

	}
	System.out.println(elist.size());
		// for(Equipment equip:equipList)
		// {
		// this.handlerEquipentService.updateSelectedItems(equip);
		// }
		modelMap.put("success", true);
		System.out.println("更新items");
		return modelMap;
	}
	
	
	

}
