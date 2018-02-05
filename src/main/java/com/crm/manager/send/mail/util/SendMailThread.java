package com.crm.manager.send.mail.util;

import java.io.IOException;
import java.util.Map;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.crm.manager.send.enums.SendTypeEnum;
import com.crm.manager.send.mail.domain.EmailBean;

import freemarker.template.TemplateException;

/**
 * 用来发送邮件的 线程，每次发送邮件都会从线程池中取一个线程, 然后进行发邮件操作
 * 
 * @author chen
 */
public class SendMailThread implements Runnable {
	
	private static Logger logger = LoggerFactory.getLogger(SendMailThread.class);
	
	private JavaMailSender javaMailSender;
	private EmailBean email;
	private String sender;
	private Map<String, Object> parameterMap;

	/**
	 * 构造发送邮件
	 * 
	 * @param simpleMailMessage
	 */
	public SendMailThread(JavaMailSender mailSender, EmailBean email, String sender, Map<String, Object> parameterMap) {
		if (javaMailSender == null) {
			this.javaMailSender = mailSender;
		}
		this.email = email;
		this.sender = sender;
		this.parameterMap = parameterMap;
	}
  
    /** 
     * 该方法将在线程池中的线程中执行 
     */  
	public void run() {
		try {
			if (javaMailSender == null) {
				logger.info("javaMailSender 为空");
				return;
			}
			this.sendMessage(javaMailSender, email, sender, parameterMap);
		} catch (MessagingException e) {
			logger.error("邮件发送异常", e);
		} catch (IOException e) {
			logger.error("邮件模板异常", e);
		} catch (TemplateException e) {
			logger.error("邮件模板解析异常", e);
		}
	}
	
	/**
	 * 邮件发送
	 * 
	 * @param javaMailSender
	 * @param email
	 * @param sender
	 * @throws MessagingException 
	 * @throws TemplateException 
	 * @throws IOException 
	 */
	private void sendMessage(JavaMailSender javaMailSender,EmailBean email, String sender, Map<String, Object> parameterMap) 
			throws MessagingException, IOException, TemplateException {
		logger.info("邮件发送开始.........");
		if (email==null||javaMailSender==null) {
			logger.error("邮件发送缺少必须条件: \r[EmailEntity="+email+",JavaMailSenderImpl="+javaMailSender+"]");
			return;
		}
		if(StringUtils.isBlank(sender)){
			logger.error("发件人为空");
			return;
		}
		if(StringUtils.isBlank(email.getEmail())){
			logger.error("收件人为空");
			return;
		}
		// 有参数就模板化
		if (parameterMap != null && parameterMap.size() > 0) {
			email.setSubject(MailTemplateUtil.processByContent(email.getSubject(), parameterMap));
			email.setMessage(MailTemplateUtil.processByContent(email.getMessage(), parameterMap));
		}
		MimeMessage message = javaMailSender.createMimeMessage();
		if (StringUtils.isNotBlank(email.getToCc())){
			message.addRecipients(Message.RecipientType.CC, email.getToCc());
		}
		if (StringUtils.isNotBlank(email.getToBcc())){
			message.addRecipients(Message.RecipientType.BCC, email.getToBcc());
		}
		MimeMessageHelper helper = new MimeMessageHelper(message, true,"utf-8");
		logger.info("本次发件人：【 " + sender + " 】");
		helper.setFrom(sender);
		if (SendTypeEnum.MULTIPLE.getValue().equals(email.getSendType())){
			helper.setTo(MailUtil.setEmail(email.getEmail()));
		}else{
			helper.setTo(email.getEmail());
		}
		helper.setSubject(email.getSubject());
		// true 表示启动HTML格式的邮件
		helper.setText(email.getMessage(), true);
		try {
			MailUtil.setAttachment(helper, email.getAttachmentList());
		} catch (Exception e) {
			logger.error("加入附件失败 : {} ",e);
		}
		javaMailSender.send(message);
		logger.info("邮件发送完毕........." + email.getEmail());
	}
}
