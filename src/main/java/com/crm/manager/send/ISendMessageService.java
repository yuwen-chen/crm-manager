package com.crm.manager.send;

import java.util.Map;

import javax.mail.MessagingException;

import com.crm.manager.send.mail.domain.EmailBean;

public interface ISendMessageService {
	
	/**
	 * 发送邮件
	 * @param templateId 邮件模板ID
	 * @param email 邮件Bean
	 * @param parameterMap 模板对应参数
	 * @throws MessagingException
	 */
	public void send(String templateId, EmailBean email,Map<String, Object> parameterMap)throws MessagingException ;
}
