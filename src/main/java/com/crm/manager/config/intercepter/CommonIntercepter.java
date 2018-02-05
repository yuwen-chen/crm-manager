package com.crm.manager.config.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.crm.manager.Application;
import com.crm.manager.common.utils.MD5Utils;
import com.crm.manager.common.utils.RequestUtils;
import com.crm.manager.config.properties.CoreProperties;

@Component
public class CommonIntercepter implements HandlerInterceptor {
	
	private static Logger logger = LoggerFactory.getLogger(Application.class);
	
	private static final String DATA_BODY = "data_body";
	private static final String DATA_SIGN = "data_sign";
	
	@Autowired
	private CoreProperties coreProperties;

	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		// 1.如果是匹配的URL,则必须走过滤
		if (!isMatchUrl(request)) {
			return true;
		}
		String dataSign = request.getParameter(DATA_SIGN);
		String dataBody = request.getParameter(DATA_BODY);
		// 2.验证参数是否正确
		if (StringUtils.isBlank(dataSign) || StringUtils.isBlank(dataBody)) {
			RequestUtils.writeExcepetion(response, 502, "错误的请求参数");
			return false;
		}
		
		// 3.参数加密后和传的签名对比
		String encryp = MD5Utils.encryption(dataBody);
		logger.info("encryp:====" + encryp);
		if(!encryp.equals(dataSign)){
			RequestUtils.writeExcepetion(response, 501, "请求不合法");
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
			request.setAttribute("ctx", request.getContextPath());
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
	
	protected boolean isMatchUrl(HttpServletRequest request) {
		return coreProperties.getSignMatchUrl().contains(request.getRequestURI().replace(request.getContextPath(), ""));
	}
	
	/**
	 * 是否 为验签的参数名
	 * @param key
	 * @return
	 */
	public static boolean isEngiore(String key) {
		if (key.equals(DATA_BODY) || key.equals(DATA_SIGN)) {
			return true;
		}
		return false;
	}
}
