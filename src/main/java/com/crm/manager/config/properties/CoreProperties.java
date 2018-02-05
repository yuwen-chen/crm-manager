package com.crm.manager.config.properties;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 自定义属性beans类
 * @author chen
 *
 */
@Component
@Configuration
@PropertySource(value = "classpath:config/properties/core.properties")//配置文件路径  

public class CoreProperties {
	
	@Value("${anon.app.url}")
	private List<String> appUrl;
	
	
	@Value("${sign.match.url}")
	private List<String> signMatchUrl;
	
	@Value("${img.upload.path}")
	private String imgUploadPath;

	public List<String> getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(List<String> appUrl) {
		this.appUrl = appUrl;
	}

	public List<String> getSignMatchUrl() {
		return signMatchUrl;
	}

	public void setSignMatchUrl(List<String> signMatchUrl) {
		this.signMatchUrl = signMatchUrl;
	}

	public String getImgUploadPath() {
		return imgUploadPath;
	}

	public void setImgUploadPath(String imgUploadPath) {
		this.imgUploadPath = imgUploadPath;
	}

}
