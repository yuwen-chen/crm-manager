package com.crm.manager.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.manager.common.JsonResult;
import com.crm.manager.send.ISendMessageService;
import com.crm.manager.send.enums.SendTypeEnum;
import com.crm.manager.send.mail.domain.EmailBean;

@RestController  
@RequestMapping("/mail")  
public class MailController {  
	
	@Autowired
	ISendMessageService sendMessageService;
    
      
    @RequestMapping("/sendemail")  
    public Object sendEmail()  {  
        try {
			EmailBean email = new EmailBean();
			email.setEmail("3365666709@qq.com");
			email.setSendType(SendTypeEnum.SINGLE.getValue());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("username", "yunzhang");
			map.put("age", "16");
			sendMessageService.send("REGISTER_SUCCESS_001",email,map);
			return JsonResult.success("邮件发送");
		} catch (MessagingException e) {
			e.printStackTrace();
			return JsonResult.failure(e.getMessage());
		}
    } 
}
