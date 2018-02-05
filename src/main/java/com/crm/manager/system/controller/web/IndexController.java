package com.crm.manager.system.controller.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crm.manager.common.base.controller.BaseController;
import com.crm.manager.system.dto.UserDTO;
import com.crm.manager.system.service.IUserService;

@Controller
public class IndexController extends BaseController{
	
	@Autowired
	private IUserService userService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value={"/","/index"})
	public String index(){
		List<UserDTO> userDTOs = userService.findAll();
		logger.debug(userDTOs.toString());
		return "index";
	}
}
