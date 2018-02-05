package com.crm.manager.staff.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.manager.common.JsonResult;
import com.crm.manager.common.enums.AppTableEnum;
import com.crm.manager.staff.dto.StaffAllotRecordsDTO;
import com.crm.manager.staff.dto.StaffDTO;
import com.crm.manager.staff.service.IStaffAllotRecordsService;
import com.crm.manager.staff.service.IStaffService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin/staff")
public class StaffController {
	
	@Autowired
	private IStaffService staffService;
	
	@Autowired
	private IStaffAllotRecordsService staffAllotRecordsService;
	
	@RequestMapping(value = { "/", "/index" })
	public String index(ModelMap map) {
		map.put("appPlatformList", AppTableEnum.values());
		return "admin/staff/index";
	}
	
	
	@RequestMapping(value = { "/list" })
	@ResponseBody
	public PageInfo<StaffDTO> list(StaffDTO staffDTO) {
		return staffService.queryStaff(staffDTO);
	}
	
	
	@RequestMapping(value = "/addview", method = RequestMethod.GET)
	public String addView(ModelMap map) {
		map.put("appPlatformList", AppTableEnum.values());
		return "admin/staff/staffInfo";
	}
	
	/*@RequestMapping(value= {"/add"} ,method = RequestMethod.POST)
	@ResponseBody
	public JsonResult add(StaffDTO staffDTO){
		
		if (staffService.addStaff(staffDTO)){
			return JsonResult.success();
		}
		return JsonResult.failure("新增失败！");
	}*/
	
	@RequestMapping(value = "/editview/{appPlatform}/{staffNo}", method = RequestMethod.GET)
	public String editView(@PathVariable String appPlatform, @PathVariable String staffNo, ModelMap map) {
		StaffDTO staffDTO = new StaffDTO();
		staffDTO.setStaffTable(appPlatform);
		staffDTO.setStaffNo(staffNo);
		StaffDTO staff = staffService.queryStaffByStaffNo(staffDTO);
		map.put("staff", staff);
		map.put("appPlatformList", AppTableEnum.values());
		return "admin/staff/staffInfo";
	}
	
	@RequestMapping(value= {"/edit"} ,method = RequestMethod.POST)
	@ResponseBody
	public JsonResult edit(StaffDTO staffDTO){
		try {
			//新增或者更新工作人员
			if(staffService.editStaff(staffDTO)){
				return JsonResult.success();
			}
		} catch (Exception e) {
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.failure("操作失败！");
	}
	
	@RequestMapping(value = "/delete/{staffNo}", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult delete(StaffDTO staffDTO) {
		try {
			if(staffDTO != null && StringUtils.isNotBlank(staffDTO.getStaffNo())){
				if(staffService.removeStaff(staffDTO)){
					return JsonResult.success();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.failure("删除失败！");
	}
	
	@RequestMapping(value = "/allotrecords/{appPlatform}/{staffNo}", method = RequestMethod.GET)
	public String allotRecords(@PathVariable String appPlatform, @PathVariable String staffNo, ModelMap map) {
		map.put("appPlatform", appPlatform);
		map.put("staffNo", staffNo);
		return "admin/staff/staffAllotRecords";
	}
	
	@RequestMapping(value = "queryAllotrecords", method = RequestMethod.POST)
	@ResponseBody
	public List<StaffAllotRecordsDTO> queryAllotRecords(String allotRecordsTable, String staffNo) {
			return staffAllotRecordsService.queryStaffAllotRecordsByStaffNo(AppTableEnum.getAllotRecordsTableByType(allotRecordsTable), staffNo);
	}

}