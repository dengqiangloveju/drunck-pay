package com.drunck.service;

import com.drunck.base.BaseService;

import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;

public interface WxMessageService extends BaseService {

	void subscribe(WxMpXmlMessage wxMessage);
	
	void unSubscribe(WxMpXmlMessage wxMessage);

}
