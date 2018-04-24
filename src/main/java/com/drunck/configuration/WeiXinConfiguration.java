package com.drunck.configuration;

import javax.annotation.Resource;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.drunck.handler.LogHandler;
import com.drunck.handler.MsgHandler;
import com.drunck.handler.SubscribeHandler;
import com.drunck.handler.UnsubscribeHandler;

import me.chanjar.weixin.common.api.WxConsts.EventType;
import me.chanjar.weixin.common.api.WxConsts.XmlMsgType;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;

@Configuration
@EnableConfigurationProperties(WechatMpProperties.class)
public class WeiXinConfiguration {
	@Resource
	private WechatMpProperties properties;
	@Resource
	protected LogHandler logHandler;
	@Resource
	private SubscribeHandler subscribeHandler;
	@Resource
	private UnsubscribeHandler unsubscribeHandler;
	@Resource
	private MsgHandler msgHandler;

	@Bean
	public WxMpInMemoryConfigStorage getWxMpConfigStorage() {
		WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
		config.setAppId(properties.getAppId()); // 设置微信公众号的appId
		config.setSecret(properties.getSecret()); // 设置微信公众号的secret
		config.setToken(properties.getToken()); // 设置微信公众号的token
		config.setAesKey(properties.getAesKey()); // 设置微信公众号的aesKey
		return config;
	}

	@Bean
	public WxMpService getWxMpService() {
		WxMpService wxMpService = new WxMpServiceImpl();
		wxMpService.setWxMpConfigStorage(getWxMpConfigStorage());
		return wxMpService;
	}

	@Bean
	public WxMpMessageRouter getRouter() {
		final WxMpMessageRouter newRouter = new WxMpMessageRouter(getWxMpService());

		// 记录所有事件的日志 （异步执行）
		//newRouter.rule().handler(this.logHandler).next();
		
		// 关注事件
	    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
	        .event(EventType.SUBSCRIBE).handler(this.subscribeHandler)
	        .end();

	    // 取消关注事件
	    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
	        .event(EventType.UNSUBSCRIBE)
	        .handler(this.unsubscribeHandler).end();
	    
	    // 默认
	    newRouter.rule().async(false).handler(this.msgHandler).end();

		return newRouter;
	}
}
