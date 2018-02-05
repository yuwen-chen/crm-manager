package com.crm.manager.investment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.manager.common.enums.AppTableEnum;
import com.crm.manager.investment.dto.InvestmentRecordsDTO;
import com.crm.manager.investment.service.IInvestmentRecordsService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin/investment")
public class InvestmentController {
	
	@Autowired
	IInvestmentRecordsService investmentRecordsService;
	
	@RequestMapping(value = { "/", "/index" })
	public String index(ModelMap map) {
		map.put("appPlatformList", AppTableEnum.values());
		return "admin/investment/index";
	}
	
	
	@RequestMapping(value = { "/list" })
	@ResponseBody
	public PageInfo<InvestmentRecordsDTO> list(InvestmentRecordsDTO investmentRecordsDTO) {
		PageInfo<InvestmentRecordsDTO> page = investmentRecordsService.queryInvestmentRecords(investmentRecordsDTO);
		return page;
	}

}
