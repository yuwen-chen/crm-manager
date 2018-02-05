package com.crm.manager.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.manager.common.enums.AppTableEnum;
import com.crm.manager.member.dto.MemberCodeDTO;
import com.crm.manager.member.service.IMemberCodeService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin/membercode")
public class MemberCodeController {
	
	@Autowired
	IMemberCodeService memberCodeService;
	
	@RequestMapping(value = { "/", "/index" })
	public String index(ModelMap map) {
		map.put("appPlatformList", AppTableEnum.values());
		return "admin/membercode/index";
	}
	
	
	@RequestMapping(value = { "/list" })
	@ResponseBody
	public PageInfo<MemberCodeDTO> list(MemberCodeDTO memberCodeDTO) {
		PageInfo<MemberCodeDTO> page = memberCodeService.queryMemberCode(memberCodeDTO);
		return page;
	}
	
	/*@RequestMapping(value = { "/edit/{}" })
	@ResponseBody
	public PageInfo<MemberCodeDTO> list(MemberCodeDTO memberCodeDTO) {
		PageInfo<MemberCodeDTO> page = memberCodeService.queryMemberCode(memberCodeDTO);
		return page;
	}*/

}
