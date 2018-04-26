package com.drunck.service;

import com.drunck.base.BaseService;

import me.chanjar.weixin.mp.bean.result.WxMpUser;

public interface WxUserService extends BaseService {

	void subscribe(WxMpUser wxMpUser);
	
	void unSubscribe(WxMpUser wxMpUser);

}
