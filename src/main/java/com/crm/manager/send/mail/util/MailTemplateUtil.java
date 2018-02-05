package com.crm.manager.send.mail.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class MailTemplateUtil {
	
	private static Configuration configuration;
	

	public static String processByContent(String content, Map<String, Object> parameters) throws IOException, TemplateException {
		Template template = new Template(null, new StringReader(content), configuration);
        return doProcess(template, parameters);
    }
	
	private static String doProcess(Template template, Map<String, Object> parameters) throws TemplateException, IOException {
		StringWriter stringWriter = new StringWriter();
		template.process(parameters, stringWriter);
		stringWriter.flush();
		return stringWriter.toString();
	}
	
	/*public void afterPropertiesSet() throws Exception {
		Configuration  cfg  =   new  Configuration(); 
	    cfg.setDefaultEncoding("UFT-8");
	    StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
	    cfg.setTemplateLoader(stringTemplateLoader);
	    System.out.println("1111111111");
	}*/
}
