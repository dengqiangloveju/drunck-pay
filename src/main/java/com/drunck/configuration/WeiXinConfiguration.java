package com.drunck.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;

@Configuration
public class WeiXinConfiguration {
	@Value(value = "${weixin.appId}") 
	private String appId;
	@Value(value = "${weixin.secret}") 
	private String secret;
	@Value(value = "${weixin.token}") 
	private String token;
	@Value(value = "${weixin.aesKey}") 
	private String aesKey;
	
	@Bean
	public WxMpInMemoryConfigStorage getWxMpConfigStorage() {
		WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
		config.setAppId(appId); // 设置微信公众号的appId
	    config.setSecret(secret); // 设置微信公众号的secret
	    config.setToken(token); // 设置微信公众号的token
	    config.setAesKey(aesKey); // 设置微信公众号的aesKey
		return config;
	}
	
	@Bean
	public WxMpService getWxMpService() {
		WxMpService wxMpService = new WxMpServiceImpl();
		wxMpService.setWxMpConfigStorage(getWxMpConfigStorage());
		return wxMpService;
	}
}
