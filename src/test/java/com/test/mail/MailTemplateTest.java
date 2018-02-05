package com.test.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.crm.manager.Application;
import com.crm.manager.send.mail.dao.IMailTemplateDao;
import com.crm.manager.send.mail.domain.MailTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MailTemplateTest {
	
	@Autowired
	IMailTemplateDao mailTemplateDao;
	
	@Test
	public void save(){
		MailTemplate mailTemplate = new MailTemplate();
		mailTemplate.setTemplateId("REGISTER_SUCCESS_001");
		mailTemplate.setDescription("注册成功");
		mailTemplate.setTemplateSubject("恭喜${username},注册成功.");
		mailTemplate.setTemplateContent("<html> <body> <h3>您好 ${userName}, ${age}!</h3> <div>     欢迎加入蔚捷金融。<br/> 	您的验证码是：1234   本条信息是系统自动发送，请勿回复！ </div> </body> </html>");
		mailTemplate.setStatus("1");
		mailTemplateDao.insertMailTemplate(mailTemplate);
	}
	
	@Test
	public void query(){
		MailTemplate mailTemplate = mailTemplateDao.queryMailTemplateByTemplateId("REGISTER_SUCCESS_001");
		System.out.println(mailTemplate);
	}

}
