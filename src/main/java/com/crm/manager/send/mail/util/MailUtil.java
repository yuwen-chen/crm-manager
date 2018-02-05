package com.crm.manager.send.mail.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailUtil {
	
	private static Logger logger = LoggerFactory.getLogger(MailUtil.class);

	public static String[] setEmail(String email) {
		StringTokenizer st = new StringTokenizer(email, ";");
		String[] tokens = new String[st.countTokens()];
		int i = 0;
		while (st.hasMoreTokens()) {
			tokens[i] = st.nextToken();
			i++;
		}
		return tokens;
	}
	
	public static void setAttachment(MimeMessageHelper helper, List<String> list) throws MessagingException, UnsupportedEncodingException {
		if (list == null || list.size() == 0) {
			return;
		}
		logger.info("邮件附件 "+list.size()+" 个");
		for (String accessory : list) {
			File file = new File(accessory);
			if (!file.exists() || !file.isFile()) {
				logger.error("不存在附件 : " + file.getAbsolutePath());
				continue;
			}
			if (!file.canRead()) {
				logger.error("不能读取附件 : " + file.getAbsolutePath());
				continue;
			}
			helper.addAttachment(MimeUtility.encodeWord(file.getName()), file);
			logger.info("加入附件 : " + file.getAbsolutePath());
		}
	}
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(setEmail("123;456")));
	}
}
