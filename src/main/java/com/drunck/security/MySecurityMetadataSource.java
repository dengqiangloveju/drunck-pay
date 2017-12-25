package com.drunck.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import com.drunck.domain.SysPrivilage;
import com.drunck.mapper.SysPrivilageMapper;

@Service("securityMetadataSource")
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
	@Resource
	private SysPrivilageMapper sysPrivilageMapper;

	/**
	 * 加载所有资源与权限的关系
	 * 
	 * @PostConstruct是Java EE 5引入的注解， 
	 * @Spring允许开发者在受管Bean中使用它。当DI容器实例化当前受管Bean时，
	 * @PostConstruct注解的方法会被自动触发，从而完成一些初始化工作，
	 */
	private void loadResourceDefine() {
		if (resourceMap == null) {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
			List<SysPrivilage> privileges = sysPrivilageMapper.selectAll();
			for (SysPrivilage privilege : privileges) {
				Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
				// 通过资源名称来表示具体的权限 注意：必须"ROLE_"开头
				// 关联代码：applicationContext-security.xml
				// 关联代码：com.huaxin.security.MyUserDetailServiceImpl#obtionGrantedAuthorities
				ConfigAttribute configAttribute = new SecurityConfig("ROLE_" + privilege.getResKey());
				configAttributes.add(configAttribute);
				resourceMap.put(privilege.getUrl(), configAttributes);
			}
		}
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		  String requestUrl = ((FilterInvocation) object).getRequestUrl();
		  if(resourceMap == null) {
		   loadResourceDefine();
		  }
		  if(requestUrl.indexOf("?")>-1){
		   requestUrl=requestUrl.substring(0,requestUrl.indexOf("?"));
		  }
		  Collection<ConfigAttribute> configAttributes = resourceMap.get(requestUrl);
		  return configAttributes;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
