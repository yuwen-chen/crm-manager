package com.crm.manager.config.shiro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

import com.crm.manager.config.properties.CoreProperties;
import com.crm.manager.system.service.IResourceService;

@Configuration
@Import(ShiroManager.class)
public class ShiroConfig {
	
	@Resource
	private IResourceService resourceService;
	
	@Autowired
	private CoreProperties coreProperties;
	

	@Bean(name = "realm")
	@DependsOn("lifecycleBeanPostProcessor")
	@ConditionalOnMissingBean
	public Realm realm() {
		return new MyRealm();
	}
	
	 /**
     * 用户授权信息Cache
     */
    @Bean(name = "shiroCacheManager")
    @ConditionalOnMissingBean
    public CacheManager cacheManager() {
        return new MemoryConstrainedCacheManager();
    }

    @Bean(name = "securityManager")
    @ConditionalOnMissingBean
    public DefaultSecurityManager securityManager() {
        DefaultSecurityManager sm = new DefaultWebSecurityManager();
        sm.setCacheManager(cacheManager());
        return sm;
    }

	@Bean(name = "shiroFilter")
	@DependsOn("securityManager")
	@ConditionalOnMissingBean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultSecurityManager securityManager, Realm realm) {
		securityManager.setRealm(realm);

		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(securityManager);
		shiroFilter.setLoginUrl("/admin/login");
		shiroFilter.setSuccessUrl("/admin/index");
		shiroFilter.setUnauthorizedUrl("/previlige/no");
		Map<String, String> filterChainDefinitionMap = new HashMap<String, String>();
		filterChainDefinitionMap.put("/assets/**", "anon");
		
		filterChainDefinitionMap.put("/admin/login", "anon");
		
		//不拦截app请求的url
		List<String> appUrlList = coreProperties.getAppUrl();
		for(int i = 0; i < appUrlList.size(); i++){
			filterChainDefinitionMap.put(appUrlList.get(i), "anon");
		}
		
		List<com.crm.manager.system.dto.ResourceDTO> list = resourceService.findAll();
		for (com.crm.manager.system.dto.ResourceDTO resource : list) {
			filterChainDefinitionMap.put(resource.getSourceUrl(), "perms[" + resource.getSourceKey() + "]");
		}
		
		filterChainDefinitionMap.put("/admin/**", "authc");
		shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilter;
	}
}
